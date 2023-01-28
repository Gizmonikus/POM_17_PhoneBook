import config.AppiumConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class RemoveContactsTests extends AppiumConfig {

    @BeforeClass
    public void preCondition(){
        new SplashScreen(driver)
                .goToAuthenticationScreen()
                .fillEmail("bobinsan21@yandex.ru")
                .fillPassword("Bob12345@")
                .submitLogin();
    }

    @Test
    public void removeOneContactPositive(){
        new ContactListScreen(driver).removeOneContact();
    }

}
