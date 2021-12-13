package lib.ui;

import io.appium.java_client.AppiumDriver;

// страница, которая открывается при входе в приложение
abstract public class NewsPageObject extends MainPageObject{

    protected static String MY_LISTS;

    public NewsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openSavedLists(){
        waitForElementAndClick(MY_LISTS);
    }
}
