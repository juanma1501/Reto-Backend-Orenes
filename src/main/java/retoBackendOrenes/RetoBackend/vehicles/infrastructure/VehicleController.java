package retoBackendOrenes.RetoBackend.vehicles.infrastructure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.users.domain.User;
import retoBackendOrenes.RetoBackend.vehicles.application.RegisterVehicle;
import retoBackendOrenes.RetoBackend.vehicles.domain.RegisterVehicleRequest;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    RegisterVehicle registerVehicle;

    @PostMapping("/registerVehicle")
    public ResponseWrapper<Boolean> registerVehicle (@RequestBody RegisterVehicleRequest registerVehicleRequest) {
        return registerVehicle.registerVehicle(registerVehicleRequest);
    }
}
