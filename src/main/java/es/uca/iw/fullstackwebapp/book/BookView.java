package es.uca.iw.ejemplos.fullstack.book;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("books")
public class BookView extends VerticalLayout {

    private final BookService bookService;

    private final Grid<Book> grid = new Grid<>(Book.class);

    public BookView(BookService bookService) {
        this.bookService = bookService;

        buildUI();

    }

    private void buildUI() {
        add(new H1("Libros no publicados"));


        TextField titleFilter = new TextField();


        grid.setItems(bookService.readUnpublishedBooks());

        add(grid);

    }

}
