package retoBackendOrenes.RetoBackend.users.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document( collection = "users")
public class User {
    @Id
    private String username;
    private String name;
    private String password;

}
