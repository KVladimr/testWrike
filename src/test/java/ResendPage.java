import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class ResendPage {

    private WebDriver driver;

    public ResendPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public ResendPage fillQAFormWithRandomAnswers() {
        List<WebElement> questions = driver.findElements(By.xpath("//form[@name='survey-form']//div"));
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
        return this;
    }

    @Step
    public ResendPage submitQAForm() {
        // successful submit label
        WebElement successElement = driver.findElement(By.xpath("//div[@class='survey-success']"));

        // click submit button
        driver.findElement(By.xpath("//form[@name='survey-form']//button[text()='Submit results']")).click();

        // 3 seconds (at most) for changing the state of QA form
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOf(successElement));

        Assert.assertTrue(successElement.isDisplayed());
        return this;
    }

    @Step
    public ResendPage clickOnResendEmailButton() {
        // There are two identical buttons and I don't know how to choose the one I need
        // So, I just take the last one
        WebElement resendButton = driver.findElement((By.xpath("(//button[text()='Resend email'])[last()]")));
        resendButton.click();

        // give the button some time to disappear
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.invisibilityOf(resendButton));

        Assert.assertFalse(resendButton.isDisplayed());
        return this;
    }

    @Step
    public ResendPage checkTwitterButton() {
        Assert.assertTrue(twitterButtonExists());
        return this;
    }

    // checks if there is a button with correct twitter link
    // can't get how to check icons :(
    private boolean twitterButtonExists() {
        final String correctURL = "https://twitter.com/wrike";
        List<WebElement> buttons = driver.findElements(By.xpath("//*[text()='Follow us']/following-sibling::ul//a"));
        for (WebElement button : buttons) {
            String url = button.getAttribute("href");
            if (url.equals(correctURL)) {
                return true;
            }
        }
        return false;
    }
}
