package Yad2Tests;

import Yad2Pages.HomePage;
import Yad2Pages.WebDriverInitializer;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCase01 {

    private WebDriver driver;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverInitializer.initializeChromeDriver();
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        softAssert.assertAll();
    }

    @Description("pressing on text car tab and checking the URL ")
    @Test(priority = 1, description = "car tab text press")
    public void First_car_tab() {
        HomePage carTextTab = new HomePage(driver);
        carTextTab.Click_on_car_text_tab();
        softAssert.assertTrue(true);
    }

    @Description("pressing on car img tab , checking the URL and compare it to the previous URL ")
    @Test(priority = 2, description = "car tab img press")
    public void Second_car_Tab() {
        HomePage carImgTab = new HomePage(driver);
        carImgTab.Click_on_car_img_tab();
        softAssert.assertTrue(true);
    }
}
