package gr.hua.ds.club_registry.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException( String username) {
        super("Could not find user " + username);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,null);
    }
}
