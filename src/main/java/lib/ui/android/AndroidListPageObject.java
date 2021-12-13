package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ListPageObject;

public class AndroidListPageObject extends ListPageObject {
    static {
        DEFAULT_ARTICLE_TITLE =
                "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{article_title}']";
    }


    public AndroidListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
