package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExtentReportManager;

public class SearchPage extends BasePage {

    private By containerPage = By.id("reactSearchPage");

    private String itemXpath = "//ol/li[%d]//div[@dir='ltr']";
    private String titleXpath = "//ol/li[%d]//header//a";

    public SearchPage(WebDriver driver) {
        super(driver);
        isOpened();
    }

    public boolean isOpened() {

        ExtentReportManager.logInfo("Checking if SearchPage is opened");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForModalLoadingSpinner();
        boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(containerPage))
                .isDisplayed();
        driver.findElement(By.id("occupancy-box")).click();

        ExtentReportManager.logInfo("SearchPage opened: " + isDisplayed);
        return isDisplayed;
    }

    public String getTitleItemByIndex(int index) {

        ExtentReportManager.logInfo("Getting title of hotel at index " + index);
        By currentItemTitle = By.xpath(String.format(titleXpath, index));
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(currentItemTitle)).getText();

        ExtentReportManager.logInfo("Hotel title at index " + index + ": " + title);
        return title;
    }

    public void clickItemByIndex(int index) {

        ExtentReportManager.logInfo("Clicking on hotel at index " + index);
        By currentItem = By.xpath(String.format(itemXpath, index));
        wait.until(ExpectedConditions.elementToBeClickable(currentItem)).click();
    }
}
