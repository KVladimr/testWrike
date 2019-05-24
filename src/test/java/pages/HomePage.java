package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final String homeURL = "https://www.wrike.com/";

    private By getStartedButtonInHeader = By.xpath("//div[@class='r']//button[contains(text(),'Get started')]");
    private By emailField = By.xpath("//label[@class='modal-form-trial__label']/input");
    private By createAccountButton = By.xpath("//label[@class='modal-form-trial__label']/button");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step(value = "Go to Wrike.com")
    public void openHomePage() {
        driver.get(homeURL);
    }

    @Step(value = "Click \"Get started for free\" button near \"Login\" button")
    public void clickHeaderGetStartedButton() {
        click(getStartedButtonInHeader);
    }

    @Step(value = "Fill in the email field")
    public void fillEmailField(String email) {
        writeText(emailField, email);
    }

    @Step(value = "Click on \"Create my Wrike account\" button")
    public ResendPage submitEmailWithSuccess() {
        click(createAccountButton);
        waitUntilStale(createAccountButton);
        return new ResendPage(driver);
    }
}
