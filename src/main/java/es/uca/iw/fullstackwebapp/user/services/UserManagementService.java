package es.uca.iw.fullstackwebapp.user.services;

import es.uca.iw.fullstackwebapp.user.domain.User;
import es.uca.iw.fullstackwebapp.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepository repository;

    private final UserEmailService emailService;

    @Autowired
    public UserService(UserRepository repository, UserEmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }


    public boolean registerUser(User user) {
        user.setPassword("codedpassword"); // TODO: encode password
        user.setRegisterCode(UUID.randomUUID().toString().substring(0, 5));

        try {
            repository.save(user);
            emailService.sendRegistrationEmail(user);
            return true;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    public boolean activateUser(String email, String registerCode) {

        Optional<User> user = repository.findByEmail(email);

        if (user.isPresent() && user.get().getRegisterCode().equals(registerCode)) {
            user.get().setActive(true);
            repository.save(user.get());
            return true;

        } else {
            return false;
        }

    }


    public Optional<User> loadUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Optional<User> loadUserById(UUID userId) {
        return repository.findById(userId);
    }

    public List<User> loadActiveUsers() {
        return repository.findByActiveTrue();
    }

    public void delete(User testUser) {
        repository.delete(testUser);

    }


}
