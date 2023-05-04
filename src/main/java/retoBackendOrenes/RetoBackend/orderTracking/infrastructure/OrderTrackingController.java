package retoBackendOrenes.RetoBackend.orderTracking.infrastructure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retoBackendOrenes.RetoBackend.orderTracking.application.RegisterOrderTracking;
import retoBackendOrenes.RetoBackend.orderTracking.application.UpdateOrderTracking;
import retoBackendOrenes.RetoBackend.orderTracking.domain.RegisterOrderTrackingRequest;
import retoBackendOrenes.RetoBackend.orderTracking.domain.UpdateOrderTrackingRequest;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tracking")
public class OrderTrackingController {

    @Autowired
    RegisterOrderTracking registerOrderTracking;

    @Autowired
    UpdateOrderTracking updateOrderTracking;

    @PostMapping("/registerOrderTracking")
    public ResponseWrapper<Boolean> registerOrderTracking (@RequestBody RegisterOrderTrackingRequest registerOrderTrackingRequest){
        return registerOrderTracking.registerOrderTracking(registerOrderTrackingRequest);
    }

    @PostMapping("/updateOrderTracking")
    public ResponseWrapper<Boolean> updateOrderTracking (@RequestBody UpdateOrderTrackingRequest updateOrderTrackingRequest){
        return updateOrderTracking.updateTrackingOrder(updateOrderTrackingRequest);
    }
}
