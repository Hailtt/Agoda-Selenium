package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExtentReportManager;

public class NorthstarPage extends BasePage {


    private By containerPage = By.className("Northstar");
    private By titleHotel = By.xpath("//div[@class='HeaderCerebrum']//h1");
    private By priceContainer = By.xpath("//div[@id='hotelNavBar']//div[@class='StickyNavPrice']");

    public NorthstarPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpened() {

        ExtentReportManager.logInfo("Checking if NorthstarPage is opened");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForModalLoadingSpinner();
        boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(containerPage)).isDisplayed();

        ExtentReportManager.logInfo("NorthstarPage opened: " + isDisplayed);
        return isDisplayed;
    }

    public String getTitleHotel() {
        String title = wait.until(ExpectedConditions.visibilityOfElementLocated(titleHotel)).getText();

        return title;
    }

    public String getPriceHotel() {
        String price = wait.until(ExpectedConditions.visibilityOfElementLocated(priceContainer)).getText();

        return price;
    }

}
