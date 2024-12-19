package es.uca.iw.fullstackwebapp.user.unit;

import es.uca.iw.fullstackwebapp.user.ObjectMother;
import es.uca.iw.fullstackwebapp.user.domain.User;
import es.uca.iw.fullstackwebapp.user.services.UserManagementService;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author ivanruizrube
 */

public abstract class UserManagementServiceAbstractTest {

    protected User testUser;

    public abstract UserManagementService getService();

    @BeforeEach
    public void setUp() {
        testUser = ObjectMother.createTestUser();
    }

    public void shouldNotActivateANoExistingUser() {

        // Given a certain user (not stored on the system)

        // When invoking the method ActivateUser
        boolean result = getService().activateUser(testUser.getEmail(), testUser.getRegisterCode());

        // Then the result method is false
        assertThat(result).isFalse();

        // When invoking the method LoadActiveUsers
        List<User> returnedUsers = getService().loadActiveUsers();

        // Then the result does not include the user
        assertThat(returnedUsers.contains(testUser)).isFalse();
    }


    public void shouldActivateAnExistingUser() {

        // Given a certain user who is registered
        getService().registerUser(testUser);

        // When invoking the method ActivateUser
        boolean result = getService().activateUser(testUser.getEmail(), testUser.getRegisterCode());

        // Then the result method is true
        assertThat(result).isTrue();

        // When invoking the method LoadActiveUsers
        List<User> returnedUsers = getService().loadActiveUsers();

        // Then the result includes the user
        assertThat(returnedUsers.contains(testUser)).isTrue();

    }

}
