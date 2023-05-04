package retoBackendOrenes.RetoBackend.orderTracking.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import retoBackendOrenes.RetoBackend.orderTracking.domain.OrderTracking;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackingRepository extends MongoRepository<OrderTracking, String> {

    Optional<OrderTracking> findByTrackingNumber(String trackingNumber);

    List<OrderTracking> findAll();

}
