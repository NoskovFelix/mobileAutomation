package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NewsPageObject;

public class IOSNewsPageObject extends NewsPageObject {

    static {
        MY_LISTS = "id:Saved";
    }

    public IOSNewsPageObject (AppiumDriver driver){
        super(driver);
    }
}
