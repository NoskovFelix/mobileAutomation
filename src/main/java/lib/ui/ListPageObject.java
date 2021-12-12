package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ListPageObject extends MainPageObject{

    private static final String DEFAULT_ARTICLE_TITLE =
            "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{article_title}']";

    public ListPageObject(AppiumDriver driver) {
        super(driver);
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
}
