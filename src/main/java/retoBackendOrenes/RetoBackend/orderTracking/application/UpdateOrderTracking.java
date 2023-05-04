package retoBackendOrenes.RetoBackend.orderTracking.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.emailNotifications.Email;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.orderTracking.domain.OrderTracking;
import retoBackendOrenes.RetoBackend.orderTracking.domain.UpdateOrderTrackingRequest;
import retoBackendOrenes.RetoBackend.orderTracking.domain.geolocation.Feature;
import retoBackendOrenes.RetoBackend.orderTracking.domain.geolocation.FeatureCollection;
import retoBackendOrenes.RetoBackend.orderTracking.domain.geolocation.Geometry;
import retoBackendOrenes.RetoBackend.orderTracking.infrastructure.TrackingRepository;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;

import javax.management.Query;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateOrderTracking {

    @Autowired
    TrackingRepository trackingRepository;

    public ResponseWrapper<Boolean> updateTrackingOrder (UpdateOrderTrackingRequest updateOrderTrackingRequest){
        ResponseWrapper<Boolean> wrapper = new ResponseWrapper<>();

        List<OrderTracking> orderTrackings = trackingRepository.findByTrackingNumber(updateOrderTrackingRequest.getTrackingNumber());

        if (orderTrackings.isEmpty()){
            wrapper.setResponse(Boolean.FALSE);
            wrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            wrapper.setMessage(new ResponseMessage("No hay ningún seguimiento con ese número."));
            return  wrapper;
        }

        if (updateOrderTrackingRequest.getCoordinates().size() != 2){
            wrapper.setResponse(Boolean.FALSE);
            wrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            wrapper.setMessage(new ResponseMessage("Introduce dos coordenadas."));
            return  wrapper;
        }

        Collections.reverse(orderTrackings);
        OrderTracking orderTracking = orderTrackings.get(0);

        orderTracking
                .getFeatureCollection()
                .getFeatures()
                .get(0).getGeometry()
                .getCoordinates()
                .add(Arrays.asList(updateOrderTrackingRequest.getCoordinates().get(0), updateOrderTrackingRequest.getCoordinates().get(1)));

        orderTracking.setLastUpdate(updateOrderTrackingRequest.getLastUpdate());

        trackingRepository.save(orderTracking);

        //Enviar correo de notificación
        String email = orderTracking.getOrder().getClient().getUsername();

        Email sender = new Email();
        sender.send(email, "Actualización de ubicación", "Estimado cliente \n su pedido ha avanzado y se encuentra ahora en las siguientes coordenadas: \n " +
                updateOrderTrackingRequest.getCoordinates().get(0) + " \n " +
                updateOrderTrackingRequest.getCoordinates().get(1));


        wrapper.setResponse(Boolean.TRUE);
        wrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
        wrapper.setMessage(new ResponseMessage("Ubicación actualizada con éxito."));

        return wrapper;
    }
}
