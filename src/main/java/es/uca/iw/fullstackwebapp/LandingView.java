package es.uca.iw.fullstackwebapp;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import es.uca.iw.fullstackwebapp.user.views.UserActivationView;
import es.uca.iw.fullstackwebapp.user.views.UserHomeView;
import es.uca.iw.fullstackwebapp.user.views.UserRegistrationView;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

@PageTitle("Inicio")
@Route(value = "")
@AnonymousAllowed
public class LandingView extends VerticalLayout {

    @Value("${app.version}")
    private String appVersion;


    public LandingView() {
        this.setAlignItems(Alignment.CENTER);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        this.setHeightFull();
    }


    @PostConstruct
    public void init() {

        // Este método se ejecuta después de que se ejecute el constructor y se inyecten las dependencias (appVersion)

        add(new H1("Bienvenid@ a la aplicación demo"));
        add(new H2("Ingeniería Web"));
        add(new H3("Versión: " + appVersion));

        RouterLink homeLink = new RouterLink("User Home view", UserHomeView.class);
        add(homeLink);

        RouterLink userRegistrationLink = new RouterLink("User Registration view", UserRegistrationView.class);
        add(userRegistrationLink);

        RouterLink userActivationLink = new RouterLink("User Activation view", UserActivationView.class);
        add(userActivationLink);
    }

}
