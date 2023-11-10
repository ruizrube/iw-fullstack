package es.uca.iw.ejemplos.fullstack.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
//...,
        //...
        return bookRepository.save(book);
    }

    public List<Book> readUnpublishedBooks() {

        return bookRepository.findByPublicationDateIsNull();
    }


}
