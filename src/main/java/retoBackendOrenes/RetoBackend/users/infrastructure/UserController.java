package retoBackendOrenes.RetoBackend.users.infrastructure;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retoBackendOrenes.RetoBackend.shared.ResponseWrapper;
import retoBackendOrenes.RetoBackend.users.application.DeleteUser;
import retoBackendOrenes.RetoBackend.users.application.GetAllUsers;
import retoBackendOrenes.RetoBackend.users.application.GetUser;
import retoBackendOrenes.RetoBackend.users.application.RegisterUser;
import retoBackendOrenes.RetoBackend.users.domain.DeleteUserRequest;
import retoBackendOrenes.RetoBackend.users.domain.RegisterUserRequest;
import retoBackendOrenes.RetoBackend.users.domain.User;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    @Autowired
    private GetAllUsers getAllUsers;

    @Autowired
    DeleteUser deleteUser;

    @Autowired
    RegisterUser registerUser;

    @Autowired
    GetUser getUser;

    @Operation(summary = "To get all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "5XX", description = "Server Error",
                    content = @Content)
    })
    @GetMapping("/getAllUsers")
    public ResponseWrapper<List<User>> allUsers () {
        return getAllUsers.getAllUsers();
    }


    @Operation(summary = "To register a new user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "5XX", description = "Server Error",
                    content = @Content)
    })
    @PostMapping("/registerUser")
    public ResponseWrapper<Boolean> registerUser (RegisterUserRequest registerUserRequest){
        return registerUser.registerUser(registerUserRequest);
    }


    @Operation(summary = "To delete an existing user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "5XX", description = "Server Error",
                    content = @Content)
    })
    @DeleteMapping("/deleteUser")
    public ResponseWrapper<Boolean> deleteUser (DeleteUserRequest deleteUserRequest){
        return deleteUser.deleteUser(deleteUserRequest);
    }


    @Operation(summary = "To find an user by username or email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "5XX", description = "Server Error",
                    content = @Content)
    })
    @GetMapping("user/{username}")
    public ResponseWrapper<User> getUser (@PathVariable("username") String username){
        return getUser.getUser(username);
    }


}
