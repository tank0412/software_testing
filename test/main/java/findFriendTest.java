package main.java;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;

public class findFriendTest {
    private AndroidDriver driver = FB.driver;

    @Test
    public void findFriend() {
        FBCommonMethods fbCommonMethods = new FBCommonMethods();
        fbCommonMethods.enterMainMenu();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Find Friends\"]")).click();
        fbCommonMethods.customWait(2);
        //clicking on search
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText")).click();
        //use search another search field to enter request
        fbCommonMethods.customWait(2);
        MobileElement searchFriends = (MobileElement) driver.findElement(By.xpath(" /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText"));
        searchFriends.sendKeys("Roman Bukhtiarov" + "\n");
        fbCommonMethods.customWait(10);
        driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"Add friend request\"])[1]")).click();

        fbCommonMethods.callFBBackButton(3);
    }
}
