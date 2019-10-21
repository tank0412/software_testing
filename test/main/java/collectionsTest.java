package main.java;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class collectionsTest {
    private AndroidDriver driver = FB.driver;

    @Test
    public void collections() {
        FBCommonMethods fbCommonMethods = new FBCommonMethods();
        fbCommonMethods.enterMainMenu();
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Saved\"]")).click();
        }
        catch(NoSuchElementException exp) {
            fbCommonMethods.seeMoreBtn();
        }
        fbCommonMethods.customWait(2);
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Create\"]/android.widget.TextView")).click();
        fbCommonMethods.customWait(1);
        //enter collection name
        MobileElement collName = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText"));
        collName.sendKeys("TEST");
        fbCommonMethods.customWait(3);
        //click create collection button
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.Button[2]/android.widget.TextView\n")).click();
        fbCommonMethods.customWait(1);
        fbCommonMethods.callAndroidBackButton(2);
    }
}
