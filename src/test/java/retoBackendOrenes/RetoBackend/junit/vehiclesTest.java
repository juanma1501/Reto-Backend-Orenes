package retoBackendOrenes.RetoBackend.junit;

import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import retoBackendOrenes.RetoBackend.MvcTestCase;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class vehiclesTest extends MvcTestCase {

    @Test
    @WithUserDetails(value = "ADMIN")
    void JupTest() throws Exception{

        // Correct Register {Everything is correct}
        doPost("/vehicles/registerVehicle", null,
                "plate", "1234JKL",
                "model", "Audi A7",
                "capacity", 897
        ).andExpect(status().isOk());

        // Correct Update {Everything is correct}
        doPost("/vehicles/updateVehicle", null,
                "plate", "1234JKL",
                "model", "Audi A7",
                "capacity", 898
        ).andExpect(status().isOk());

        // Correct Delete
        doDelete("/vehicles/deleteVehicle", null,
                "plate", "1234JKL"
        ).andExpect(status().isOk());

        //Correct
        doGet("/vehicles/getAllVehicles", null
        ).andExpect(status().isOk());

        //Correct
        doGetWithVar("/vehicles/vehicle/{plate}", "1234JKL", null).andExpect(status().isOk());

    }
}
