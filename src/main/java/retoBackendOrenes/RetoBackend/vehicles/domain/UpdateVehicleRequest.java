package retoBackendOrenes.RetoBackend.vehicles.domain;


import lombok.Data;

@Data
public class UpdateVehicleRequest {
    private String plate;
    private String model;
    private int capacity;
}
