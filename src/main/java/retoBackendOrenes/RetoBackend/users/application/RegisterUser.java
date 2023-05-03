package retoBackendOrenes.RetoBackend.users.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.users.domain.RegisterUserRequest;
import retoBackendOrenes.RetoBackend.users.domain.User;
import retoBackendOrenes.RetoBackend.users.infrastructure.UserRepository;

import java.util.Base64;

@Component
public class RegisterUser {

    @Autowired
    UserRepository userRepository;

    public ResponseWrapper<Boolean> registerUser (RegisterUserRequest registerUserRequest){
        ResponseWrapper<Boolean> registerWrapper = new ResponseWrapper<>();

        if (registerUserRequest.getUsername().equals("") || registerUserRequest.getName().equals("") ||
                registerUserRequest.getPassword().equals("")){
            registerWrapper.setResponse(Boolean.FALSE);
            registerWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            registerWrapper.setMessage(new ResponseMessage("Rellena todos los campos"));

            return registerWrapper;
        }

        if (userRepository.findByUsername(registerUserRequest.getUsername()).isPresent()){
            registerWrapper.setResponse(Boolean.FALSE);
            registerWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            registerWrapper.setMessage(new ResponseMessage("El usuario ya está registrado"));

            return registerWrapper;
        }

        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setName(registerUserRequest.getName());
        user.setPassword(Base64.getEncoder().encodeToString(registerUserRequest.getPassword().getBytes()));

        userRepository.save(user);

        registerWrapper.setResponse(Boolean.TRUE);
        registerWrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
        registerWrapper.setMessage(new ResponseMessage("Usuario registrado correctamente."));

        return registerWrapper;
    }

}
