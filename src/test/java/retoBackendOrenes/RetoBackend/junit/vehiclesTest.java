package retoBackendOrenes.RetoBackend.junit;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MvcResult;
import retoBackendOrenes.RetoBackend.MvcTestCase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class vehiclesTest extends MvcTestCase {

    @Test
    @WithUserDetails(value = "ADMIN")
    void JupTest() throws Exception{

        MvcResult result;
        String content;
        JSONObject json;

        // Correct Register {Everything is correct}
        result = doPost("/vehicles/registerVehicle", null,
                "plate", "1234JKL",
                "model", "Audi A7",
                "capacity", 897
        ).andExpect(status().isOk()).andReturn();
        content = result.getResponse().getContentAsString();
        json = new JSONObject(content);
        assertEquals("200", json.getString("code"));

        // Incorrect {Empty model}
        result = doPost("/vehicles/registerVehicle", null,
                "plate", "1234JKL",
                "model", "",
                "capacity", 897
        ).andExpect(status().isOk())
                .andReturn();
        content = result.getResponse().getContentAsString();
        json = new JSONObject(content);
        assertEquals("500", json.getString("code"));


        // Incorrect {Duplicate vehicle plate}
        result = doPost("/vehicles/registerVehicle", null,
                "plate", "1234JKL",
                "model", "Audi A7",
                "capacity", 897
        ).andExpect(status().isOk())
                .andReturn();
        content = result.getResponse().getContentAsString();
        json = new JSONObject(content);
        assertEquals("500", json.getString("code"));

        // Correct Update {Everything is correct}
        result = doPost("/vehicles/updateVehicle", null,
                "plate", "1234JKL",
                "model", "Audi A7",
                "capacity", 898
        ).andExpect(status().isOk()).andReturn();
        content = result.getResponse().getContentAsString();
        json = new JSONObject(content);
        assertEquals("200", json.getString("code"));

        // Incorrect Update {Vehicle doesnt exist}
        result = doPost("/vehicles/updateVehicle", null,
                "plate", "kkdh33",
                "model", "Audi A7",
                "capacity", 898
        ).andExpect(status().isOk()).andReturn();
        content = result.getResponse().getContentAsString();
        json = new JSONObject(content);
        assertEquals("500", json.getString("code"));

        //Correct
        result = doGetWithVar("/vehicles/vehicle/{plate}", "1234JKL", null).andExpect(status().isOk()).andReturn();
        content = result.getResponse().getContentAsString();
        json = new JSONObject(content);
        assertEquals("200", json.getString("code"));

        // Correct Delete
        result = doDelete("/vehicles/deleteVehicle", null,
                "plate", "1234JKL"
        ).andExpect(status().isOk()).andReturn();
        content = result.getResponse().getContentAsString();
        json = new JSONObject(content);
        assertEquals("200", json.getString("code"));

        //Correct
        result = doGet("/vehicles/getAllVehicles", null
        ).andExpect(status().isOk()).andReturn();
        content = result.getResponse().getContentAsString();
        json = new JSONObject(content);
        assertEquals("200", json.getString("code"));

        //Incorrect {plate doesnt exist}
        result = doGetWithVar("/vehicles/vehicle/{plate}", "kkdi866", null).andExpect(status().isOk()).andReturn();
        content = result.getResponse().getContentAsString();
        json = new JSONObject(content);
        assertEquals("500", json.getString("code"));

    }
}
