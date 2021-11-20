package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String MORE_OPTIONS = "//android.widget.ImageView[@content-desc=\"More options\"]";
    private static final String OPTION_TITLE = "//*[@text = '{option_title}']";
    private static final String FOLDER_LIST = "//*[@resource-id = 'org.wikipedia:id/item_title'][@text = '{folder_name}']";
    private static final String SUCCESS_ACTION = "//android.widget.Button[@text = 'OK']";
    private static final String READING_LIST_TITLE_INPUT = "org.wikipedia:id/text_input";
    private static final String INFORMATION_BOARD = "//*[@resource-id = 'org.wikipedia:id/onboarding_button'][@text = 'GOT IT']";
    private static final String RETURN_TO_PREVIOUS = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]";
    private static final String ARTICLE_TITLE = "//*[@resource-id = 'org.wikipedia:id/view_page_title_text'][@text='{article_title}']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }


    public void openArticleOptions(){
        waitForElementAndClick(By.xpath(MORE_OPTIONS));
    }

    public void openArticleOptionWithTitle(String title){
        waitForElementAndClick(By.xpath(replaceTemplate(
                OPTION_TITLE,
                "{option_title}",
                title
        )));
    }

    public void acquaintedWithTheInformationBoard(){
        waitForElementAndClick(By.xpath(INFORMATION_BOARD));
    }

    public void sendReadingListTitle(String title){
        waitForElementAndClear(By.id(READING_LIST_TITLE_INPUT));
        waitForElementAndSendKeys(By.id(READING_LIST_TITLE_INPUT), title);
    }

    public void clickOKToSaveList(){
        waitForElementAndClick(By.xpath(SUCCESS_ACTION));
    }

    public void returnToPreviousPage(){
        waitForElementAndClick(By.xpath(RETURN_TO_PREVIOUS));
    }

    public void addArticleInExistingFolder(String folderName){
        waitForElementAndClick(By.xpath(replaceTemplate(FOLDER_LIST, "{folder_name}", folderName)));
    }

    public WebElement getArticleTitleElement(String title){
        return waitForElementPresent(By.xpath(replaceTemplate(
                ARTICLE_TITLE,
                "{article_title}",
                title
        )));
    }
}
