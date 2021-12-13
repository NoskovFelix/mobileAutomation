package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver){
        this.driver = driver;
    }

    public boolean assertElementPresent(By by){
        return driver.findElements(by).size() > 0;
    }

    public String getAttributeFromElement(String locator, String attribute){
        WebElement element = waitForElementPresent(locator);
        return element.getAttribute(attribute);
    }

    public void compareThatActualValueMoreThanExpected(String locator, int expectedValue){
        By by = this.getLocatorByString(locator);
        int actualValue = driver.findElements(by).size();
        Assert.assertTrue("actual value less than expected", actualValue > expectedValue);
    }

    public void assertAllElementContainText(String locator, String text){
        By by = this.getLocatorByString(locator);
        List<WebElement> elements = driver.findElements(by);
        for (WebElement element: elements){
            Assert.assertTrue("Element '" + element.getText() + "' not contain '" + text + "'",
                    element.getText().contains(text));
        }
    }

    public void assertElementHasText(String locator, String expectedText, String errorMessage){
        WebElement element = waitForElementPresent(locator);
        String actualText = element.getText();
        Assert.assertEquals(errorMessage, expectedText, actualText);
    }

    public WebElement waitForElementPresent(String locator, long timeoutInSec){

        By by = this.getLocatorByString(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSec);
        webDriverWait.withMessage("Element '" + by.toString() + "' not present on the page" + "\n");
        return webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public void waitForElementAndClear(String locator){
        WebElement element = waitForElementPresent(locator);
        element.clear();
    }

    public boolean waitForElementNotPresent(String locator, long timeoutInSec){

        By by = this.getLocatorByString(locator);
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSec);
        webDriverWait.withMessage("Element '" + by.toString() + "' present on the page" + "\n");
        return webDriverWait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(String locator){

        return waitForElementPresent(locator, 5);
    }

    public void waitForElementAndClick(String locator){
        WebElement element = waitForElementPresent(locator);
        element.click();
    }

    public void waitForElementAndSendKeys(String locator, String value){
        WebElement element = waitForElementPresent(locator);
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
                .press(PointOption.point(middleX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(swipeTime)))
                .moveTo(PointOption.point(middleX, endY))
                .release()
                .perform();
    }

    public void swipeElementToLeft(String locator){
        WebElement element = waitForElementPresent(locator);
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
                .press(PointOption.point(rightX, middleY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(250)))
                .moveTo(PointOption.point(leftX, middleY))
                .release()
                .perform();
    }

    public String replaceTemplate(String str, String template, String replacedValue){
        return str.replace(template, replacedValue);
    }

    public String replaceTemplates(String str, String[] templates, String[] replacedValues){
        if (templates.length != replacedValues.length)
            throw new RuntimeException("templates size not equals replaces values list size!");

        StringBuilder stringBuilder = new StringBuilder(str);

        for (int i = 0; i < templates.length; i ++) {
            stringBuilder = new StringBuilder(replaceTemplate(stringBuilder.toString(), templates[i], replacedValues[i]));
        }

        return stringBuilder.toString();
    }

    private By getLocatorByString(String locatorWithType){
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String locatorType = explodedLocator[0];
        String locator = explodedLocator[1];

        if (locatorType.equals("xpath"))
            return By.xpath(locator);
        else if (locatorType.equals("id"))
            return By.id(locator);
        else throw new IllegalArgumentException("Cannot get type of locator: " + locatorWithType);
    }

    public WebElement waitForElementByTitleAndDescriptionCore(String locator, String title, String description){
        return waitForElementPresent(replaceTemplates(
                locator,
                new String[]{"{article_title}","{article_description}"},
                new String[]{title, description}
        ));
    }
}
