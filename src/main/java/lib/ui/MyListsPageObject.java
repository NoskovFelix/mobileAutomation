package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject{

    private static final String LIST = "xpath://*[@resource-id = 'org.wikipedia:id/item_title'][@text = '{list_name}']";

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openList(String list_name){
        waitForElementAndClick(replaceTemplate(LIST, "{list_name}", list_name));
    }
}
