package gr.hua.ds.club_registry.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ClubsNotFoundException extends RuntimeException{
    public ClubsNotFoundException(){
        super("Club not found!");
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,null);
    }
}
