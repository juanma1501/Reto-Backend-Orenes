package retoBackendOrenes.RetoBackend.orders.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.orders.domain.Order;
import retoBackendOrenes.RetoBackend.orders.infrastructure.OrderRepository;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.vehicles.domain.Vehicle;

import java.util.List;

@Component
public class GetAllOrders {

    @Autowired
    OrderRepository orderRepository;

    public ResponseWrapper<List<Order>> getAllOrders(){
        ResponseWrapper<List<Order>> allOrdersWrapper = new ResponseWrapper<>();

        List<Order> orderList = orderRepository.findAll();

        if (orderList.isEmpty()){
            allOrdersWrapper.setResponse(null);
            allOrdersWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            allOrdersWrapper.setMessage(new ResponseMessage("No hay ningún pedido cargado en la BBDD."));
        }else{
            allOrdersWrapper.setResponse(orderList);
            allOrdersWrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
            allOrdersWrapper.setMessage(new ResponseMessage(ResponseCodes.SUCCESS.getDescription()));
        }

        return  allOrdersWrapper;
    }
}
