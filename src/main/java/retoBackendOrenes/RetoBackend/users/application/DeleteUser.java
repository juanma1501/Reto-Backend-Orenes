package retoBackendOrenes.RetoBackend.users.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.users.domain.DeleteUserRequest;
import retoBackendOrenes.RetoBackend.users.domain.RegisterUserRequest;
import retoBackendOrenes.RetoBackend.users.domain.User;
import retoBackendOrenes.RetoBackend.users.infrastructure.UserRepository;

import java.util.Optional;

@Component
public class DeleteUser {

    @Autowired
    UserRepository userRepository;

    public ResponseWrapper<Boolean> deleteUser (DeleteUserRequest deleteUserRequest){
        ResponseWrapper<Boolean> wrapper = new ResponseWrapper<>();

        Optional<User> user = userRepository.findByUsername(deleteUserRequest.getUsername());

        if (user.isEmpty()){
            wrapper.setResponse(Boolean.FALSE);
            wrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            wrapper.setMessage(new ResponseMessage("El usuario que has introducido no está registrado."));
        }else {
            if (userRepository.deleteByUsername(deleteUserRequest.getUsername()) == 1){
                wrapper.setResponse(Boolean.TRUE);
                wrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
                wrapper.setMessage(new ResponseMessage("Usuario borrado con éxito"));
            }else{
                wrapper.setResponse(Boolean.FALSE);
                wrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
                wrapper.setMessage(new ResponseMessage("No se pudo borrar el usuario."));
            }

        }

        return wrapper;
    }
}
