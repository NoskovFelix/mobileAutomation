package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

abstract public class ListPageObject extends MainPageObject{

    protected static String DEFAULT_ARTICLE_TITLE;
    protected static String SYNC_WINDOW;
    protected static String TITLE_AND_DESCRIPTION_OF_ARTICLE;

    public ListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void closeSyncWindow(){
        this.waitForElementAndClick(SYNC_WINDOW);
    }

    public void deleteArticleWithSwipe(String articleTitle){
        swipeElementToLeft(replaceTemplate(
                DEFAULT_ARTICLE_TITLE,
                "{article_title}",
                articleTitle));
    }

    public void checkThatListContainArticle(String articleTitle){
        waitForElementPresent(replaceTemplate(
                DEFAULT_ARTICLE_TITLE,
                "{article_title}",
                articleTitle
        ));
    }

    public void checkThatListNotContainArticle(String articleTitle){
        waitForElementNotPresent(replaceTemplate(
                DEFAULT_ARTICLE_TITLE,
                "{article_title}",
                articleTitle
        ), 10);
    }

    public void openArticle(String articleTitle){
        waitForElementAndClick(replaceTemplate(
                DEFAULT_ARTICLE_TITLE,
                "{article_title}",
                articleTitle
        ));
    }

    public WebElement waitForElementByTitleAndDescription(String title, String description) {
        return this.waitForElementByTitleAndDescriptionCore(
                TITLE_AND_DESCRIPTION_OF_ARTICLE,
                title,
                description
        );
    }
}
