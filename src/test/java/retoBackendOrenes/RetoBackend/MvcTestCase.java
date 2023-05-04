package retoBackendOrenes.RetoBackend;

import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class MvcTestCase {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    private final String username = "admin";
    private final String password = "admin";

    @Before
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        // Autenticar al usuario
        this.mvc.perform(formLogin().user(username).password(password));
    }

    protected ResultActions doPost(String url, HttpSession session, Object... fieldsAndValues) throws Exception {
        url = normalize(url);

        JSONObject jso = new JSONObject();
        for (int i=0; i<fieldsAndValues.length; i=i+2)
            jso.put(fieldsAndValues[i].toString(), fieldsAndValues[i+1]);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.
                post(url).
                contentType("application/json").
                content(jso.toString())
                .with(csrf());

        if (session!=null)
            request.session((MockHttpSession) session);

        return mvc.perform(request);
    }

    protected ResultActions doDelete(String url, HttpSession session, Object... fieldsAndValues) throws Exception {
        url = normalize(url);

        JSONObject jso = new JSONObject();
        for (int i = 0; i < fieldsAndValues.length; i = i + 2)
            jso.put(fieldsAndValues[i].toString(), fieldsAndValues[i + 1]);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(url)
                .contentType("application/json")
                .content(jso.toString())
                .with(csrf());

        if (session != null)
            request.session((MockHttpSession) session);

        return mvc.perform(request);
    }

    protected ResultActions doPost(String url, HttpSession session, JSONObject jso) throws Exception {
        url = normalize(url);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.
                post(url).
                contentType("application/json").
                content(jso.toString())
                .with(csrf());

        if (session!=null)
            request.session((MockHttpSession) session);

        return mvc.perform(request);
    }

    protected ResultActions doPut(String url, HttpSession session, Object... fieldsAndValues) throws Exception {
        url = normalize(url);

        JSONObject jso = new JSONObject();
        for (int i=0; i<fieldsAndValues.length; i=i+2)
            jso.put(fieldsAndValues[i].toString(), fieldsAndValues[i+1]);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.
                put(url).
                contentType("application/json").
                content(jso.toString())
                .with(csrf());

        return mvc.perform(request);
    }

    protected ResultActions doPut(String url, HttpSession session, JSONObject jso) throws Exception {
        url = normalize(url);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.
                put(url).
                contentType("application/json").
                content(jso.toString())
                .with(csrf());

        if (session!=null)
            request.session((MockHttpSession) session);

        return mvc.perform(request);
    }

    protected ResultActions doGet(String url, HttpSession session) throws Exception {
        url = normalize(url);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.
                get(url).
                contentType("application/json");

        if (session!=null)
            request.session((MockHttpSession) session);

        return mvc.perform(request);
    }

    protected ResultActions doGet(String url, HttpSession session, Object... fieldsAndValues) throws Exception {
        url = normalize(url);

        JSONObject jso = null;
        if (fieldsAndValues.length>0) {
            jso = new JSONObject();
            for (int i=0; i<fieldsAndValues.length; i=i+2)
                jso.put(fieldsAndValues[i].toString(), fieldsAndValues[i+1]);
        }

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.
                get(url).
                contentType("application/json");

        if (session!=null)
            request.session((MockHttpSession) session);

        if (jso!=null)
            request.content(jso.toString());

        return mvc.perform(request);
    }

    protected ResultActions doGetWithVar(String url, String variable, HttpSession session) throws Exception {
        url = normalize(url);

        String uri = UriComponentsBuilder.fromPath(url).buildAndExpand(variable).toUriString();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.
                get(uri).
                contentType("application/json");

        if (session!=null)
            request.session((MockHttpSession) session);

        return mvc.perform(request);
    }

    private String normalize(String url) {
        if (url.charAt(0)!='/')
            url = '/' + url;
        return url;
    }
}
