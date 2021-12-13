package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

abstract public class SearchPageObject extends MainPageObject{

    protected static String SEARCH_FIELD;
    protected static String INPUT_FIELD;
    protected static String SEARCH_RESULT_ARTICLES;
    protected static String ARTICLE_TITLE;
    protected static String CLOSE_BUTTON;
    protected static String TITLE_AND_DESCRIPTION_OF_ARTICLE;


    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    public void initSearchInput(){
        waitForElementAndClick(SEARCH_FIELD);
    }

    public void sendValueInSearchInput(String value){
        this.waitForElementAndSendKeys(INPUT_FIELD, value);
    }

    public void searchResultMoreOrEqualThanValue(int expectedValue){
        this.compareThatActualValueMoreThanExpected(SEARCH_RESULT_ARTICLES, expectedValue);
    }

    public void closeSearchInput(){
        this.waitForElementAndClick(CLOSE_BUTTON);
    }

    public void checkThatSearchResultNotPresent(){
        this.waitForElementNotPresent(SEARCH_RESULT_ARTICLES,10);
    }

    public void checkThatAllSearchArticlesContainText(String text){
        this.assertAllElementContainText(SEARCH_RESULT_ARTICLES, text);
    }

    public void checkThatSearchInputContainTextInPlaceHolder(String text){
        this.assertElementHasText(SEARCH_FIELD, text, "Search input does not contain text:" + text);
    }

    public void openArticleFromSearchResult(String articleTitle){
        waitForElementAndClick(replaceTemplate(
                ARTICLE_TITLE,
                "{article_title}",
                articleTitle));
    }

    public WebElement waitForElementByTitleAndDescription(String title, String description){
        return waitForElementPresent(replaceTemplates(
                TITLE_AND_DESCRIPTION_OF_ARTICLE,
                new String[]{"{article_title}","{article_description}"},
                new String[]{title, description}
        ));
    }
}
