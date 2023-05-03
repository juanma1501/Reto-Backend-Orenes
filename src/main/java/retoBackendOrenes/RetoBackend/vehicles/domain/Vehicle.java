package retoBackendOrenes.RetoBackend.vehicles.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data @AllArgsConstructor
@Document( collection = "vehicles")
public class Vehicle {

    @Id
    private String plate;

    private String model;
    private int capacity;
}
