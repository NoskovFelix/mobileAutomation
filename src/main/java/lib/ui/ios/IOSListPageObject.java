package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ListPageObject;

public class IOSListPageObject extends ListPageObject {

    static {
        DEFAULT_ARTICLE_TITLE =
                "xpath://XCUIElementTypeStaticText[@name='{article_title}']";
        SYNC_WINDOW = "id:Close";
    }

    public IOSListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
