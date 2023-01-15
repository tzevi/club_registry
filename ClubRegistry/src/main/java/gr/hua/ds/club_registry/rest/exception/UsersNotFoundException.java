package gr.hua.ds.club_registry.rest.exception;

import gr.hua.ds.club_registry.db.enums.Roles;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsersNotFoundException extends RuntimeException{

    public UsersNotFoundException( Roles role ) {
        super("Could not find users with role: "+role);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,null);
    }
}
