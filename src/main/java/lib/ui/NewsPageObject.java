package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

// страница, которая открывается при входе в приложение
public class NewsPageObject extends MainPageObject{

    private static final String MY_LISTS = "//android.widget.FrameLayout[@content-desc=\"My lists\"]";

    public NewsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openSavedLists(){
        waitForElementAndClick(By.xpath(MY_LISTS));
    }
}
