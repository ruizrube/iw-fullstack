package es.uca.iw.fullstackwebapp.user.it;

import es.uca.iw.fullstackwebapp.user.ObjectMother;
import es.uca.iw.fullstackwebapp.user.domain.User;
import es.uca.iw.fullstackwebapp.user.services.UserManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author ivanruizrube
 */

@SpringBootTest
@Transactional(propagation = Propagation.REQUIRES_NEW) // después de cada test se hace un rollback de la base de datos
public class UserManagementServiceIT {

    User testUser;
    @Autowired
    private UserManagementService userManagementService;

    @Test
    public void shouldNotActivateANoExistingUser() {

        // Given
        // a certain user (not stored on the database)
        User testUser = ObjectMother.createTestUser();

        // When invoking the method ActivateUser
        boolean result = userManagementService.activateUser(testUser.getEmail(), testUser.getRegisterCode());

        // Then the result method is false
        assertThat(result).isFalse();

        // When invoking the method FindActiveUsers
        List<User> returnedUsers = userManagementService.loadActiveUsers();

        // Then the result does not include the user
        assertThat(returnedUsers.contains(testUser)).isFalse();

    }

    @Test
    public void shouldActivateAnExistingUser() {

        // Given
        // a certain user
        User testUser = ObjectMother.createTestUser();

        // who is registered
        userManagementService.registerUser(testUser);

        // When invoking the method ActivateUser
        boolean result = userManagementService.activateUser(testUser.getEmail(), testUser.getRegisterCode());

        // Then the result method is true
        assertThat(result).isTrue();

        // When invoking the method FindActive
        List<User> returnedUsers = userManagementService.loadActiveUsers();

        // Then the result includes the user
        assertThat(returnedUsers.contains(testUser)).isTrue();

    }


}
