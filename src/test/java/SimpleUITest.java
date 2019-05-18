import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SimpleUITest {

    private WebDriver driver;

    @BeforeClass
    public static void createDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void simpleTest() {
        HomePage homePage = new HomePage(driver);
        String email = randomString(4, 15) + "+wpt@wriketask.qaa";

        homePage.openHomePage()
                .clickHeaderGetStartedButton()
                .fillAndSubmitEmail(email)
                .fillQAFormWithRandomAnswers()
                .submitQAForm()
                .clickOnResendEmailButton()
                .checkTwitterButton();
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
