package retoBackendOrenes.RetoBackend.vehicles.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.vehicles.domain.Vehicle;
import retoBackendOrenes.RetoBackend.vehicles.infrastructure.VehicleRepository;

import java.util.List;

@Component
public class GetAllVehicles {

    @Autowired
    VehicleRepository vehicleRepository;

    public ResponseWrapper<List<Vehicle>> getAllVehicles(){
        ResponseWrapper<List<Vehicle>> allVehiclesWrapper = new ResponseWrapper<>();

        List<Vehicle> vehicleList = vehicleRepository.findAll();

        if (vehicleList.isEmpty()){
            allVehiclesWrapper.setResponse(null);
            allVehiclesWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            allVehiclesWrapper.setMessage(new ResponseMessage("No hay ningún vehículo cargado en la BBDD."));
        }else{
            allVehiclesWrapper.setResponse(vehicleList);
            allVehiclesWrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
            allVehiclesWrapper.setMessage(new ResponseMessage(ResponseCodes.SUCCESS.getDescription()));
        }

        return  allVehiclesWrapper;
    }
}
