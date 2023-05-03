package retoBackendOrenes.RetoBackend.users.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retoBackendOrenes.RetoBackend.enums.ResponseCodes;
import retoBackendOrenes.RetoBackend.shared.ResponseMessage;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.users.domain.User;
import retoBackendOrenes.RetoBackend.users.infrastructure.UserRepository;

import java.util.List;

@Component
@Slf4j
public class GetAllUsers {

    @Autowired
    private UserRepository usersRepository;

    public ResponseWrapper<List<User>> getAllUsers (){
        ResponseWrapper<List<User>> usersWrapper = new ResponseWrapper<List<User>>();
        List<User> userRespond = usersRepository.findAll();

        if (userRespond != null) {
            usersWrapper.setResponse(userRespond);
            if (userRespond.size() > 0) {
                usersWrapper.setResponse(userRespond);
                usersWrapper.setCode(ResponseCodes.SUCCESS.getResponseCode());
                usersWrapper.setMessage(new ResponseMessage(ResponseCodes.SUCCESS.getDescription()));
            } else {
                usersWrapper.setResponse(null);
                usersWrapper.setCode(ResponseCodes.ERROR_NOT_FOUND.getResponseCode());
                usersWrapper.setMessage(new ResponseMessage("No hay ningún usuario en la BBDD."));
            }
        } else {
            usersWrapper.setResponse(null);
            usersWrapper.setCode(ResponseCodes.GENERIC_ERROR.getResponseCode());
            usersWrapper.setMessage(new ResponseMessage("There has been a problem"));
        }
        return usersWrapper;
    }
}
