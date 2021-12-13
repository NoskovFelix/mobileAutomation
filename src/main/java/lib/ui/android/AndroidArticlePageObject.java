package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        MORE_OPTIONS = "xpath://android.widget.ImageView[@content-desc=\"More options\"]";
        OPTION_TITLE = "xpath://*[@text = '{option_title}']";
        FOLDER_LIST = "xpath://*[@resource-id = 'org.wikipedia:id/item_title'][@text = '{folder_name}']";
        SUCCESS_ACTION = "xpath://android.widget.Button[@text = 'OK']";
        READING_LIST_TITLE_INPUT = "id:org.wikipedia:id/text_input";
        INFORMATION_BOARD = "xpath://*[@resource-id = 'org.wikipedia:id/onboarding_button'][@text = 'GOT IT']";
        RETURN_TO_PREVIOUS = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]";
        ARTICLE_TITLE = "xpath://*[@resource-id = 'org.wikipedia:id/view_page_title_text'][@text='{article_title}']";
    }

    public AndroidArticlePageObject(AppiumDriver driver){
        super(driver);
    }

}
