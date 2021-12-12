package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class iOSTestCase extends TestCase {

    protected AppiumDriver driver;
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 8");
        capabilities.setCapability("platformVersion", "15.0");
        capabilities.setCapability("app", "/Users/felixnv/Desktop/mobileCources/apks/Wikipedia.app");
        // после каждого теста устанавливается портретная ориентация приложения
        capabilities.setCapability("orientation", "PORTRAIT");

        driver = new IOSDriver(new URL(APPIUM_URL), capabilities);
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }
}
