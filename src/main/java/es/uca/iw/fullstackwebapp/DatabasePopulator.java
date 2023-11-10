package es.uca.iw.ejemplos.fullstack;

import es.uca.iw.ejemplos.fullstack.user.UserService;
import org.springframework.boot.CommandLineRunner;

//@Component
public class DatabasePopulator implements CommandLineRunner {

    UserService userService;

    public DatabasePopulator(UserService userService) {
        this.userService = userService;

    }

    @Override
    public void run(String... args) throws Exception {


    }


}
