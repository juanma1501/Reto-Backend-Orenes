package retoBackendOrenes.RetoBackend.swaggerConfig;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        return new OpenAPI().info(new Info().title("My API")
                .description("API Documentation")
                .version("v2"));
    }
}
