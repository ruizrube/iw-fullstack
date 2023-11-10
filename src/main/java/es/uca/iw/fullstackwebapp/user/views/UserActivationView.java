package es.uca.iw.fullstackwebapp.user;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@PageTitle("Activate User")
@Route(value = "useractivation")
@Component // Required for unit testing
@Scope("prototype") // Required for IT testing
public class UserActivationView extends VerticalLayout {

    private static final long serialVersionUID = 851217309689685413L;

    private final UserService service;
    private final H1 title;
    private final TextField email;
    private final TextField secretCode;
    private final Button activate;
    private final H4 status;

    public UserActivationView(UserService service) {
        this.service = service;

        title = new H1("Activate User");
        email = new TextField("Your email");
        email.setId("email");

        secretCode = new TextField("Your secret code");
        secretCode.setId("secretCode");

        status = new H4();
        status.setId("status");

        activate = new Button("Activate");
        activate.setId("activate");

        status.setVisible(false);

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
