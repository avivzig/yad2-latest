package Yad2Tests;

import Yad2Pages.FilterPage;
import Yad2Pages.Pagination;
import Yad2Pages.WebDriverInitializer;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PaginationTest  {

    private WebDriver driver;
    private Pagination testcases;
    private FilterPage filter;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverInitializer.initializeChromeDriver();
        testcases = new Pagination(driver);
        //filter = new FilterPage((ChromeDriver) driver);

    }



    @Test(priority = 1, description = "paginations compare between 2 element numbers")
    @Description("the test run on 3 pages to validate pagination and total count of items and the count that show to customer")
    public void threePagesListItemTest() throws InterruptedException {
        testcases.TrackPage();
        Thread.sleep(2000);

        testcases.search_tracks_tab();
        Thread.sleep(2000);

        //filter.price_select();
        //Thread.sleep(2000);
        //filter.price_click();
        //Thread.sleep(2000);
        //filter.price_ishrur1();
        //Thread.sleep(2000);




        testcases.paginationMethods1();
        Thread.sleep(2000);
        testcases.clickPageMethod1();
        Thread.sleep(2000);
        testcases.paginationMethods2();
        Thread.sleep(2000);
        testcases.clickPageMethod2();
        Thread.sleep(2000);
        testcases.paginationMethods3();
        Thread.sleep(2000);
        testcases.getTotalCount();
    }
}
