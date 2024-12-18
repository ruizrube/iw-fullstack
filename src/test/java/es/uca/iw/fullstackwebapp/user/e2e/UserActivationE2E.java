package es.uca.iw.fullstackwebapp.user.e2e;

import es.uca.iw.fullstackwebapp.user.domain.User;
import es.uca.iw.fullstackwebapp.user.it.UserActivationViewAbstractIT;
import es.uca.iw.fullstackwebapp.user.services.UserManagementService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ivanruizrube
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//La etiqueta @Transactional no funciona con selenium y hay que restaurar manualmente el estado de la BD
public class UserActivationE2E extends UserActivationViewAbstractIT {


    @Autowired
    private UserManagementService userManagementService;


    private User testUser;


    @Override
    protected UserManagementService getService() {
        return userManagementService;
    }


    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }

        if (testUser != null && testUser.getId() != null) {
            userManagementService.delete(testUser);
        }
    }

    @Test
    public void shouldNotActivateANoExistingUser() {

        super.shouldNotActivateANoExistingUser();

    }


    @Test
    public void shouldActivateAnExistingUser() {

        super.shouldActivateAnExistingUser();

    }
}
