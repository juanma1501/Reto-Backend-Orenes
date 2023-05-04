package retoBackendOrenes.RetoBackend.orderTracking.domain.geolocation;

import lombok.Data;

@Data
public class Feature {
    private final String type = "Feature";
    private final Object properties = new Object();
    private Geometry geometry;
}
