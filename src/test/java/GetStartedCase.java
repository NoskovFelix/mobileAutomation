import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedCase extends CoreTestCase {


    @Test
    public void testPassThroughWelcome(){

        if (Platform.getInstance().isAndroid()) return;

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
