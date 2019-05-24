package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Footer extends BasePage {

    private By socialLinks = By.xpath("//*[text()='Follow us']/following-sibling::ul//a");

    public Footer(WebDriver driver) {
        super(driver);
    }

    @Step(value = "Check that section \"Follow us\" at the site footer contains the \"Twitter\" button")
    public boolean isTwitterButtonPresent() {
        return isSocialButtonPresent(
                "https://twitter.com/wrike",
                "/content/themes/wrike/dist/img/sprite/vector/footer-icons.symbol.svg?v2#twitter");
    }

    // checks if there is a social button with the correct link and icon
    private boolean isSocialButtonPresent(String link, String icon) {
        List<WebElement> buttons = driver.findElements(socialLinks);
        for (WebElement button : buttons) {
            String url = button.getAttribute("href");
            // check if there is a button with correct link
            if (url.equals(link)) {
                // there is a button with the correct link, so check its icon
                String iconPath = button
                        .findElement(By.xpath(".//*[local-name()='use']"))
                        .getAttribute("xlink:href");
                if (iconPath.equals(icon)) {
                    return true;
                }
            }
        }
        return false;
    }
}
