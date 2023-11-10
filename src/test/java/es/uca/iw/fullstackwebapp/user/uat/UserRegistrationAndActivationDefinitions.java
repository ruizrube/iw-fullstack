package es.uca.iw.fullstackwebapp.user.uat;

import es.uca.iw.fullstackwebapp.user.domain.User;
import es.uca.iw.fullstackwebapp.user.services.UserManagementService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//La etiqueta @Transactional no funciona con selenium y hay que restaurar manualmente el estado de la BD
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserRegistrationAndActivationDefinitions {

    private final String uribase = "http://localhost:";
    @LocalServerPort
    private int port;
    private WebDriver driver;

    @Autowired
    private UserManagementService userManagementService;

    private User testUser;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }

        if (testUser != null && testUser.getId() != null) {
            userManagementService.delete(testUser);
        }

    }

    @Given("An user with name {string} is registered on the system")
    public void an_user_is_registered_on_the_system(String username) {

        testUser = userManagementService.loadUserByUsername(username).get();

    }

    @When("The user {string} with email {string} and password {string} registers on the app")
    public void registerUser(String username, String email, String password) {

        // Given
        // a certain user
        testUser = new User();
        testUser.setUsername(username);
        testUser.setEmail(email);
        testUser.setPassword(password);

        // When

        // point the browser to the activation page
        driver.get(uribase + port + "/userregistration");

        // and introduce form data
        driver.findElement(By.id("email")).sendKeys(testUser.getEmail());
        driver.findElement(By.id("username")).sendKeys(testUser.getUsername());
        driver.findElement(By.id("password")).sendKeys(testUser.getPassword());
        driver.findElement(By.id("password2")).sendKeys(testUser.getPassword());


        // and press the activate button
        driver.findElement(By.id("register")).click();

        // Then
        WebElement element = driver.findElement(By.id("status"));

        assertThat(element.getText().equals("Great. Please look at your mail inbox!")).isTrue();

    }

    @When("The user {string} introduces their email {string} and a verification code to activate")
    public void the_user_introduces_their_email_and_a_verification_code(String username, String email) {

        // HTTP web invocation
        driver.get(uribase + port + "/useractivation");

        // user interaction
        driver.findElement(By.id("email")).sendKeys(email);

        Optional<User> optUser = userManagementService.loadUserByUsername(username);

        if (optUser.isPresent()) {
            testUser = optUser.get();
            driver.findElement(By.id("secretCode")).sendKeys(testUser.getRegisterCode());
        } else {
            driver.findElement(By.id("secretCode")).sendKeys("randomkey");
        }

        driver.findElement(By.id("activate")).click();

    }

    @Then("The user gets a message with the text {string}")
    public void the_user_gets_a_message_with_the_text(String text) {
        // Assertion

        WebElement element = driver.findElement(By.id("status"));

        assertThat(element.getText().equals(text)).isTrue();

    }

}