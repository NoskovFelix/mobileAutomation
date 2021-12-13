package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_FIELD = "id:Search Wikipedia";
        INPUT_FIELD = "id:Search Wikipedia";
        SEARCH_RESULT_ARTICLES = "xpath://XCUIElementTypeCell";
        ARTICLE_TITLE = "xpath://XCUIElementTypeStaticText[@name='{article_title}']";
        CLOSE_BUTTON = "xpath://XCUIElementTypeButton[@name='Cancel']";
        TITLE_AND_DESCRIPTION_OF_ARTICLE
                = "xpath://android.widget.LinearLayout" +
                "[" +
                ".//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{article_title}']" +
                "/../" +
                "android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{article_description}']" +
                "]";
    }


    public IOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
