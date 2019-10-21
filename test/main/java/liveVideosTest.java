package main.java;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class liveVideosTest {
    private AndroidDriver driver = FB.driver;

    @Test
    public void liveVideos() {
        FBCommonMethods fbCommonMethods = new FBCommonMethods();
        fbCommonMethods.enterMainMenu();
        fbCommonMethods.customWait(5);
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Live Videos\"]")).click();
        }
        catch(NoSuchElementException exp) {
            fbCommonMethods.seeMoreBtn();
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Live Videos\"]")).click();
        }
        fbCommonMethods.customWait(4);
        //like and follow on first post
        try {
            driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + "follow" + "\").instance(0)").click();
        }
        catch(NoSuchElementException exp) {
            try {
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.view.ViewGroup[2]").click();
            }
            catch (NoSuchElementException exp2) {
                System.out.println("Failed to follow");
            }
        }
        fbCommonMethods.customWait(2);
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+"like"+"\").instance(0)").click();
        //click share button
        fbCommonMethods.customWait(2);
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+"share"+"\").instance(0)").click();
        fbCommonMethods.customWait(2);
        //write post during sharing
        MobileElement shareVideoPost = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.EditText");
        shareVideoPost.sendKeys("Not bad");
        fbCommonMethods.customWait(5);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"SHARE NOW\"]")).click();
        fbCommonMethods.customWait(2);
        fbCommonMethods.callAndroidBackButton(1);
    }
}
