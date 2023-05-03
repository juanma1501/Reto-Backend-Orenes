package retoBackendOrenes.RetoBackend.vehicles.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.vehicles.domain.UpdateVehicleRequest;
import retoBackendOrenes.RetoBackend.vehicles.domain.Vehicle;
import retoBackendOrenes.RetoBackend.vehicles.infrastructure.VehicleRepository;

import java.util.Optional;

@Component
public class UpdateVehicle {

    @Autowired
    VehicleRepository vehicleRepository;

    public ResponseWrapper<Boolean> updateVehicle(UpdateVehicleRequest updateVehicleRequest) {

        ResponseWrapper<Boolean> updateWrapper = new ResponseWrapper<>();

        Optional<Vehicle> vehicle = vehicleRepository.findByPlate(updateVehicleRequest.getPlate());

        if (vehicle.isPresent()) {

            if (updateVehicleRequest.getCapacity() == 0) {
                updateWrapper.setResponse(Boolean.FALSE);
                updateWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
                updateWrapper.setMessage(new ResponseMessage("El vehículo no puede tener capacidad 0."));
                return updateWrapper;
            }

            if (updateVehicleRequest.getModel() == null || updateVehicleRequest.getModel().isBlank()) {
                updateWrapper.setResponse(Boolean.FALSE);
                updateWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
                updateWrapper.setMessage(new ResponseMessage("Introduce un nombre de modelo."));
                return updateWrapper;
            }

            vehicle.get().setCapacity(updateVehicleRequest.getCapacity());
            vehicle.get().setModel(updateVehicleRequest.getModel());
            vehicleRepository.save(vehicle.get());

            updateWrapper.setResponse(Boolean.TRUE);
            updateWrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
            updateWrapper.setMessage(new ResponseMessage(ResponseCodes.SUCCESS.getDescription()));
        } else {
            updateWrapper.setResponse(Boolean.FALSE);
            updateWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            updateWrapper.setMessage(new ResponseMessage("No existe ningún vehículo con esa matrícula"));
        }

        return updateWrapper;

    }

}
