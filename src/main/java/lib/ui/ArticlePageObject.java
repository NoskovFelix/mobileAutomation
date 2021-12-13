package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String MORE_OPTIONS;
    protected static String OPTION_TITLE;
    protected static String FOLDER_LIST;
    protected static String SUCCESS_ACTION ;
    protected static String READING_LIST_TITLE_INPUT;
    protected static String INFORMATION_BOARD;
    protected static String RETURN_TO_PREVIOUS;
    protected static String ARTICLE_TITLE;
    protected static String UNSAVED_ARTICLE;


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }


    public void openArticleOptions(){
        waitForElementAndClick(MORE_OPTIONS);
    }

    public void openArticleOptionWithTitle(String title){
        waitForElementAndClick(replaceTemplate(
                OPTION_TITLE,
                "{option_title}",
                title
        ));
    }

    public void acquaintedWithTheInformationBoard(){
        waitForElementAndClick(INFORMATION_BOARD);
    }

    public void sendReadingListTitle(String title){
        waitForElementAndClear(READING_LIST_TITLE_INPUT);
        waitForElementAndSendKeys(READING_LIST_TITLE_INPUT, title);
    }

    public void clickOKToSaveList(){
        waitForElementAndClick(SUCCESS_ACTION);
    }

    public void returnToPreviousPage(){
        waitForElementAndClick(RETURN_TO_PREVIOUS);
    }

    public void addArticleInExistingFolder(String folderName){
        waitForElementAndClick(replaceTemplate(FOLDER_LIST, "{folder_name}", folderName));
    }

    public WebElement getArticleTitleElement(String title){
        return waitForElementPresent(replaceTemplate(
                ARTICLE_TITLE,
                "{article_title}",
                title
        ));
    }

    public void savedArticleToList(String folderName){
        this.openArticleOptions();
        this.openArticleOptionWithTitle("Add to reading list");
        this.acquaintedWithTheInformationBoard();
        this.sendReadingListTitle(folderName);
    }

    public void addArticlesToMySaved(){
        this.waitForElementAndClick(SUCCESS_ACTION);
    }

    public void setUnsavedArticle(){
        this.waitForElementAndClick(UNSAVED_ARTICLE);
    }
}
