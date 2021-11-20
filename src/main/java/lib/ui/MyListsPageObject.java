package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{

    private static final String LIST = "//*[@resource-id = 'org.wikipedia:id/item_title'][@text = '{list_name}']";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openList(String list_name){
        waitForElementAndClick(By.xpath(replaceTemplate(LIST, "{list_name}", list_name)));
    }
}
