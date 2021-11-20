package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }

    public boolean assertElementPresent(By by){
        return driver.findElements(by).size() > 0;
    }

    public String getAttributeFromElement(By by, String attribute){
        WebElement element = waitForElementPresent(by);
        return element.getAttribute(attribute);
    }

    public void compareThatActualValueMoreThanExpected(By by, int expectedValue){
        int actualValue = driver.findElements(by).size();
        Assert.assertTrue("actual value less than expected", actualValue > expectedValue);
    }

    public void assertAllElementContainText(By by, String text){
        List<WebElement> elements = driver.findElements(by);
        for (WebElement element: elements){
            Assert.assertTrue("Element '" + element.getText() + "' not contain '" + text + "'",
                    element.getText().contains(text));
        }
    }

    public void assertElementHasText(By by, String expectedText, String errorMessage){
        WebElement element = waitForElementPresent(by);
        String actualText = element.getText();
        Assert.assertEquals(errorMessage, expectedText, actualText);
    }

    public WebElement waitForElementPresent(By by, long timeoutInSec){

        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSec);
        webDriverWait.withMessage("Element '" + by.toString() + "' not present on the page" + "\n");
        return webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public void waitForElementAndClear(By by){
        WebElement element = waitForElementPresent(by);
        element.clear();
    }

    public boolean waitForElementNotPresent(By by, long timeoutInSec){

        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSec);
        webDriverWait.withMessage("Element '" + by.toString() + "' present on the page" + "\n");
        return webDriverWait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(By by){

        return waitForElementPresent(by, 5);
    }

    public void waitForElementAndClick(By by){
        WebElement element = waitForElementPresent(by);
        element.click();
    }

    public void waitForElementAndSendKeys(By by, String value){
        WebElement element = waitForElementPresent(by);
        element.sendKeys(value);
    }

    public void quickSwipeUp(){
        swipeUp(200);
    }

    public void swipeUp(int swipeTime){
        Dimension dimension = driver.manage().window().getSize();
        int middleX = dimension.width / 2;
        int startY = (int) (dimension.height * 0.8);
        int endY = (int) (dimension.height * 0.2);

        TouchAction touchAction = new TouchAction(driver);
        touchAction
                .press(middleX, startY)
                .waitAction(swipeTime)
                .moveTo(middleX, endY)
                .release()
                .perform();
    }

    public void swipeElementToLeft(By by){
        WebElement element = waitForElementPresent(by);
        // самая левая точка элемента
        int leftX = element.getLocation().getX();
        //правая точка элемента = левая точка + ширина элемента
        int rightX = leftX + element.getSize().getWidth();
        //самая верхняя координата по ОУ
        int upperY = element.getLocation().getY();
        //нижняя координата по ОУ = верхняя + высота элемента
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction touchAction = new TouchAction(driver);
        touchAction
                .press(rightX, middleY)
                .waitAction(250)
                .moveTo(leftX, middleY)
                .release()
                .perform();
    }

    public String replaceTemplate(String str, String template, String replacedValue){
        return str.replace(template, replacedValue);
    }
}
