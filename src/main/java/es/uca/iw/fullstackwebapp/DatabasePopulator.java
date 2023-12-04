package es.uca.iw.fullstackwebapp;

import com.github.javafaker.Faker;
import es.uca.iw.fullstackwebapp.book.Book;
import es.uca.iw.fullstackwebapp.book.BookService;
import es.uca.iw.fullstackwebapp.user.domain.Role;
import es.uca.iw.fullstackwebapp.user.domain.User;
import es.uca.iw.fullstackwebapp.user.services.UserManagementService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabasePopulator implements CommandLineRunner {

    BookService bookService;

    UserManagementService userService;

    public DatabasePopulator(BookService bookService, UserManagementService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        // Creamos admin
        if (userService.count() == 0) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword("admin");
            user.setEmail("admin@uca.es");
            user.addRole(Role.ADMIN);
            userService.registerUser(user);
            userService.activateUser(user.getEmail(), user.getRegisterCode());
            System.out.println("Admin created");

        }
        // Creamos libros si no hay ninguno
        if (bookService.count() == 0) {
            for (int i = 1; i < 50; i++) {
                Book book = new Book();
                book.setTitle(faker.book().title());
                book.setPublisher(faker.book().publisher());
                book.setAuthor(faker.book().author());
                bookService.createBook(book);
                System.out.println("Book created");
            }

        }


    }


}
