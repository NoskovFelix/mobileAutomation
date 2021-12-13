package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        FOLDER_LIST = "xpath://*[@resource-id = 'org.wikipedia:id/item_title'][@text = '{folder_name}']";
        RETURN_TO_PREVIOUS = "id:Back";
        SUCCESS_ACTION = "id:Save for later";
        ARTICLE_TITLE = "id:JavaScript";
        UNSAVED_ARTICLE = "id:Saved. Activate to unsave.";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
