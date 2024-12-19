package es.uca.iw.fullstackwebapp.user.it;

import es.uca.iw.fullstackwebapp.user.services.UserManagementService;
import es.uca.iw.fullstackwebapp.user.unit.UserManagementServiceAbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ivanruizrube
 */

@SpringBootTest
@Transactional(propagation = Propagation.REQUIRES_NEW) // después de cada test se hace un rollback de la base de datos
public class UserManagementServiceIT extends UserManagementServiceAbstractTest {

    @Autowired
    private UserManagementService userManagementService;

    @Override
    public UserManagementService getService() {
        return userManagementService;
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
