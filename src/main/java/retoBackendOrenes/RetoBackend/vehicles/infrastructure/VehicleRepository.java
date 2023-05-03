package retoBackendOrenes.RetoBackend.vehicles.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import retoBackendOrenes.RetoBackend.vehicles.domain.Vehicle;

import java.util.Optional;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    Optional<Vehicle> findByPlate(String plate);
}
