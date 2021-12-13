package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject {

    static {
        SEARCH_FIELD = "xpath://*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']";
        INPUT_FIELD = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT_ARTICLES = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title']";
        ARTICLE_TITLE = SEARCH_RESULT_ARTICLES + "[@text='{article_title}']";
        CLOSE_BUTTON = "id:org.wikipedia:id/search_close_btn";
        TITLE_AND_DESCRIPTION_OF_ARTICLE
                = "xpath://android.widget.LinearLayout" +
                "[" +
                ".//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{article_title}']" +
                "/../" +
                "android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{article_description}']" +
                "]";
    }


    public AndroidSearchPageObject(AppiumDriver driver) {
        super(driver);
    }

}
