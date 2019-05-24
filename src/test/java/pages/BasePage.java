package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    public void waitVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public void waitInvisibilily(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void click (By locator) {
        waitVisibility(locator);
        driver.findElement(locator).click();
    }

    public void writeText (By locator, String text) {
        waitVisibility(locator);
        driver.findElement(locator).sendKeys(text);
    }

    public String getCurrentURL () {
        return driver.getCurrentUrl();
    }

    public void waitUntilStale(By locator) {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(locator)));
    }
}
