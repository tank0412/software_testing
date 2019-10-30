package main.java;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class jobsTest {
    private AndroidDriver driver = FB.driver;

    @Test
    public void jobs() {
        FBCommonMethods fbCommonMethods = new FBCommonMethods();
        fbCommonMethods.enterMainMenu();
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Jobs\"]")).click();
        }
        catch(NoSuchElementException exp) {
            fbCommonMethods.seeMoreBtn();
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Jobs\"]")).click();
        }
        fbCommonMethods.customWait(4);
        //click on settings
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.Button")).click();
        fbCommonMethods.customWait(2);
        //click on jobs which we are interested
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.Button")).click();
        fbCommonMethods.customWait(2);
        //enter job
        MobileElement enterJob = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.EditText");
        enterJob.sendKeys("Cashier");
        fbCommonMethods.customWait(4);
        //click on job which we entered
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.widget.ImageView")).click();
        fbCommonMethods.customWait(2);
        //disable notifications to receive a it when job is available
        try {
            driver.findElement(By.xpath("//X.6Nr[@content-desc=\"Toggle button\"]")).click();
        }
        catch (NoSuchElementException exp) {
            System.out.println("Failed to press toogle to disable notifications. Attempting to use another method.....");
            driver.findElementByAndroidUIAutomator("UiSelector().description(\"Toggle button\")").click();
        }
        fbCommonMethods.customWait(2);
        //click save button
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.Button[2]/android.widget.TextView")).click();
        fbCommonMethods.customWait(2);
        fbCommonMethods.callFBBackButton(2);
    }
}
