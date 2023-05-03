package retoBackendOrenes.RetoBackend.orders.infrastructure;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retoBackendOrenes.RetoBackend.orders.application.DeleteOrder;
import retoBackendOrenes.RetoBackend.orders.application.GetAllOrders;
import retoBackendOrenes.RetoBackend.orders.application.GetOrder;
import retoBackendOrenes.RetoBackend.orders.application.RegisterOrder;
import retoBackendOrenes.RetoBackend.orders.domain.DeleteOrderRequest;
import retoBackendOrenes.RetoBackend.orders.domain.Order;
import retoBackendOrenes.RetoBackend.orders.domain.UpdateAndRegisterOrderRequest;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    DeleteOrder deleteOrder;

    @Autowired
    GetAllOrders getAllOrders;

    @Autowired
    GetOrder getOrder;

    @Autowired
    RegisterOrder registerOrder;

    @Operation(summary = "To register an order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "5XX", description = "Server Error",
                    content = @Content)
    })
    @PostMapping("/registerOrder")
    public ResponseWrapper<Boolean> registerOrder (@RequestBody UpdateAndRegisterOrderRequest updateAndRegisterOrderRequest) {
        return registerOrder.registerOrder(updateAndRegisterOrderRequest);
    }


    @Operation(summary = "To get an order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "5XX", description = "Server Error",
                    content = @Content)
    })
    @GetMapping("/order/{orderNumber}")
    public ResponseWrapper<Order> registerOrder (@PathVariable("orderNumber") String orderNumber) {
        return getOrder.getOrder(orderNumber);
    }



    @Operation(summary = "To get all orders.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "5XX", description = "Server Error",
                    content = @Content)
    })
    @GetMapping("/getAllOrders")
    public ResponseWrapper<List<Order>> registerOrder () {
        return getAllOrders.getAllOrders();
    }


    @Operation(summary = "To delete an order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "5XX", description = "Server Error",
                    content = @Content)
    })
    @DeleteMapping("/deleteOrder")
    public ResponseWrapper<Boolean> registerOrder (DeleteOrderRequest deleteOrderRequest) {
        return deleteOrder.deleteOrder(deleteOrderRequest);
    }
}
