package retoBackendOrenes.RetoBackend.vehicles.infrastructure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.users.domain.User;
import retoBackendOrenes.RetoBackend.vehicles.application.GetVehicle;
import retoBackendOrenes.RetoBackend.vehicles.application.RegisterVehicle;
import retoBackendOrenes.RetoBackend.vehicles.application.UpdateVehicle;
import retoBackendOrenes.RetoBackend.vehicles.domain.RegisterVehicleRequest;
import retoBackendOrenes.RetoBackend.vehicles.domain.UpdateVehicleRequest;
import retoBackendOrenes.RetoBackend.vehicles.domain.Vehicle;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    RegisterVehicle registerVehicle;

    @Autowired
    GetVehicle getVehicle;

    @Autowired
    UpdateVehicle updateVehicle;

    @PostMapping("/registerVehicle")
    public ResponseWrapper<Boolean> registerVehicle (@RequestBody RegisterVehicleRequest registerVehicleRequest) {
        return registerVehicle.registerVehicle(registerVehicleRequest);
    }

    @GetMapping("/vehicle/{plate}")
    ResponseWrapper<Vehicle> getVehicle(@PathVariable("plate") String plate) {
        return getVehicle.getVehicle(plate);
    }

    @PostMapping("/updateVehicle")
    public ResponseWrapper<Boolean> registerVehicle (@RequestBody UpdateVehicleRequest updateVehicleRequest) {
        return updateVehicle.updateVehicle(updateVehicleRequest);
    }


}
