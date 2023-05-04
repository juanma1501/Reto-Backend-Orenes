package retoBackendOrenes.RetoBackend.orderTracking.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UpdateOrderTrackingRequest {
    private String trackingNumber;
    private List<Double> coordinates;
    private Date lastUpdate;
}
