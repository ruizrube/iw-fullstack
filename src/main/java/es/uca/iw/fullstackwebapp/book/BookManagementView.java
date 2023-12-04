package es.uca.iw.fullstackwebapp.book;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import es.uca.iw.fullstackwebapp.MainLayout;
import jakarta.annotation.security.RolesAllowed;


@Route(value = "bookmanagement", layout = MainLayout.class)
@RolesAllowed("ADMIN")
@PageTitle("Book Management")
public class BookManagementView extends VerticalLayout {

    private final BookService bookService;


    public BookManagementView(BookService bookService) {
        this.bookService = bookService;

        buildUI();

    }

    private void buildUI() {
        add(new H1("TO DO"));
        add(new H2("Vista de gestión de libros"));
    }


}
