package es.uca.iw.fullstackwebapp.user.security;

import com.vaadin.flow.spring.security.AuthenticationContext;
import es.uca.iw.fullstackwebapp.user.domain.User;
import es.uca.iw.fullstackwebapp.user.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class AuthenticatedUser {

    private final UserRepository userRepository;
    private final AuthenticationContext authenticationContext;

    public AuthenticatedUser(AuthenticationContext authenticationContext, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.authenticationContext = authenticationContext;
    }

    @Transactional
    public Optional<User> get() {
        return authenticationContext.getAuthenticatedUser(User.class)
                .map(userDetails -> userRepository.findByUsername(userDetails.getUsername()).get());


    }

    public void logout() {
        authenticationContext.logout();
    }

}
