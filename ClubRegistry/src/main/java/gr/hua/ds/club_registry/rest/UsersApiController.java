package gr.hua.ds.club_registry.rest;

import gr.hua.ds.club_registry.db.enums.Roles;
import gr.hua.ds.club_registry.db.models.User;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import gr.hua.ds.club_registry.rest.exception.UsersNotFoundException;
import gr.hua.ds.club_registry.service.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/users")
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins = "http://localhost:5000",allowCredentials = "true")
public class UsersApiController {

    @Autowired
    private UserService userService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
   @GetMapping("/all")
   public List <User>  getAllUsers(){
       return userService.findAllUsers();
   }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user")
   public User getUser( @RequestHeader String username ){
       return userService.findUser(username);
   }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public User insertUser(@RequestBody User user){
       return userService.insertUser(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value="/updateuser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user){
        User oldUser =  userService.findUser(user.getUsername());
        return  userService.updateUser(oldUser, user);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable String username){
        User user =  userService.findUser(username);
        userService.deleteUser(user);
        return "User "+ username+ " has been deleted!";
    }



}
