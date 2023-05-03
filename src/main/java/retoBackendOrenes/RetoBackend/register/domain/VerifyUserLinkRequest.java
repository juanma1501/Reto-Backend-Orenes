package retoBackendOrenes.RetoBackend.register.domain;

import lombok.Data;

@Data
public class VerifyUserLinkRequest {

    private String verifyEmailToken;
}
