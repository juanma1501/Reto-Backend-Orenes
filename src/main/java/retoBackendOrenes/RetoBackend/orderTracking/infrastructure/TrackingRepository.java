package retoBackendOrenes.RetoBackend.orderTracking.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import retoBackendOrenes.RetoBackend.orderTracking.domain.OrderTracking;

import java.util.List;

@Repository
public interface TrackingRepository extends MongoRepository<OrderTracking, String> {

    List<OrderTracking> findByTrackingNumber(String trackingNumber);

    List<OrderTracking> findByOrder_client_username(String email);

    List<OrderTracking> findByOrder_OrderNumber(String orderNumber);

    List<OrderTracking> findAll();

}
