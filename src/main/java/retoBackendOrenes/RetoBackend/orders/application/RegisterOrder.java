package retoBackendOrenes.RetoBackend.orders.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.orders.domain.Order;
import retoBackendOrenes.RetoBackend.orders.domain.UpdateAndRegisterOrderRequest;
import retoBackendOrenes.RetoBackend.orders.infrastructure.OrderRepository;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.vehicles.domain.RegisterVehicleRequest;
import retoBackendOrenes.RetoBackend.vehicles.domain.Vehicle;
import retoBackendOrenes.RetoBackend.vehicles.infrastructure.VehicleRepository;

import java.util.Optional;

@Component
@Slf4j
public class RegisterOrder {

    @Autowired
    OrderRepository orderRepository;

    public ResponseWrapper<Boolean> registerOrder(UpdateAndRegisterOrderRequest updateAndRegisterOrderRequest) {

        ResponseWrapper<Boolean> registerOrderWrapper = new ResponseWrapper<>();

        Optional<Order> order = orderRepository.findByOrderNumber(updateAndRegisterOrderRequest.getOrderNumber());

        if (order.isPresent() || updateAndRegisterOrderRequest.getOrderNumber().equals("")){
            registerOrderWrapper.setResponse(Boolean.FALSE);
            registerOrderWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            registerOrderWrapper.setMessage(new ResponseMessage("El pedido que has introducido ya está registrado o el número de pedido está vacío."));
        }else{
            Order newOrder = new Order();
            newOrder.setOrderNumber(updateAndRegisterOrderRequest.getOrderNumber());
            newOrder.setCreationDate(updateAndRegisterOrderRequest.getCreationDate());
            newOrder.setDeliveryDate(updateAndRegisterOrderRequest.getDeliveryDate());
            newOrder.setDeliveryAddress(updateAndRegisterOrderRequest.getDeliveryAddress());
            newOrder.setOriginAddress(updateAndRegisterOrderRequest.getOriginAddress());
            newOrder.setWeight(updateAndRegisterOrderRequest.getWeight());
            newOrder.setUserId(updateAndRegisterOrderRequest.getUserId());

            orderRepository.save(newOrder);

            registerOrderWrapper.setResponse(Boolean.TRUE);
            registerOrderWrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
            registerOrderWrapper.setMessage(new ResponseMessage("Pedido registrado con éxito."));
        }

        return registerOrderWrapper;
    }
}
