package Yad2Tests;

import Yad2Pages.FilterPage;
import Yad2Pages.Pagination;
import Yad2Pages.TruckDetailsPage;
import Yad2Pages.WebDriverInitializer;
import io.qameta.allure.Description;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TruckDetailsInsideTabTest {

    private ChromeDriver driver;
    private WebDriverWait wait;
    private Pagination testcases;
    private FilterPage filter;
    private TruckDetailsPage truckDetailsPage;

    String parentWindowHandle;

    TruckDetailsPage truckloop;




    @BeforeSuite
    public void setup() {

        driver = (ChromeDriver) WebDriverInitializer.initializeChromeDriver();
        wait = new WebDriverWait(driver, 20);
        parentWindowHandle = driver.getWindowHandle();
        testcases = new Pagination(driver);
        filter = new FilterPage(driver);
        //truckDetailsPage = new TruckDetailsPage(driver, wait);
        truckloop = new TruckDetailsPage(driver,wait);
    }

    // Your test methods here


    @Test(priority = 1, description = "getting to the trucks page with all previus filters")
    @Description("after setup the yad2 URL , getting to the point previus Test : FilterPageTest stoped")
    public void gettingToTheTrackPageFlow() throws InterruptedException {

        testcases.TrackPage();

        filter.price_select();
        Thread.sleep(2000);
        filter.price_click();
        Thread.sleep(2000);
        filter.price_ishrur1();
        Thread.sleep(2000);

        testcases.search_tracks_tab();
        Thread.sleep(2000);
        //filter.press_on_filter();
        //filter.press_zol_to_yakar();
        //Thread.sleep(2000);
    }

    @Test(priority = 2, description = "extract elements of trucks before and after clicking on each tab",dependsOnMethods = "gettingToTheTrackPageFlow")
    @Description("after getting to the point of previus test: FilterPageTest , listing all the 3 first track elements before clicking on" +
            "each tab of truck and after that  getting inside every truck details and asserting the details of truck before clicking and after  ")
    public void findTrucks() {

    //the loop method
        truckloop.findTrucks1();

    }

    @AfterSuite
    public void tearDown() {
        // Close the WebDriver instance after test execution
        driver.quit();
    }

}
