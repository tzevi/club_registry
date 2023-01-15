package gr.hua.ds.club_registry.rest;

import javax.validation.Valid;

import gr.hua.ds.club_registry.config.jwt.JwtUtils;
import gr.hua.ds.club_registry.rest.dtos.JwtResponse;
import gr.hua.ds.club_registry.rest.dtos.LoginRequest;
import gr.hua.ds.club_registry.rest.exception.CustomException;
import gr.hua.ds.club_registry.db.models.User;
import gr.hua.ds.club_registry.db.enums.Roles;
import gr.hua.ds.club_registry.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class AuthApiController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/auth/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                Roles.valueOf(userDetails.getAuthorities().iterator().next().getAuthority())));
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            ResponseEntity<String> userFound = new ResponseEntity("User found",HttpStatus.CONFLICT);
            return userFound;
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUserRole(user.getUserRole());
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @GetMapping("/whoami")
    public User whoami() {
        return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    }

}
