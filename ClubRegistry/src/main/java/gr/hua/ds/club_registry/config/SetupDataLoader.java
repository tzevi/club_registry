package gr.hua.ds.club_registry.config;


import gr.hua.ds.club_registry.db.enums.Roles;
import gr.hua.ds.club_registry.db.models.User;
import gr.hua.ds.club_registry.db.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // API

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // == create initial user
        createUserIfNotFound("admin", "admin", "admin", "admin_password","admin@gmail.com", Roles.ROLE_ADMIN);

        alreadySetup = true;
    }


    @Transactional
    User createUserIfNotFound(final String username, final String firstName, final String lastName, final String password, final String email, final Roles role) {
        boolean user = userRepository.existsByUsername(username);
        if (!user) {
            User new_user = new User();
            new_user.setFirst_name(firstName);
            new_user.setLast_name(lastName);
            new_user.setPassword(passwordEncoder.encode(password));
            new_user.setUsername(username);
            new_user.setEmail(email);
            new_user.setUserRole(role);
            User saved_user = userRepository.save(new_user);
            return saved_user;
        }
        return null;
    }

}
