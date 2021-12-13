package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject{

    private final static String STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia";
    private final static String STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore";
    private final static String STEP_ADD_OR_EDIT_LANGUAGES = "id:Add or edit preferred languages";
    private final static String STEP_LEARN_MORE_DATA_COLLECT_LINK = "id:Learn more about data collected";
    private final static String NEXT_BUTTON = "id:Next";
    private final static String GET_STARTED_BUTTON = "id:Get started";
    private final static String SKIP_BUTTON = "id:Skip";



    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink(){
        this.waitForElementPresent(STEP_LEARN_MORE_LINK);
    }

    public void waitForNewWaysToExplore(){
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE);
    }

    public void waitForAddOrEditLanguagesLink(){
        this.waitForElementPresent(STEP_ADD_OR_EDIT_LANGUAGES);
    }

    public void waitForLearnMoreDataCollectingLink(){
        this.waitForElementPresent(STEP_LEARN_MORE_DATA_COLLECT_LINK);
    }

    public void clickNextButton(){
        this.waitForElementAndClick(NEXT_BUTTON);
    }

    public void clickGetStartedButton(){
        this.waitForElementAndClick(GET_STARTED_BUTTON);
    }

    public void clickSkip(){
        this.waitForElementAndClick(SKIP_BUTTON);
    }
}
