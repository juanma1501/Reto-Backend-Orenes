package retoBackendOrenes.RetoBackend.vehicles.infrastructure;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.users.domain.User;
import retoBackendOrenes.RetoBackend.vehicles.application.*;
import retoBackendOrenes.RetoBackend.vehicles.domain.DeleteVehicleRequest;
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

    @Autowired
    GetAllVehicles getAllVehicles;

    @Autowired
    DeleteVehicle deleteVehicle;

    @Operation(summary = "To register a vehicle")
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
    @PostMapping("/registerVehicle")
    public ResponseWrapper<Boolean> registerVehicle (@RequestBody RegisterVehicleRequest registerVehicleRequest) {
        return registerVehicle.registerVehicle(registerVehicleRequest);
    }

    @Operation(summary = "To find a vehicle by plate")
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
    @GetMapping("/vehicle/{plate}")
    ResponseWrapper<Vehicle> getVehicle(@PathVariable("plate") String plate) {
        return getVehicle.getVehicle(plate);
    }


    @Operation(summary = "To update a vehicle")
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
    @PostMapping("/updateVehicle")
    public ResponseWrapper<Boolean> registerVehicle (@RequestBody UpdateVehicleRequest updateVehicleRequest) {
        return updateVehicle.updateVehicle(updateVehicleRequest);
    }


    @Operation(summary = "Get all vehicles")
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
    @GetMapping("/getAllVehicles")
    public ResponseWrapper<List<Vehicle>> getAllVehicles () {
        return getAllVehicles.getAllVehicles();
    }


    @Operation(summary = "Delete a vehicle by plate")
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
    @DeleteMapping("/deleteVehicle")
    public ResponseWrapper<Boolean> deleteVehicle (@RequestBody DeleteVehicleRequest deleteVehicleRequest) {
        return deleteVehicle.deleteVehicle(deleteVehicleRequest);
    }


}
