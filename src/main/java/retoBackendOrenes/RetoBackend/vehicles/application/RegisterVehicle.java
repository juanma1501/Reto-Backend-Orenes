package retoBackendOrenes.RetoBackend.vehicles.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.vehicles.domain.RegisterVehicleRequest;
import retoBackendOrenes.RetoBackend.vehicles.domain.Vehicle;
import retoBackendOrenes.RetoBackend.vehicles.infrastructure.VehicleRepository;

@Component
@Slf4j
public class RegisterVehicle {

    @Autowired
    VehicleRepository vehicleRepository;

    public ResponseWrapper<Boolean> registerVehicle(RegisterVehicleRequest registerVehicleRequest) {
        log.info("Adding vehicle");
        ResponseWrapper<Boolean> registerVehicleWrapper = new ResponseWrapper<>();

        if (registerVehicleRequest.getCapacity() == 0) {
            registerVehicleWrapper.setResponse(Boolean.FALSE);
            registerVehicleWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            registerVehicleWrapper.setMessage(new ResponseMessage("El vehículo no puede tener capacidad 0."));
            return registerVehicleWrapper;
        }

        if(registerVehicleRequest.getModel() == null || registerVehicleRequest.getModel().isBlank()){
            registerVehicleWrapper.setResponse(Boolean.FALSE);
            registerVehicleWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            registerVehicleWrapper.setMessage(new ResponseMessage("Introduce un nombre de modelo."));
            return  registerVehicleWrapper;
        }

        if(vehicleRepository.findByPlate(registerVehicleRequest.getPlate()).isEmpty()){
            Vehicle vehicle = new Vehicle(registerVehicleRequest.getPlate(), registerVehicleRequest.getModel(), registerVehicleRequest.getCapacity());
            vehicleRepository.save(vehicle);

            registerVehicleWrapper.setResponse(Boolean.TRUE);
            registerVehicleWrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
            registerVehicleWrapper.setMessage(new ResponseMessage("Vehicle created."));

        }else{
            registerVehicleWrapper.setResponse(Boolean.FALSE);
            registerVehicleWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            registerVehicleWrapper.setMessage(new ResponseMessage("El vehículo que has introducido ya está registrado."));
            return  registerVehicleWrapper;
        }

        return registerVehicleWrapper;
    }
}
