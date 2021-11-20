package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    private static final String SEARCH_FIELD = "//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']";
    private static final String INPUT_FIELD = "org.wikipedia:id/search_src_text";
    private static final String SEARCH_RESULT_ARTICLES = "//*[@resource-id='org.wikipedia:id/page_list_item_title']";
    private static final String ARTICLE_TITLE = SEARCH_RESULT_ARTICLES + "[@text='{article_title}']";
    private static final String CLOSE_BUTTON = "org.wikipedia:id/search_close_btn";



    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    public void initSearchInput(){
        waitForElementAndClick(By.xpath(SEARCH_FIELD));
    }

    public void sendValueInSearchInput(String value){
        this.waitForElementAndSendKeys(By.id(INPUT_FIELD), value);
    }

    public void searchResultMoreOrEqualThanValue(int expectedValue){
        this.compareThatActualValueMoreThanExpected(By.xpath(SEARCH_RESULT_ARTICLES), expectedValue);
    }

    public void closeSearchInput(){
        this.waitForElementAndClick(By.id(CLOSE_BUTTON));
    }

    public void checkThatSearchResultNotPresent(){
        this.waitForElementNotPresent(By.xpath(SEARCH_RESULT_ARTICLES),10);
    }

    public void openArticleFromSearchResult(String articleTitle){
        waitForElementAndClick(By.xpath(replaceTemplate(
                ARTICLE_TITLE,
                "{article_title}",
                articleTitle)));
    }
}
