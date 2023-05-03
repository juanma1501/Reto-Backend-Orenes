package retoBackendOrenes.RetoBackend.users.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document( collection = "users")
public class User {
    @Id
    private String username;

    private String name;

    private String password;

    private boolean verifiedAccount;

    private String verifyEmailToken;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime createdEmailVerificationTokenOn;



}
