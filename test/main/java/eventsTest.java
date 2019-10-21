package main.java;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class eventsTest {
    private AndroidDriver driver = FB.driver;

    @Test
    public void events() {
        FBCommonMethods fbCommonMethods = new FBCommonMethods();
        fbCommonMethods.enterMainMenu();
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Events\"]")).click();
        }
        catch (NoSuchElementException exp) {
            fbCommonMethods.seeMoreBtn();
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Events\"]")).click();
        }
        fbCommonMethods.customWait(2);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Tomorrow\"]")).click();
        fbCommonMethods.customWait(2);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup")).click();
        fbCommonMethods.customWait(2);
        fbCommonMethods.callFBBackButton(2);
    }
}
