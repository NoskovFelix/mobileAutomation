
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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
                5);
    }

    @Test
    public void checkContainWordInSearchTitle(){

        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']"));

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "JAVA");

        assertAllElementContainText(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),"Java");
    }

    @Test
    public void testSaveTwoArticlesInFolder(){

        String folderName = "TestFolder";
        String article_1 = "Java";
        String article_2 = "JavaScript";
        String defaultSearchArticleLocator = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";

        // создаем и добавляем первую статью
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']"));

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java");

        waitForElementAndClick(By.xpath(defaultSearchArticleLocator + "[@text='"+ article_1 + "']"));

        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"));

        waitForElementAndClick(By.xpath("//*[@text = 'Add to reading list']"));
        waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/onboarding_button'][@text = 'GOT IT']"));

        waitForElementAndClear(By.id("org.wikipedia:id/text_input"));

        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), folderName);

        waitForElementAndClick(By.xpath("//android.widget.Button[@text = 'OK']"));
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));

        //добавляем вторую статью
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']"));

        waitForElementAndSendKeys(By.id("org.wikipedia:id/search_src_text"), "Java");

        waitForElementAndClick(By.xpath(defaultSearchArticleLocator + "[@text='"+ article_2 + "']"));

        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc=\"More options\"]"));

        waitForElementAndClick(By.xpath("//*[@text = 'Add to reading list']"));

        waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/item_title'][@text = '"+ folderName + "']"));

        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"));

        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc=\"My lists\"]"));

        waitForElementAndClick(By.xpath("//*[@resource-id = 'org.wikipedia:id/item_title'][@text = '"+ folderName + "']"));

        // удаляем одну из записей
        swipeElementToLeft(By.xpath(defaultSearchArticleLocator + "[@text='"+ article_1 + "']"));

        // проверяем, что вторая записб отсталась
        waitForElementPresent(By.xpath(defaultSearchArticleLocator + "[@text='"+ article_2 + "']"));

        // плюс дополнительная проверка на то, что удаленного элемента нету на странице
        waitForElementNotPresent(
                By.xpath(defaultSearchArticleLocator + "[@text='"+ article_1 + "']"),
                10);

        waitForElementAndClick(By.xpath(defaultSearchArticleLocator + "[@text='"+ article_2 + "']"));

        // добавялем ожидание того, что прогрузилось название статьи и заодно сохраняем элемент для извлечения его текста в след шаге
        WebElement articleTitle =
                waitForElementPresent(By.xpath("//*[@resource-id = 'org.wikipedia:id/view_page_title_text'][@text='"+ article_2 + "']"));

        Assert.assertEquals(
                "Expected article '" + article_2 + "' not equals with actual '" + articleTitle.getText() + "'",
                article_2,
                articleTitle.getText()
                );
    }

    private String getAttributeFromElement(By by, String attribute){
        WebElement element = waitForElementPresent(by);
        return element.getAttribute(attribute);
    }

    private void compareThatActualValueMoreThanExpected(By by, int expectedValue){
        int actualValue = driver.findElements(by).size();
        Assert.assertTrue("actual value less than expected", actualValue > expectedValue);
    }

    private void assertAllElementContainText(By by, String text){
        List <WebElement> elements = driver.findElements(by);
        for (WebElement element: elements){
            Assert.assertTrue("Element '" + element.getText() + "' not contain '" + text + "'",
                    element.getText().contains(text));
        }
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

    private void waitForElementAndClear(By by){
        WebElement element = waitForElementPresent(by);
        element.clear();
    }

    private boolean waitForElementNotPresent(By by, long timeoutInSec){

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

    private void quickSwipeUp(){
        swipeUp(200);
    }

    private void swipeUp(int swipeTime){
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

    private void swipeElementToLeft(By by){
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
}
