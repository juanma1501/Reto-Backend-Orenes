package retoBackendOrenes.RetoBackend.users.domain;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String username;
    private String name;
    private String password;

}
