package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;

public class SearchPage extends BasePage {

    private By containerPage = By.id("reactSearchPage");
    private By listItem = By.xpath("//ol/li");

    private String itemXpath = "//ol/li[%d]//header";
    private String titleXpath = "//ol/li[%d]//header//a";

    public SearchPage(WebDriver driver) {
        super(driver);
        isOpened();
    }

    public boolean isOpened() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            waitForModalLoadingSpinner();
            wait.until(ExpectedConditions.visibilityOfElementLocated(containerPage)).isDisplayed();
            wait.until(ExpectedConditions.visibilityOfElementLocated(listItem)).isDisplayed();
            driver.findElement(By.id("occupancy-box")).click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTitleItemByIndex(int index) {
        By currentItemTitle = By.xpath(String.format(titleXpath, index));
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(currentItemTitle)).getText();
        return title;
    }

    public void clickItemByIndex(int index) {
        By currentItem = By.xpath(String.format(itemXpath, index));
        wait.until(ExpectedConditions.elementToBeClickable(currentItem)).click();
    }
}
