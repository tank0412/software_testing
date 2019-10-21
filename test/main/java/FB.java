package main.java;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        cityGuidesTest.class,
        collectionsTest.class,
        enterGroupTest.class,
        eventsTest.class,
        FBShortcutTest.class,
        findFriendTest.class,
        fundRaisersTest.class,
        gamingTest.class,
        jobsTest.class,
        liveVideosTest.class,
        messangerInstallTest.class,
        removeSuggestedFriendTest.class,
        videosTest.class,
})

public class FB {

    public static AndroidDriver driver;
    private static boolean setUpIsDone = false;
    public static boolean notLastTest = true;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        if(setUpIsDone) {
            return;
        }
       DesiredCapabilities desiredCapabilities = new DesiredCapabilities();


        desiredCapabilities.setCapability( MobileCapabilityType.DEVICE_NAME, "192.168.51.101:5555"); //emulator
       // desiredCapabilities.setCapability(MobileCapabilityType.APP, "D:\\facebook/Facebook+135.0.0.22.90.zip.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "com.facebook.katana");
        //desiredCapabilities.setCapability("appActivity", "com.facebook.katana.app.FacebookSplashScreenActivity"); //first screen
        //desiredCapabilities.setCapability("appActivity", "com.facebook.katana.dbl.activity.FacebookLoginActivity"); // for old FB version
        desiredCapabilities.setCapability("appActivity", "com.facebook.account.login.activity.SimpleLoginActivity");

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        //desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0"); //emulator

        desiredCapabilities.setCapability("fastReset", "true");
        desiredCapabilities.setCapability("autoGrantPermissions", "true");
        desiredCapabilities.setCapability("noReset", "false");


        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.NANOSECONDS);
        setUpIsDone = true;
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        FBCommonMethods fbCommonMethods = new FBCommonMethods();
        fbCommonMethods.customWait(20);
        FBAuth fbAuth = new FBAuth(driver);
        fbAuth.login();
        //fbAuth.register();
    }

    @AfterClass
    public static void tearDown() {
        if(notLastTest) return;
        FBAuth fbAuth = new FBAuth(driver);
        fbAuth.logOut();
        FBShortcutTest fbShortcutTest = new FBShortcutTest();
        fbShortcutTest.testFBShortcut();
        //driver.quit();
    }
}
