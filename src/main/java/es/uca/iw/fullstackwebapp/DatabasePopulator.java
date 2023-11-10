package es.uca.iw.fullstackwebapp;

import com.github.javafaker.Faker;
import es.uca.iw.fullstackwebapp.book.Book;
import es.uca.iw.fullstackwebapp.book.BookService;
import es.uca.iw.fullstackwebapp.user.services.UserManagementService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabasePopulator implements CommandLineRunner {

    BookService bookService;

    UserManagementService userService;

    public DatabasePopulator(BookService bookService) {
        this.bookService = bookService;

    }

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        // Creamos libros si no hay ninguno
        if (bookService.count() == 0) {
            for (int i = 1; i < 50; i++) {
                Book book = new Book();
                book.setTitle(faker.book().title());
                book.setPublisher(faker.book().publisher());
                book.setAuthor(faker.book().author());
                bookService.createBook(book);
            }

        }
        

    }


}
