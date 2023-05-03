package retoBackendOrenes.RetoBackend.vehicles.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.vehicles.domain.DeleteVehicleRequest;
import retoBackendOrenes.RetoBackend.vehicles.domain.Vehicle;
import retoBackendOrenes.RetoBackend.vehicles.infrastructure.VehicleRepository;

import java.util.Optional;

@Component
public class DeleteVehicle {

    @Autowired
    VehicleRepository vehicleRepository;

    public ResponseWrapper<Boolean> deleteVehicle (DeleteVehicleRequest deleteVehicleRequest){
        ResponseWrapper<Boolean> deleteWrapper = new ResponseWrapper<>();

        Optional<Vehicle> vehicle = vehicleRepository.findByPlate(deleteVehicleRequest.getPlate());

        if(vehicle.isEmpty()){
            deleteWrapper.setResponse(Boolean.FALSE);
            deleteWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            deleteWrapper.setMessage(new ResponseMessage("El vehículo que has introducido no está registrado."));
        }else {
            if (vehicleRepository.deleteByPlate(deleteVehicleRequest.getPlate()) == 1){
                deleteWrapper.setResponse(Boolean.TRUE);
                deleteWrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
                deleteWrapper.setMessage(new ResponseMessage("Vehículo borrado con éxito"));
            }else{
                deleteWrapper.setResponse(Boolean.FALSE);
                deleteWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
                deleteWrapper.setMessage(new ResponseMessage("No se pudo borrar el vehículo."));
            }
        }

        return deleteWrapper;
    }
}
