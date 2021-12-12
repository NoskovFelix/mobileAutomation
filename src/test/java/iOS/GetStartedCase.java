package iOS;

import lib.CoreTestCase;
import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedCase extends iOSTestCase {

    @Test
    public void testPassThroughWelcome(){

        WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
        welcomePageObject.waitForLearnMoreLink();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForNewWaysToExplore();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForAddOrEditLanguagesLink();
        welcomePageObject.clickNextButton();

        welcomePageObject.waitForLearnMoreDataCollectingLink();
        welcomePageObject.clickGetStartedButton();
    }
}
