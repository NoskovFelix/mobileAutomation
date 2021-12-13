package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NewsPageObject;

public class AndroidNewsPageObject extends NewsPageObject {

    static {
        MY_LISTS = "xpath://android.widget.FrameLayout[@content-desc=\"My lists\"]";
    }

    public AndroidNewsPageObject(AppiumDriver driver){
        super(driver);
    }

}
