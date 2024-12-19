package es.uca.iw.fullstackwebapp.user.unit;

import es.uca.iw.fullstackwebapp.user.EmailFakeService;
import es.uca.iw.fullstackwebapp.user.repositories.UserRepository;
import es.uca.iw.fullstackwebapp.user.services.EmailService;
import es.uca.iw.fullstackwebapp.user.services.UserManagementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

/**
 * @author ivanruizrube
 */

@ExtendWith(MockitoExtension.class)
public class UserManagementServiceTest extends UserManagementServiceAbstractTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private EmailService emailService = new EmailFakeService();

    @Override
    public UserManagementService getService() {
        return new UserManagementService(userRepository, emailService, passwordEncoder);
    }

    @Test
    public void shouldNotActivateANoExistingUser() {
        super.shouldNotActivateANoExistingUser();
    }

    @Test
    public void shouldActivateAnExistingUser() {
        // the repo methods are stubbed
        given(userRepository.findByEmail(anyString())).willReturn(Optional.of(testUser));
        given(userRepository.findByActiveTrue()).willReturn(List.of(testUser));

        super.shouldActivateAnExistingUser();
    }

}
