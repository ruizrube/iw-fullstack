package es.uca.iw.fullstackwebapp.user.unit;

import es.uca.iw.fullstackwebapp.user.ObjectMother;
import es.uca.iw.fullstackwebapp.user.controllers.UserRestController;
import es.uca.iw.fullstackwebapp.user.domain.User;
import es.uca.iw.fullstackwebapp.user.services.UserManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * @author ivanruizrube
 */

@ExtendWith(MockitoExtension.class)
public class UserRestControllerTest {

    private UserRestController controller;

    @Mock
    private UserManagementService userManagementService;

    @BeforeEach
    public void setUp() {
        controller = new UserRestController(userManagementService);
    }

    @Test
    public void shouldReturnListOfUsers() {

        // Given a certain user
        User testUser = ObjectMother.createTestUser();

        // and the service is stubbed for the method loadActiveUsers
        given(userManagementService.loadActiveUsers()).willReturn(List.of(testUser));

        // When
        // the All method of the controller is invoked
        List<User> result = controller.all();

        // Then
        assertThat(result.contains(testUser));
    }
}
