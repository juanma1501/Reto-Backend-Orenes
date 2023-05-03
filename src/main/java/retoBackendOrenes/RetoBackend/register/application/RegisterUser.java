package retoBackendOrenes.RetoBackend.register.application;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;

@Component
public class RegisterUser {

    public ResponseWrapper<ResponseEntity<?>> registerUser(ResponseMessage message, boolean error) {
        ResponseWrapper<ResponseEntity<?>> registerWrapper = new ResponseWrapper<ResponseEntity<?>>();
        if (error) {
            registerWrapper.setResponse(ResponseEntity
                    .badRequest()
                    .body(message));
        } else {
            registerWrapper.setResponse(ResponseEntity.ok(message));
        }
        registerWrapper.setMessage(message);
        return registerWrapper;
    }
}
