package es.uca.iw.fullstackwebapp.user.ut;

import es.uca.iw.fullstackwebapp.user.ObjectMother;
import es.uca.iw.fullstackwebapp.user.domain.User;
import es.uca.iw.fullstackwebapp.user.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author ivanruizrube
 */

//DataJpaTest es equivalente a poner las siguientes etiquetas:
//@Transactional(propagation = Propagation.REQUIRED)
//@AutoConfigureTestDatabase(replace=Replace.ANY)
//@SpringBootTest 

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldNotFindANotExistingUser() {

        // Given
        // a random user Id
        UUID userId = UUID.randomUUID();

        // When invoking the method
        Optional<User> foundUser = userRepository.findById(userId);

        // Then
        assertThat(foundUser.isPresent()).isFalse();

    }

    @Test
    public void shouldFindAnExistingUser() {

        // Given
        // a certain user
        User testUser = ObjectMother.createTestUser();
        // stored in the repository
        userRepository.save(testUser);

        // When invoking the method findById
        Optional<User> foundUser = userRepository.findById(testUser.getId());

        // Then
        assertThat(foundUser.get()).isEqualTo(testUser);

    }
}
