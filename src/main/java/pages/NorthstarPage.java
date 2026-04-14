package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NorthstarPage extends BasePage {
    private By containerPage = By.className("Northstar");
    private By titleHotel = By.xpath("//div[@class='HeaderCerebrum']//h1");
    private By priceContainer = By.xpath("//div[@id='hotelNavBar']//div[@class='StickyNavPrice']");

    public NorthstarPage(WebDriver driver) {
        super(driver);
        isOpened();
    }

    public boolean isOpened() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            waitForModalLoadingSpinner();
            pressEscKey();
            return wait.until(ExpectedConditions.visibilityOfElementLocated(containerPage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getTitleHotel() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(titleHotel)).getText();
    }

    public String getPriceHotel() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(priceContainer)).getText();
    }

}
