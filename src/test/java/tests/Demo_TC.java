package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.HomePage;
import pages.NorthstarPage;
import pages.SearchPage;
import utils.DateTimeUtils;

public class Demo_TC extends BaseTest {

    private HomePage homePage;
    private SearchPage searchPage;
    private NorthstarPage northstarPage;

    private String searchValue = "Muong Thanh Saigon Centre Hotel";
    private String formatDayPicker = "MMM dd yyyy";
    private String formatCheckingDay = "dd MMM yyyy";
    private int startDayFromToday = 2;
    private int endDayFromToday = 3;
    private String titleFirstItem;

    @BeforeMethod
    void init() {
        homePage = new HomePage(this.driver);
    }

    @Test(description = "Verify price display for specific hotel search")
    public void verifyHotelPriceDisplay() {

        // STEP 1: Input into Search Box
        homePage.searchHotel(searchValue);

        // STEP 2: Select CheckInDay and CheckOutDay
        selecAndVerifyDayPicker();

        // STEP 3: Change value of Adults
        selecAndVerifyAdult();

        // STEP 4: Click Srearch button and observe searchPage is opened
        searchPage = homePage.clickSearchButton();

        // STEP 5: Click on the first available option
        clickOnTheFirstItem();

        // STEP 6: Switch new tab and verify Title and Price of hotel has been displayed
        switchToNewTabAndVerify();
    }

    private void selecAndVerifyDayPicker() {
        String startDateStr = DateTimeUtils.getTargetDate(startDayFromToday, formatDayPicker);
        String endDateStr = DateTimeUtils.getTargetDate(endDayFromToday, formatDayPicker);
        homePage.selectDayPicker(startDateStr, endDateStr);

        // Format Day
        startDateStr = DateTimeUtils.formatStringDate(startDateStr, formatDayPicker, formatCheckingDay);
        endDateStr = DateTimeUtils.formatStringDate(endDateStr, formatDayPicker, formatCheckingDay);

        String actualStartDaypicker = homePage.getTextCheckInDayPicker();
        String actualEndDaypicker = homePage.getTextCheckOutDayPicker();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(actualStartDaypicker.contains(startDateStr), "Check-in date is WRONG!");
        softAssert.assertTrue(actualEndDaypicker.contains(endDateStr), "Check-out date is WRONG!");
        softAssert.assertAll();
    }

    private void selecAndVerifyAdult() {
        homePage.openOccupancyBox();
        homePage.setOccupancyQuantity(1, 1);
        homePage.setOccupancyQuantity(2, 4);
        homePage.setOccupancyQuantity(3, 2);
        homePage.pressEscKey();

        String actualOccupancy = homePage.getTextOccupancyBox();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(actualOccupancy.contains("1 room"), "Room is WRONG!");
        softAssert.assertTrue(actualOccupancy.contains("4 adults"), "Adults is WRONG!");
        softAssert.assertTrue(actualOccupancy.contains("2 children"), "Children is WRONG!");
        softAssert.assertAll();
    }

    private void clickOnTheFirstItem() {
        titleFirstItem = searchPage.getTitleItemByIndex(1).trim();
        searchPage.clickItemByIndex(1);
    }

    private void switchToNewTabAndVerify() {
        northstarPage = new NorthstarPage(driver);
        northstarPage.switchToNewTab();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(northstarPage.getTitleHotel().trim().equals(titleFirstItem), "Title Hotel is Wrong");
        softAssert.assertFalse(northstarPage.getPriceHotel().isEmpty(), "Price is Empty");
        softAssert.assertAll();
    }
}
