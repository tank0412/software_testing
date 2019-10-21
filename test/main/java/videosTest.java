package main.java;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class videosTest {
    private AndroidDriver driver = FB.driver;

    @Test
    public void videos() {
        FBCommonMethods fbCommonMethods = new FBCommonMethods();
        fbCommonMethods.enterMainMenu();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Videos on Watch\"]")).click();
        fbCommonMethods.customWait(2);
        MobileElement searchVideo = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText");
        searchVideo.sendKeys("ITMO UNIVERSITY" +"\n");
        fbCommonMethods.customWait(5);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.view.ViewGroup")).click();
        fbCommonMethods.customWait(4);
        boolean success = true;
        while(success) {
            try {
                driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Clear text\"]")).click(); //Click video
                driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Clear text\"]")).click(); // enter menu
                driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Save video, Add this to your saved videos.\"]/android.widget.TextView[1]")).click();
                fbCommonMethods.customWait(1);
                success = false;
            }
            catch (NoSuchElementException exp) {
                System.out.println("Failed to save video. Attempting again....");

            }
        }
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]")).click();
        fbCommonMethods.callAndroidBackButton(7);
    }
}
