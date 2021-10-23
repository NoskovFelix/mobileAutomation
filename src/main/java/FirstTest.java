
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "11");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/felixnv/Desktop/mobileCources/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown(){
        driver.quit();
    }


    @Test
    public void testCheckContainsThatSearchFieldHasText(){

        assertElementHasText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']"),
                "Search Wikipedia",
                "text 'Search Wikipedia' not present in the search field"
        );
    }

    @Test
    public void testCheckThatResultsAreGone(){

        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']"));

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java");

        compareThatActualValueMoreThanExpected(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"), 1);

        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"));

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
                "element displayed on the page",
                5);
    }



    private void compareThatActualValueMoreThanExpected(By by, int expectedValue){
        int actualValue = driver.findElements(by).size();
        Assert.assertTrue("actual value less than expected", actualValue > expectedValue);
    }


    private void assertElementHasText(By by, String expectedText, String errorMessage){
        WebElement element = waitForElementPresent(by);
        String actualText = element.getText();
        Assert.assertEquals(errorMessage, expectedText, actualText);
    }

    private WebElement waitForElementPresent(By by, long timeoutInSec){

        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSec);
        webDriverWait.withMessage("Element '" + by.toString() + "' not present on the page" + "\n");
        return webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSec){

        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSec);
        webDriverWait.withMessage("Element '" + by.toString() + "' present on the page" + "\n");
        return webDriverWait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by){

        return waitForElementPresent(by, 5);
    }

    private void waitForElementAndClick(By by){
        WebElement element = waitForElementPresent(by);
        element.click();
    }

    private void waitForElementAndSendKeys(By by, String value){
        WebElement element = waitForElementPresent(by);
        element.sendKeys(value);
    }


}
