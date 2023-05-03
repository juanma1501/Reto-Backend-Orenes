package retoBackendOrenes.RetoBackend.orders.infrastructure;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import retoBackendOrenes.RetoBackend.orders.domain.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    Optional<Order> findByOrderNumber(String orderNumber);

    List<Order> findAll();

    int deleteByOrderNumber(String orderNumber);
}
