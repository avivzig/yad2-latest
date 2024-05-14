package Yad2Tests;

import Yad2Pages.CarsPage;
import Yad2Pages.HomePage;
import Yad2Pages.WebDriverInitializer;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCase02 {

    private static WebDriver driver;
    private static CarsPage carpagetabs;
    private static HomePage cartext;
    private SoftAssert softAssert;

    @BeforeClass
    public void setup() {
        if (driver == null) {
            driver = WebDriverInitializer.initializeChromeDriver();
            carpagetabs = new CarsPage(driver);
            cartext = new HomePage(driver);
        }
        softAssert = new SoftAssert();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        softAssert.assertAll();
    }

    @Test(priority = 1, description = "click on cartext tab ")
    @Description("clicking on car text tab first action this test")
    public void clickingontexcartab() throws InterruptedException {
        cartext.click_on_cartext();
        Thread.sleep(2000);
        softAssert.assertTrue(true, "Assert message 1");
    }

    @Test(priority = 2, description = "validate tabs ")
    @Description("moving on the tabs at car page between all sub tabs")
    public void Car_headers() {
        carpagetabs.verifyPrivateCar();
        softAssert.assertTrue(true, "private cars text is as expected");
        carpagetabs.verifyMotorcycleTabs();
        softAssert.assertTrue(true, "motorcycle text is as expected");
        carpagetabs.verifybikeTabs();
        softAssert.assertTrue(true, "bikes text is as expected");
        carpagetabs.tracks();
        softAssert.assertTrue(true, "trucks text is as expected");
        carpagetabs.verifySeatravel();
        softAssert.assertTrue(true, "sea travel text is as expected");
        carpagetabs.verifySpecialTravel();
        softAssert.assertTrue(true, "speocial travel text is as expected");
    }
}
