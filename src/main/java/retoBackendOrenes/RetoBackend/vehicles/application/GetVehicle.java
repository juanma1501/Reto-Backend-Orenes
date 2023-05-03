package retoBackendOrenes.RetoBackend.vehicles.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.vehicles.domain.RegisterVehicleRequest;
import retoBackendOrenes.RetoBackend.vehicles.domain.Vehicle;
import retoBackendOrenes.RetoBackend.vehicles.infrastructure.VehicleRepository;

import java.util.Optional;

@Component
public class GetVehicle {

    @Autowired
    VehicleRepository vehicleRepository;

    public ResponseWrapper<Vehicle> getVehicle(String plate) {

        ResponseWrapper<Vehicle> vehicleResponseWrapper = new ResponseWrapper<>();

        Optional<Vehicle> vehicle = vehicleRepository.findByPlate(plate);

        if(vehicle.isPresent()){
            vehicleResponseWrapper.setResponse(vehicle.get());
            vehicleResponseWrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
            vehicleResponseWrapper.setMessage(new ResponseMessage(ResponseCodes.SUCCESS.getDescription()));

        }else{
            vehicleResponseWrapper.setResponse(null);
            vehicleResponseWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            vehicleResponseWrapper.setMessage(new ResponseMessage("No existe ningún vehículo con esa matrícula"));
        }

        return vehicleResponseWrapper;

    }
}
