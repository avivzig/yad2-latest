package Yad2Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TruckDetailsPage {



    private WebDriver driver;
    private WebDriverWait wait;
    String parentWindowHandle;

    private SoftAssert softAssert;



    public TruckDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.parentWindowHandle = driver.getWindowHandle(); // Initialize parent window handle
        this.softAssert = new SoftAssert();

    }





    //list elements before  click on truck "find elements" :
    protected By trackName1 = By.xpath("//span[@class='feed-item-info_heading__k5pVC']");
    protected By text1 = By.xpath("//span[@class='feed-item-info_marketingText__eNE4R']");
    protected By yeaR1 = By.xpath("//span[@class='feed-item-info_yearAndHandBox___JLbc']");
    protected By priCe1 = By.xpath("//span[@class='price_price__xQt90' and @data-testid='price' and not(parent::span[@data-testid='price-dropped-flag'])]");
    protected By haNd1 = By.xpath("//span[@class='feed-item-info_yearAndHandBox___JLbc']");


    //after click on truck elements:
    protected By trackName = By.xpath("//*[@id=\"__next\"]/div[3]/main/div[2]/div/div[1]/h1");

    protected By text = By.xpath("//*[@id=\"__next\"]/div[3]/main/div[2]/div/div[1]/h2");
    protected By yeaR = By.xpath("//*[@id=\"__next\"]/div[3]/main/div[2]/div/div[1]/div[2]/div[1]/span");
    protected By priCe = By.xpath("//*[@id=\"__next\"]/div[3]/main/div[2]/aside/div/div[1]/div[1]/div[1]/span");

    protected By haNd = By.xpath("//*[@id=\"__next\"]/div[3]/main/div[2]/div/div[1]/div[2]/div[2]/span[2]");




    public void findTrucks1() {
        // Find all elements with the class 'feed-item-info_heading__k5pVC'
        List<WebElement> truckElements = driver.findElements(trackName1);
        // Find all elements with the class 'feed-item-info_marketingText__eNE4R'
        List<WebElement> marketingTextElements = driver.findElements(text1);

        List<WebElement> yearConditionElement = driver.findElements(yeaR1);

        List<WebElement> priceElement = driver.findElements(priCe1);

        List<WebElement> carHand = driver.findElements(haNd1);

        // Iterate through each truck
        int numTrucksToProcess = Math.min(6, truckElements.size());
        for (int i = 0; i < numTrucksToProcess; i++) {
            WebElement truck = truckElements.get(i);
            WebElement marketingTextElement = marketingTextElements.get(i);
            WebElement year = yearConditionElement.get(i);
            WebElement price = priceElement.get(i);
            WebElement hand = carHand.get(i);

            // Print truck elements and store the text of the first element
            String beforeMakeModel = truck.getText();
            System.out.println("Truck make: " + beforeMakeModel);

            // Print marketing text elements and store their text
            String beforeDescription = marketingTextElement.getText();
            System.out.println("Marketing Text: " + beforeDescription);

            String beforeYearCondition = year.getText().split(" ")[0];
            System.out.println("year Text: " + beforeYearCondition);

            String beforePrice = price.getText();
            System.out.println("price Text: " + beforePrice);

            String[] splitText = hand.getText().split(" ");
            String beforehand = splitText[splitText.length - 1];
            System.out.println("hand Text: " + beforehand);

            // Wait until the truck element is clickable
            wait.until(ExpectedConditions.elementToBeClickable(truck));

            // Click on the truck
            truck.click();

            // Switch to the new window
            switchToNewWindow();

            // Wait for the page to load completely
            waitForPageToLoad();

            // Get the text of the element after clicking
            WebElement makeModelElement = driver.findElement(trackName);
            String afterMakeModel = makeModelElement.getText();
            softAssert.assertEquals(beforeMakeModel, afterMakeModel,"Make model mismatch");

            // Get the text of the description element before clicking
            WebElement descriptionElement = driver.findElement(text);
            String afterDescription = descriptionElement.getText();
            softAssert.assertEquals(beforeDescription, afterDescription,"description model mismatch");

            WebElement yearConditionElement1 = driver.findElement(yeaR);
            String afterYearCondition = yearConditionElement1.getText();
            softAssert.assertEquals(beforeYearCondition, afterYearCondition,"year model mismatch");

            WebElement priceElement1 = driver.findElement(priCe);
            String afterPrice = priceElement1.getText();
            softAssert.assertEquals(beforePrice, afterPrice,"price mismatch");

            WebElement handElement1 = driver.findElement(haNd);
            String afterhand = handElement1.getText();
            softAssert.assertEquals(beforehand, afterhand,"hand mismatch");

            // Close the current window and switch back to the parent window
            closeAndSwitchToParentWindow();

            // Scroll the page
            scrollPage();

            // Add a wait to stabilize the test
            waitForPageToStabilize();
        }

        softAssert.assertAll();
    }



    private void switchToNewWindow() {
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    private void waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(trackName));

    }

    private void closeAndSwitchToParentWindow() {
        driver.close();
        driver.switchTo().window(parentWindowHandle);
    }

    private void scrollPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 200)");
    }

    private void waitForPageToStabilize() {
        // Add code to wait for specific conditions indicating that the page has stabilized
        // For example, wait for a loading spinner to disappear or for a certain element to be present
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loading-spinner")));
    }

}

