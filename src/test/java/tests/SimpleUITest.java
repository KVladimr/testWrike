package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Footer;
import pages.HomePage;
import pages.ResendPage;

import java.util.Random;

public class SimpleUITest {

    private WebDriver driver;

    @BeforeClass
    public static void createDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void simpleTest() {
        String email = randomString(4, 15) + "+wpt@wriketask.qaa";

        HomePage homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickHeaderGetStartedButton();
        homePage.fillEmailField(email);

        String beforeURL = homePage.getCurrentURL();
        ResendPage resendPage = homePage.submitEmailWithSuccess();
        Assert.assertNotEquals(beforeURL, resendPage.getCurrentURL());

        resendPage.fillQAFormWithRandomAnswers();
        resendPage.clickSubmitQAFormButton();
        Assert.assertTrue(resendPage.isSubmitSuccessful());
        resendPage.clickOnResendEmailButton();
        Assert.assertTrue(resendPage.verifyResendButtonDisappeared());

        Footer footer = new Footer(driver);
        Assert.assertTrue(footer.isTwitterButtonPresent());
    }

    private String randomString(int minLength, int maxLength) {
        Random random = new Random();
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        int length = random.nextInt(maxLength - minLength + 1) + minLength;
        StringBuilder sb = new StringBuilder(length);
        int index;
        for (int i = 0; i < length; i++) {
            index = random.nextInt(alphaNumericString.length());
            sb.append(alphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
