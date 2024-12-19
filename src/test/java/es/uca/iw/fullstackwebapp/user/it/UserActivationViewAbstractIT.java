package es.uca.iw.fullstackwebapp.user.it;

import es.uca.iw.fullstackwebapp.user.ObjectMother;
import es.uca.iw.fullstackwebapp.user.domain.User;
import es.uca.iw.fullstackwebapp.user.services.UserManagementService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author ivanruizrube
 */

public abstract class UserActivationViewAbstractIT {
    private final String uribase = "http://127.0.0.1:";
    protected WebDriver driver;
    @LocalServerPort
    private int port;
    private User testUser;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Autowired
    protected abstract UserManagementService getService();

    @BeforeEach
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
//        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--window-size=1920,1200");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        testUser = ObjectMother.createTestUser();
    }


    @Test
    public void shouldNotActivateANoExistingUser() {

        // Given
        // a certain user

        // When point the browser to the activation page
        driver.get(uribase + port + "/useractivation");

        // and introduce form data
        driver.findElement(By.id("email")).sendKeys(testUser.getEmail());
        driver.findElement(By.id("secretCode")).sendKeys("key");

        // and press the activate button
        driver.findElement(By.id("activate")).click();

        // Then, the label is the following...
        WebElement element = driver.findElement(By.id("status"));
        assertThat(element.getText().equals("Ups. The user could not be activated")).isTrue();

        // Podemos validar número de llamadas al servicio activar usuario con ..
        //verify(userManagementService, times(NUMBER)).activateUser(anyString(), anyString());

    }


    @Test
    public void shouldActivateAnExistingUser() {

        // Given
        // a certain user
        //who is registered
        getService().registerUser(testUser);

        // When point the browser to the activation page
        driver.get(uribase + port + "/useractivation");

        // and introduce form data
        driver.findElement(By.id("email")).sendKeys(testUser.getEmail());
        driver.findElement(By.id("secretCode")).sendKeys(testUser.getRegisterCode());

        // and press the activate button
        driver.findElement(By.id("activate")).click();

        // Then, the label is the following...
        WebElement element = driver.findElement(By.id("status"));
        assertThat(element.getText().equals("Congrats. The user has been activated")).isTrue();

    }
}
