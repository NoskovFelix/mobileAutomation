package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject{

    private static final String SEARCH_FIELD = "xpath://*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']";
    private static final String INPUT_FIELD = "id:org.wikipedia:id/search_src_text";
    private static final String SEARCH_RESULT_ARTICLES = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
    private static final String ARTICLE_TITLE = SEARCH_RESULT_ARTICLES + "[@text='{article_title}']";
    private static final String CLOSE_BUTTON = "id:org.wikipedia:id/search_close_btn";
    private static final String TITLE_AND_DESCRIPTION_OF_ARTICLE
            = "xpath://android.widget.LinearLayout" +
            "[" +
            ".//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{article_title}']" +
            "/../" +
            "android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{article_description}']" +
            "]";


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
