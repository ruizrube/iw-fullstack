package es.uca.iw.fullstackwebapp.book;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;


@Route("books")
public class BookView extends VerticalLayout {

    private final BookService bookService;

    private final Grid<Book> grid = new Grid<>(Book.class);

    private TextField titleFilter;

    public BookView(BookService bookService) {
        this.bookService = bookService;

        buildUI();

    }

    private void buildUI() {
        add(new H1("Libros no publicados"));

        titleFilter = new TextField("Filtrar por título");
        titleFilter.setValueChangeMode(ValueChangeMode.EAGER);
        titleFilter.addValueChangeListener(e -> showFilteredData());


        grid.setItems(bookService.readUnpublishedBooks());


        add(titleFilter, grid);

    }

    private void showFilteredData() {
        grid.setItems(bookService.readUnpublishedBooksByTitle(titleFilter.getValue()));

    }

}
