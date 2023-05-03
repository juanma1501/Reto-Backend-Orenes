package retoBackendOrenes.RetoBackend.users.infrastructure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.users.application.GetAllUsers;
import retoBackendOrenes.RetoBackend.users.domain.User;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    @Autowired
    private GetAllUsers getAllUsers;

    @GetMapping("/getAllUsers")
    public ResponseWrapper<List<User>> allUsers () {
        return getAllUsers.getAllUsers();
    }


}
