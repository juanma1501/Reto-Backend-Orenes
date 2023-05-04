package retoBackendOrenes.RetoBackend.orderTracking.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.orderTracking.domain.OrderTracking;
import retoBackendOrenes.RetoBackend.orderTracking.domain.RegisterOrderTrackingRequest;
import retoBackendOrenes.RetoBackend.orderTracking.domain.geolocation.Feature;
import retoBackendOrenes.RetoBackend.orderTracking.domain.geolocation.FeatureCollection;
import retoBackendOrenes.RetoBackend.orderTracking.domain.geolocation.Geometry;
import retoBackendOrenes.RetoBackend.orderTracking.infrastructure.TrackingRepository;
import retoBackendOrenes.RetoBackend.orders.domain.Order;
import retoBackendOrenes.RetoBackend.orders.infrastructure.OrderRepository;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.vehicles.domain.Vehicle;
import retoBackendOrenes.RetoBackend.vehicles.infrastructure.VehicleRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class RegisterOrderTracking  {

    @Autowired
    TrackingRepository trackingRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    public ResponseWrapper<Boolean> registerOrder (RegisterOrderTrackingRequest registerOrderTrackingRequest){

        ResponseWrapper<Boolean> wrapper = new ResponseWrapper<>();
        OrderTracking orderTracking = new OrderTracking();
        Optional<Vehicle> vehicle = vehicleRepository.findByPlate(registerOrderTrackingRequest.getVehicle());
        Optional<Order> order = orderRepository.findByOrderNumber(registerOrderTrackingRequest.getOrder());

        //Comprobamos que tanto el pedido como el vehículo existen.

        if (order.isEmpty()){
            wrapper.setResponse(Boolean.FALSE);
            wrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            wrapper.setMessage(new ResponseMessage("El pedido que estás introduciendo no existe."));
            return  wrapper;
        }

        if (vehicle.isEmpty()){
            wrapper.setResponse(Boolean.FALSE);
            wrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            wrapper.setMessage(new ResponseMessage("El vehículo que estás introduciendo no existe."));
            return  wrapper;
        }

        orderTracking.setTrackingNumber(registerOrderTrackingRequest.getTrackingNumber());
        orderTracking.setOrder(order.get());
        orderTracking.setVehicle(vehicle.get());
        orderTracking.setLastUpdate(registerOrderTrackingRequest.getLastUpdate());

        FeatureCollection featureCollection = new FeatureCollection();
        Feature feature = new Feature();
        Geometry geometry = new Geometry();

        List<List<Double>> coordinates = new ArrayList<>();
        coordinates.add(Arrays.asList(registerOrderTrackingRequest.getCoordinates().get(0), registerOrderTrackingRequest.getCoordinates().get(1)));
        geometry.setCoordinates(coordinates);
        feature.setGeometry(geometry);

        List<Feature> featureList = new ArrayList<>();
        featureList.add(feature);

        featureCollection.setFeatures(featureList);

        orderTracking.setFeatureCollection(featureCollection);

        wrapper.setResponse(Boolean.TRUE);
        wrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
        wrapper.setMessage(new ResponseMessage("Seguimiento de pedido creado con éxito."));

        return wrapper;
    }

}
