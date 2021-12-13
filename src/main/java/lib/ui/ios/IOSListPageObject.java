package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ListPageObject;

public class IOSListPageObject extends ListPageObject {

    static {
        DEFAULT_ARTICLE_TITLE =
                "xpath://XCUIElementTypeStaticText[@name='{article_title}']";
        SYNC_WINDOW = "id:Close";
        TITLE_AND_DESCRIPTION_OF_ARTICLE =
            "xpath://XCUIElementTypeOther" +
            "[" +
            ".//XCUIElementTypeStaticText[@name='{article_title}']" +
            "/../" +
            "XCUIElementTypeStaticText[@name='{article_description}']" +
            "]";
    }

    public IOSListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
