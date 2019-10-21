package main.java;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class messangerInstallTest {
    private AndroidDriver driver = FB.driver;
    @Test
    public void messangerInstall() {
        FBCommonMethods fbCommonMethods = new FBCommonMethods();
        fbCommonMethods.enterMainMenu();
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Install Messenger\"]")).click();
        }
        catch(NoSuchElementException exp) {
            fbCommonMethods.seeMoreBtn();
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Install Messenger\"]")).click();
        }
        fbCommonMethods.customWait(4);
        //click install button in Google Play
        driver.findElement(By.id("com.android.vending:id/right_button")).click();
        fbCommonMethods.customWait(4);
        //click cancel to not wait
        driver.findElement(By.id("com.android.vending:id/left_button")).click();
        fbCommonMethods.customWait(2);
        fbCommonMethods.callAndroidBackButton(1);
        fbCommonMethods.customWait(2);
        fbCommonMethods.callFBBackButton(1);
    }
}
