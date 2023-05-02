package retoBackendOrenes.RetoBackend.users.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document( collection = "users")
public class User {
    @Id
    private String id;

    @Indexed(unique=true)
    private String userId;

    @Indexed(unique=true)
    private String email;

    private String name;

    private String password;

}
