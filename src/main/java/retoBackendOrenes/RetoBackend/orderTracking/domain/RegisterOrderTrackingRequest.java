package retoBackendOrenes.RetoBackend.orderTracking.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RegisterOrderTrackingRequest {

    private String trackingNumber;
    private String order;
    private String vehicle;
    private Date lastUpdate;
    private List<Double> coordinates;

}
