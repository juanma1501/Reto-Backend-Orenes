package retoBackendOrenes.RetoBackend.vehicles.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class RegisterVehicleRequest {
    @Id
    private String plate;
    private String model;
    private int capacity;
}
