package main.java;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Ignore;
import org.junit.Test;

import java.time.Duration;

public class FBShortcutTest {
    private AndroidDriver driver = FB.driver;
    @Ignore
    @Test
    public void testFBShortcut() {
        FBCommonMethods fbCommonMethods = new FBCommonMethods();
        fbCommonMethods.customWait(2);
        driver.pressKey(new KeyEvent(AndroidKey.HOME)); // press home
        fbCommonMethods.customWait(2);
        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Apps\"]").click();
        fbCommonMethods.customWait(2);
        MobileElement fbApp = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Facebook\"]");
        new TouchAction((MobileDriver) driver).longPress(PointOption.point(450,722)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(5000))).moveTo(PointOption.point(479, 1312)).release().perform();
        fbCommonMethods.customWait(2);
    }
}
