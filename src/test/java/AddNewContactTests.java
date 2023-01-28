import config.AppiumConfig;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

import java.util.Random;

public class AddNewContactTests extends AppiumConfig {

    @BeforeClass
    public void preCondition(){
        new SplashScreen(driver)
                .goToAuthenticationScreen()
                .fillEmail("bobinsan21@yandex.ru")
                .fillPassword("Bob12345@")
                .submitLogin();
    }

    @Test
    public void addOneContactPositive(){
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Add " + i)
                .lastName("Positive")
                .email("Add_" + i + "@mail.com")
                .phone("123456" + i)
                .address("Haifa")
                .description("Add " + i + " New Positive")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContact()
                .isContactAdded(contact);
    }

    @Test
    public void addOneContactNegativeEmptyPhone(){
        Contact contact = Contact.builder()
                .name("EmptyPhone")
                .lastName("Negative")
                .email("emptyPhone@mail.com")
                //.phone("123456")
                .address("Haifa")
                //.description("Add " + i + " New Positive")
                .build();


        Assert.assertTrue(
                new ContactListScreen(driver)
                        .openContactForm()
                        .fillContactForm(contact)
                        .submitContactNegative()
                        .isErrorMessageContainsText("Error")
        );
    }
    @AfterClass
    public void postCondition(){

    }
}
