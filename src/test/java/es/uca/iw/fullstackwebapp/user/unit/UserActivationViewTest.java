package es.uca.iw.fullstackwebapp.user.unit;

import com.vaadin.flow.function.DeploymentConfiguration;
import com.vaadin.flow.server.*;
import es.uca.iw.fullstackwebapp.user.ObjectMother;
import es.uca.iw.fullstackwebapp.user.domain.User;
import es.uca.iw.fullstackwebapp.user.services.UserManagementService;
import es.uca.iw.fullstackwebapp.user.views.UserActivationView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author ivanruizrube
 */

@ExtendWith(MockitoExtension.class)
public class UserActivationViewTest {

    private UserActivationView userView;

    @Mock
    private UserManagementService userManagementService;

    @BeforeEach
    public void setUp() {
        userView = new UserActivationView(userManagementService);

    }
    @Test
    public void shouldShowFailureMessageWhenUserIsNotActivated() {

        // Given
        // a certain user
        User testUser = ObjectMother.createTestUser();

        // and the service is stubbed for the activateUser method
        given(userManagementService.activateUser(anyString(), anyString())).willReturn(false);

        // When
        // Set form values
        userView.setEmail(testUser.getEmail());
        userView.setSecretCode(testUser.getRegisterCode());

        // and invoking the method onActivateButtonClick
        userView.onActivateButtonClick();

        // Then
        verify(userManagementService, times(1)).activateUser(anyString(), anyString());
        // and
        assertThat(userView.getStatus().equals("Ups. The user could not be activated")).isTrue();
    }

    @Test
    public void shouldShowSuccessMessageWhenUserIsActivated() {

        // Given
        // a certain user
        User testUser = ObjectMother.createTestUser();

        // and the service is stubbed for the activateUser method
        given(userManagementService.activateUser(anyString(), anyString())).willReturn(true);

        // When
        // Set form values
        userView.setEmail(testUser.getEmail());
        userView.setSecretCode(testUser.getRegisterCode());

        // and invoking the method onActivateButtonClick
        userView.onActivateButtonClick();

        // Then
        verify(userManagementService, times(1)).activateUser(anyString(), anyString());
        // and
        assertThat(userView.getStatus().equals("Congrats. The user has been activated")).isTrue();
    }


}
