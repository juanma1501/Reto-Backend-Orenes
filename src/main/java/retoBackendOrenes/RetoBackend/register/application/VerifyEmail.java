package retoBackendOrenes.RetoBackend.register.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.emailNotifications.Email;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.register.domain.VerifyRequest;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.users.domain.User;
import retoBackendOrenes.RetoBackend.users.infrastructure.UserRepository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class VerifyEmail {


    @Autowired
    private UserRepository userRepository;

    private final Email sendEmail = new Email();

    @Value("${login.token.timelive}")
    private int tokenTimelive;

    @Value("${verifyEmail.servername}")
    private String serverName;

    @Value("${back.server.name}")
    private String backServerName;

    private static final String subjectEmailVerification = "My company | Registro";
    private static final String messageEmailVerification = "Haz click en el siguiente enlace para verificar tu cuenta (solo estará disponible durante los siguientes %s minutos):\n %s/%s";

    public ResponseWrapper<ResponseEntity<?>> verifyEmail(VerifyRequest username) {
        ResponseWrapper<ResponseEntity<?>> verifyEmailWrapper = new ResponseWrapper<>();
        Map<String, Object> userResponseMap = new HashMap<>();

        Optional<User> user = userRepository.findByUsername(username.getUsername());

        if (!user.isPresent()) {
            userResponseMap.put("message", "Email no encontrado");
            ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(userResponseMap, HttpStatus.FORBIDDEN);
            verifyEmailWrapper.setResponse(responseEntity);

            return verifyEmailWrapper;
        } else {
            // Generate random 36-character string token for reset password
            User userResponse = user.get();
            userResponse.setVerifyEmailToken(UUID.randomUUID().toString());
            userResponse.setCreatedEmailVerificationTokenOn(LocalDateTime.now());

            // Save token to database
            userRepository.save(userResponse);

            sendEmail.send(username.getUsername(), subjectEmailVerification,
                    String.format(messageEmailVerification, tokenTimelive, backServerName+serverName, userResponse.getVerifyEmailToken()));

            userResponseMap.put("message", "Mail sent correctly");
            ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(userResponseMap, HttpStatus.ACCEPTED);
            verifyEmailWrapper.setResponse(responseEntity);
            verifyEmailWrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
        }
        return verifyEmailWrapper;
    }

}
