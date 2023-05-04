package retoBackendOrenes.RetoBackend.orderTracking.domain.geolocation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeatureCollection {
    private String type = "FeatureCollection";
    private List<Feature> features;
}
