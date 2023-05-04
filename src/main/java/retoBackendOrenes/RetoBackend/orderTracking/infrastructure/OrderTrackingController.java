package retoBackendOrenes.RetoBackend.orderTracking.infrastructure;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retoBackendOrenes.RetoBackend.orderTracking.application.GetOrderTrackingByClient;
import retoBackendOrenes.RetoBackend.orderTracking.application.GetOrderTrackingByTrackingNumber;
import retoBackendOrenes.RetoBackend.orderTracking.application.RegisterOrderTracking;
import retoBackendOrenes.RetoBackend.orderTracking.application.UpdateOrderTracking;
import retoBackendOrenes.RetoBackend.orderTracking.domain.OrderTracking;
import retoBackendOrenes.RetoBackend.orderTracking.domain.RegisterOrderTrackingRequest;
import retoBackendOrenes.RetoBackend.orderTracking.domain.UpdateOrderTrackingRequest;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tracking")
public class OrderTrackingController {

    @Autowired
    RegisterOrderTracking registerOrderTracking;

    @Autowired
    UpdateOrderTracking updateOrderTracking;

    @Autowired
    GetOrderTrackingByTrackingNumber getOrderTrackingByTrackingNumber;

    @Autowired
    GetOrderTrackingByClient getOrderTrackingByClient;


    @Operation(summary = "To start an order tracking")
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
    @PostMapping("/registerOrderTracking")
    public ResponseWrapper<Boolean> registerOrderTracking (@RequestBody RegisterOrderTrackingRequest registerOrderTrackingRequest){
        return registerOrderTracking.registerOrderTracking(registerOrderTrackingRequest);
    }


    @Operation(summary = "To update the location of a tracking and the date")
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
    @PostMapping("/updateOrderTracking")
    public ResponseWrapper<Boolean> updateOrderTracking (@RequestBody UpdateOrderTrackingRequest updateOrderTrackingRequest){
        return updateOrderTracking.updateTrackingOrder(updateOrderTrackingRequest);
    }


    @Operation(summary = "To get all the updates of a tracking.")
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
    @GetMapping("/getOrderTracking/{trackingNumber}")
    public ResponseWrapper<List<OrderTracking>> getOrderByTrackingNumber (@PathVariable("trackingNumber") String trackingNumber){
        return getOrderTrackingByTrackingNumber.getOrderTrackingByTrackingNumber(trackingNumber);
    }


    @Operation(summary = "To get all the tracking records of a client")
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
    @GetMapping("/getOrderTrackingByClient/{client}")
    public ResponseWrapper<List<OrderTracking>> getOrderByClient (@PathVariable("client") String client){
        return getOrderTrackingByClient.getOrderTrackingByClient(client);
    }
}
