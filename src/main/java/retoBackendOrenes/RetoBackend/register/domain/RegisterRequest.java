package retoBackendOrenes.RetoBackend.register.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterRequest {
    private String id;
    private String userId;
    private String username;
    private String name;
    private String password;
    private LocalDateTime createdEmailVerificationTokenOn;
    private boolean verifiedAccount;
}
