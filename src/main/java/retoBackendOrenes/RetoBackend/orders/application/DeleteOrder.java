package retoBackendOrenes.RetoBackend.orders.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.orders.domain.DeleteOrderRequest;
import retoBackendOrenes.RetoBackend.orders.domain.Order;
import retoBackendOrenes.RetoBackend.orders.infrastructure.OrderRepository;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.vehicles.domain.DeleteVehicleRequest;
import retoBackendOrenes.RetoBackend.vehicles.domain.Vehicle;
import retoBackendOrenes.RetoBackend.vehicles.infrastructure.VehicleRepository;

import java.util.Optional;

@Component
public class DeleteOrder {

    @Autowired
    OrderRepository orderRepository;

    public ResponseWrapper<Boolean> deleteOrder (DeleteOrderRequest deleteOrderRequest){
        ResponseWrapper<Boolean> deleteWrapper = new ResponseWrapper<>();

        Optional<Order> order = orderRepository.findByOrderNumber(deleteOrderRequest.getOrderNumber());

        if(order.isEmpty()){
            deleteWrapper.setResponse(Boolean.FALSE);
            deleteWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            deleteWrapper.setMessage(new ResponseMessage("El pedido que has introducido no está registrado."));
        }else {
            if (orderRepository.deleteByOrderNumber(deleteOrderRequest.getOrderNumber()) == 1){
                deleteWrapper.setResponse(Boolean.TRUE);
                deleteWrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
                deleteWrapper.setMessage(new ResponseMessage("Pedido borrado con éxito"));
            }else{
                deleteWrapper.setResponse(Boolean.FALSE);
                deleteWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
                deleteWrapper.setMessage(new ResponseMessage("No se pudo borrar el pedido."));
            }
        }

        return deleteWrapper;
    }
}
