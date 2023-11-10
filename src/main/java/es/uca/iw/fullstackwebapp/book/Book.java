package es.uca.iw.ejemplos.fullstack.book;

import es.uca.iw.ejemplos.fullstack.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Book {

    @Id
    private UUID id;

    private String title;

    @ManyToOne
    private User author;

    private LocalDate publicationDate;

    private String editorial;
}
