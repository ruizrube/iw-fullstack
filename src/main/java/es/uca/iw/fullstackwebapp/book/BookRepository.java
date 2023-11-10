package es.uca.iw.fullstackwebapp.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    List<Book> findByPublicationDateIsNull();

    List<Book> findByTitleContainsAndPublicationDateAfter(String title, LocalDate publicationDate);

    List<Book> findByAuthorContaining(String authorName);

}
