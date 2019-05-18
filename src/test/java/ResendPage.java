import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
            //chosenAnswer = answers.get(answers.size() - 1);
            chosenAnswer.click();

            if (chosenAnswer.findElement(By.xpath("./..")).getAttribute("class").contains("switch--text")) {
                chosenAnswer.sendKeys("random comment");
            }
        }
        return this;
    }

    @Step
    public ResendPage submitQAForm() {
        driver.findElement(By.xpath("//form[@name='survey-form']//button[text()='Submit results']")).click();
        return this;
    }

    @Step
    public ResendPage clickOnResendEmailButton() {
        // There are two identical buttons and I don't know how to choose the one I need
        // That's why I just take the last one
        driver.findElement((By.xpath("(//button[text()='Resend email'])[last()]"))).click();
        return this;
    }

    @Step
    public ResendPage checkTwitterButton() {
        Assert.assertTrue(twitterButtonExists());
        return this;
    }

    private boolean twitterButtonExists() {
        final String correctURL = "https://twitter.com/wrike";
        List<WebElement> buttons = driver.findElements(By.xpath("//*[text()='Follow us']/following-sibling::ul/li"));
        for (WebElement button : buttons) {
            WebElement linkElement = button.findElement(By.xpath("./a"));
            String link = linkElement.getAttribute("href");

            if (link.equals(correctURL)) {
                return true;
            }
        }
        return false;
    }
}
