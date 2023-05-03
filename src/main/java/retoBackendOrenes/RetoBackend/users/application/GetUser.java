package retoBackendOrenes.RetoBackend.users.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.users.domain.User;
import retoBackendOrenes.RetoBackend.users.infrastructure.UserRepository;

import java.util.Optional;

@Component
public class GetUser  {

    @Autowired
    UserRepository userRepository;

    public ResponseWrapper<User> getUser (String username){
        ResponseWrapper<User> wrapper = new ResponseWrapper<>();

        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()){
            wrapper.setResponse(user.get());
            wrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
            wrapper.setMessage(new ResponseMessage(ResponseCodes.SUCCESS.getDescription()));

        }else{
            wrapper.setResponse(null);
            wrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            wrapper.setMessage(new ResponseMessage("No existe ningún usuario con ese email o usuario"));
        }

        return wrapper;
    }
}
