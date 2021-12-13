package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ListPageObject;

public class AndroidListPageObject extends ListPageObject {
    static {
        DEFAULT_ARTICLE_TITLE =
                "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{article_title}']";
        TITLE_AND_DESCRIPTION_OF_ARTICLE = "xpath://android.widget.LinearLayout" +
                "[" +
                ".//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{article_title}']" +
                "/../" +
                "android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{article_description}']" +
                "]";
    }


    public AndroidListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
