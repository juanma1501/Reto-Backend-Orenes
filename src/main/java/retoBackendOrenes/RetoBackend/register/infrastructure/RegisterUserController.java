package retoBackendOrenes.RetoBackend.register.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retoBackendOrenes.RetoBackend.register.domain.RegisterRequest;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/register")
@Slf4j
public class RegisterUserController {

    //@PostMapping("/register")
    //public ResponseWrapper<ResponseEntity<?>> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {

    //}
}
