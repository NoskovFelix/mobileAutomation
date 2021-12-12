package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import sun.security.krb5.internal.crypto.Des;

import java.net.MalformedURLException;
import java.net.URL;

public class CoreTestCase extends TestCase {

    private final static String PLATFORM_IOS = "ios";
    private final static String PLATFORM_ANDROID = "android";
    protected AppiumDriver driver;
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
        driver = this.getDriverFromEnv(capabilities);
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (platform.equals(PLATFORM_ANDROID)){
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "11");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/felixnv/Desktop/mobileCources/apks/org.wikipedia.apk");
        }
        else if (platform.equals(PLATFORM_IOS)){
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 8");
            capabilities.setCapability("platformVersion", "15.0");
            capabilities.setCapability("app", "/Users/felixnv/Desktop/mobileCources/apks/Wikipedia.app");
        }
        else throw new Exception("Can not find platform from env variable. Your variable is '" + platform + "'");

        capabilities.setCapability("orientation", "PORTRAIT");
        return capabilities;
    }

    private AppiumDriver getDriverFromEnv(DesiredCapabilities capabilities) throws Exception {
        String platform = System.getenv("PLATFORM");

        if (platform.equals(PLATFORM_ANDROID))
            return new AndroidDriver(new URL(APPIUM_URL), capabilities);
        else if (platform.equals(PLATFORM_IOS))
            return new IOSDriver(new URL(APPIUM_URL), capabilities);
        else throw new Exception("Can not find platform from env variable. Your variable is '" + platform + "'");
    }
}
