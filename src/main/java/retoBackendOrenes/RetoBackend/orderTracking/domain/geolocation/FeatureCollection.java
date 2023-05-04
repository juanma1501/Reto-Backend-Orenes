package retoBackendOrenes.RetoBackend.orderTracking.domain.geolocation;

import lombok.Data;

import java.util.List;

@Data
public class FeatureCollection {
    private final String type = "FeatureCollection";
    private List<Feature> features;
}
