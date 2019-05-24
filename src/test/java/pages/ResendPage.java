package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ResendPage extends BasePage {

    private By formQA = By.xpath("//form[@name='survey-form']//div");
    private By successfulSubmitLabel = By.xpath("//div[@class='survey-success']");
    private By submitResultsButton = By.xpath("//form[@name='survey-form']//button[text()='Submit results']");

    // There are two identical buttons and I don't know how to choose the one I need
    // So, I just take the last one
    private By resendEmailButton = By.xpath("(//button[text()='Resend email'])[last()]");

    public ResendPage(WebDriver driver) {
        super(driver);
    }

    @Step(value = "Fill in the Q&A section with random answers")
    public void fillQAFormWithRandomAnswers() {
        List<WebElement> questions = driver.findElements(formQA);
        Random random = new Random();
        List<WebElement> answers;
        WebElement chosenAnswer;
        for (WebElement question : questions) {
            answers = question.findElements(By.xpath(".//button"));
            chosenAnswer = answers.get(random.nextInt(answers.size()));
            chosenAnswer.click();

            if (chosenAnswer.findElement(By.xpath("./..")).getAttribute("class").contains("switch--text")) {
                chosenAnswer.sendKeys("random comment");
            }
        }
    }

    @Step(value = "Submit Q&A form")
    public void clickSubmitQAFormButton() {
        click(submitResultsButton);
    }

    @Step(value = "Check form submission")
    public boolean isSubmitSuccessful() {
        waitVisibility(successfulSubmitLabel);
        return driver.findElement(successfulSubmitLabel).isDisplayed();
    }

    @Step(value = "Click on \"Resend email\" button")
    public void clickOnResendEmailButton() {
        click(resendEmailButton);
    }

    @Step(value = "Check disappearance of \"Resend email\" button")
    public boolean verifyResendButtonDisappeared() {
        waitInvisibilily(resendEmailButton);
        return !driver.findElement(resendEmailButton).isDisplayed();
    }
}
