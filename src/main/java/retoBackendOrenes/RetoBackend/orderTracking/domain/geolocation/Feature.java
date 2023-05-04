package retoBackendOrenes.RetoBackend.orderTracking.domain.geolocation;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Data
public class Feature {

    private  String type = "Feature";
    private Map<String, Object> properties;
    private Geometry geometry;

    public Feature() {
        properties = new HashMap<>();
        properties.put("prop0", "value0");
    }

}
