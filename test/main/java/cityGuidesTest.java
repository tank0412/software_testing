package main.java;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class cityGuidesTest {
    private AndroidDriver driver = FB.driver;

    @Test
    public void cityGuides() {
        FBCommonMethods fbCommonMethods = new FBCommonMethods();
        fbCommonMethods.enterMainMenu();
        fbCommonMethods.customWait(3);
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"City Guides\"]")).click();
        }
        catch (NoSuchElementException exp) {
            fbCommonMethods.seeMoreBtn();
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"City Guides\"]")).click();
        }
        fbCommonMethods.customWait(2);
        //click Saint Petersburg button
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")).click();
        fbCommonMethods.customWait(4);
        //press like button for city
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Like\"]/android.view.ViewGroup")).click();
        fbCommonMethods.customWait(2);

        //make check-in
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Check In\"]/android.view.ViewGroup")).click();
        fbCommonMethods.customWait(2);
        //write check in post
        MobileElement checkInPost = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.EditText");
        checkInPost.sendKeys("Saint Petersburg is an awesome city!");
        fbCommonMethods.customWait(5);
        //click post button
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"POST\"]")).click();
        fbCommonMethods.customWait(2);

        //share a city
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Share\"]/android.view.ViewGroup")).click();
        fbCommonMethods.customWait(2);
        //enter Feeling/Activity/Sticker menu
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Feeling/Activity/Sticker\"]")).click();
        fbCommonMethods.customWait(2);
        //enter sticker menu
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Stickers, Tab 2 of 3\"]")).click();
        fbCommonMethods.customWait(4);
        //choose happy sticker
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+"Happy"+"\").instance(0)").click();
        fbCommonMethods.customWait(2);
        //choose a last happy sticker in a list
        driver.findElement(By.xpath("//android.widget.RelativeLayout[@content-desc=\"Sticker 23 of 23\"]/android.widget.ImageView")).click();
        }
        catch (NoSuchElementException exp) {
            System.out.println("Impossible to post a sticker!");
        }
        fbCommonMethods.customWait(2);
        MobileElement shareCityPost;
        try {
            shareCityPost = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.EditText");
        }
        catch (NoSuchElementException exp) {
            System.out.println("Failed to write a post text, Trying to use another method....");
            shareCityPost = (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+"Write something…"+"\").instance(0)");
        }

        shareCityPost.sendKeys("What a beautiful city!");
        fbCommonMethods.customWait(5);
        //click post button
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"POST\"]")).click();
        fbCommonMethods.customWait(2);

        //save a city
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Save\"]/android.view.ViewGroup")).click();
        fbCommonMethods.customWait(2);

        //save a first restaurant from a list
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.Button/android.view.ViewGroup[4]/android.widget.ImageView")).click();
        fbCommonMethods.customWait(2);

        fbCommonMethods.callFBBackButton(2);
    }
}
