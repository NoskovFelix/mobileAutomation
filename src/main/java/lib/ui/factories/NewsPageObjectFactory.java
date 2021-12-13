package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.Platform;
import lib.ui.NewsPageObject;
import lib.ui.android.AndroidNewsPageObject;
import lib.ui.ios.IOSNewsPageObject;

public class NewsPageObjectFactory {

    public static NewsPageObject get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid())
            return new AndroidNewsPageObject(driver);
        else return new IOSNewsPageObject(driver);
    }
}
