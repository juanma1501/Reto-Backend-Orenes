package retoBackendOrenes.RetoBackend.orderTracking.domain.geolocation;

import lombok.Data;

import java.util.List;

@Data
public class Geometry {
    private String type = "LineString";
    private List<List<Double>> coordinates;
}
