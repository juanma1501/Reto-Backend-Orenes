package retoBackendOrenes.RetoBackend.orderTracking.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import retoBackendOrenes.RetoBackend.orderTracking.domain.geolocation.FeatureCollection;
import retoBackendOrenes.RetoBackend.orders.domain.Order;
import retoBackendOrenes.RetoBackend.vehicles.domain.Vehicle;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document( collection = "tracking")
public class OrderTracking {
    @Id
    private String trackingNumber;
    private Order order;
    private Vehicle vehicle;
    private Date lastUpdate;
    private FeatureCollection featureCollection;
}
