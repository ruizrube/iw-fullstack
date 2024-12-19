package es.uca.iw.fullstackwebapp.user.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import es.uca.iw.fullstackwebapp.user.services.UserManagementService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serial;

@PageTitle("Activate User")
@Route(value = "useractivation")
@Component // Required for unit testing
@Scope("prototype") // Required for IT testing
@AnonymousAllowed
public class UserActivationView extends VerticalLayout {

    @Serial
    private static final long serialVersionUID = 851217309689685413L;

    private final UserManagementService service;
    private final H1 title;
    private final TextField email;
    private final TextField secretCode;
    private final Button activate;
    private final H4 status;
    private final RouterLink login;

    public UserActivationView(UserManagementService service) {
        this.service = service;

        title = new H1("Activate User");
        email = new TextField("Your email");
        email.setId("email");

        secretCode = new TextField("Your secret code");
        secretCode.setId("secretCode");

        status = new H4();
        status.setId("status");
        status.setVisible(false);

        login=new RouterLink("Log in", UserHomeView.class);
        login.setId("login");
        login.setVisible(false);

        activate = new Button("Activate");
        activate.setId("activate");


        setMargin(true);

        add(title, new HorizontalLayout(email, secretCode), activate, status);

        activate.addClickListener(e -> onActivateButtonClick());
    }

    /**
     * Handler
     */
    public void onActivateButtonClick() {

        status.setVisible(true);

        if (service.activateUser(email.getValue(), secretCode.getValue())) {
            status.setText("Congrats. The user has been activated");
            login.setVisible(true);


        } else {
            status.setText("Ups. The user could not be activated");
        }

    }

    public void setEmail(String email) {
        this.email.setValue(email);

    }

    public void setSecretCode(String secretCode) {
        this.secretCode.setValue(secretCode);
    }

    public String getStatus() {
        return status.getText();
    }

}
