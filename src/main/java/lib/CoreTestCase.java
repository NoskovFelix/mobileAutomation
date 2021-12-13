package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;


public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.skipWelcomPageForIOSApp();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    private void skipWelcomPageForIOSApp(){
        if (Platform.getInstance().isIOS()){
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();
        }

    }
}
