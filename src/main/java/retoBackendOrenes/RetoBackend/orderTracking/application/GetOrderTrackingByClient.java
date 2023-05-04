package retoBackendOrenes.RetoBackend.orderTracking.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.orderTracking.domain.OrderTracking;
import retoBackendOrenes.RetoBackend.orderTracking.infrastructure.TrackingRepository;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;

import java.util.List;

@Component
public class GetOrderTrackingByClient {

    @Autowired
    TrackingRepository trackingRepository;

    public ResponseWrapper<List<OrderTracking>> getOrderTrackingByClient (String client){

        ResponseWrapper<List<OrderTracking>> wrapper = new ResponseWrapper<>();

        List<OrderTracking> orderTrackingList = trackingRepository.findByOrder_client_username(client);

        if (orderTrackingList.isEmpty()){
            wrapper.setResponse(null);
            wrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            wrapper.setMessage(new ResponseMessage("No hay ningún seguimiento con ese número."));
            return  wrapper;
        }

        wrapper.setResponse(orderTrackingList);
        wrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
        wrapper.setMessage(new ResponseMessage(ResponseCodes.SUCCESS.getDescription()));


        return wrapper;

    }

}
