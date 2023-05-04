package retoBackendOrenes.RetoBackend.orderTracking.domain.geolocation;

import lombok.Data;
import org.json.JSONObject;

@Data
public class Feature {
    private  String type = "Feature";
    private  JSONObject properties = new JSONObject();
    private Geometry geometry;
}
