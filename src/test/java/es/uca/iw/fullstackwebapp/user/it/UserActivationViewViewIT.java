package es.uca.iw.fullstackwebapp.user.it;

import es.uca.iw.fullstackwebapp.user.services.UserManagementService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

/**
 * @author ivanruizrube
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserActivationViewViewIT extends UserActivationViewAbstractIT {


    @MockBean
    private UserManagementService userManagementService;


    @Override
    protected UserManagementService getService() {
        return userManagementService;
    }


    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void shouldNotActivateANoExistingUser() {

        // and the service is stubbed for the activateUser method
        given(userManagementService.activateUser(anyString(), anyString())).willReturn(false);

        super.shouldNotActivateANoExistingUser();


    }

    @Test
    public void shouldActivateAnExistingUser() {

        // and the service is stubbed for the activateUser method
        given(userManagementService.activateUser(anyString(), anyString())).willReturn(true);

        super.shouldActivateAnExistingUser();

        // and
        //verify(userManagementService, times(2)).activateUser(anyString(), anyString());


    }

}
