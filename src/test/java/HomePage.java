import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

    private static final String homeURL = "https://www.wrike.com/";

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step(value = "Go to Wrike.com")
    public HomePage openHomePage() {
        driver.get(homeURL);
        return this;
    }

    @Step(value = "Click \"Get started for free\" button near \"Login\" button")
    public HomePage clickHeaderGetStartedButton() {
        driver.findElement(By.xpath("//div[@class='r']//button[contains(text(),'Get started')]")).click();
        return this;
    }

    @Step(value = "Fill in the email field with random generated value of email " +
            "and Click on \"Create my Wrike account\" button")
    public ResendPage fillAndSubmitEmail(String email) {
        String beforeURL = driver.getCurrentUrl();

        driver.findElement(By.xpath("//label[@class='modal-form-trial__label']/input")).sendKeys(email);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement button = driver.findElement(By.xpath("//label[@class='modal-form-trial__label']/button"));
        button.click();
        wait.until(ExpectedConditions.stalenessOf(button));

        ResendPage resendPage = new ResendPage(driver);

        String afterURL = driver.getCurrentUrl();
        Assert.assertNotEquals(beforeURL, afterURL);

        return resendPage;
    }
}
