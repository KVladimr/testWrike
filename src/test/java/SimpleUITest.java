import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SimpleUITest {

    private WebDriver driver;

    @BeforeClass
    public static void createDriver() {

    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void simpleTest() {
        HomePage homePage = new HomePage(driver);
        String email = randomString() + "+wpt@wriketask.qaa";

        homePage.openHomePage()
                .clickHeaderGetStartedButton()
                .fillAndSubmitEmail(email)
                .fillQAFormWithRandomAnswers()
                .submitQAForm()
                .clickOnResendEmailButton()
                .checkTwitterButton();

        try{
            Thread.sleep(5000);
        } catch (Exception e) {}
    }

    private String randomString() {
        Random random = new Random();
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        // length of string is from 4 to 15
        int length = random.nextInt(12) + 4;
        StringBuilder sb = new StringBuilder(length);
        int index;
        for (int i = 0; i < length; i++) {
            index = random.nextInt(alphaNumericString.length());
            sb.append(alphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
