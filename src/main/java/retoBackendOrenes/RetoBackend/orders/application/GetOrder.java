package retoBackendOrenes.RetoBackend.orders.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.orders.domain.Order;
import retoBackendOrenes.RetoBackend.orders.infrastructure.OrderRepository;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.vehicles.domain.Vehicle;
import retoBackendOrenes.RetoBackend.vehicles.infrastructure.VehicleRepository;

import java.util.Optional;

@Component
public class GetOrder {

    @Autowired
    OrderRepository orderRepository;

    public ResponseWrapper<Order> getOrder(String orderNumber) {

        ResponseWrapper<Order> vehicleResponseWrapper = new ResponseWrapper<>();

        Optional<Order> order = orderRepository.findByOrderNumber(orderNumber);

        if(order.isPresent()){
            vehicleResponseWrapper.setResponse(order.get());
            vehicleResponseWrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
            vehicleResponseWrapper.setMessage(new ResponseMessage(ResponseCodes.SUCCESS.getDescription()));

        }else{
            vehicleResponseWrapper.setResponse(null);
            vehicleResponseWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            vehicleResponseWrapper.setMessage(new ResponseMessage("No existe ningún pedido con esa matrícula"));
        }

        return vehicleResponseWrapper;

    }
}
