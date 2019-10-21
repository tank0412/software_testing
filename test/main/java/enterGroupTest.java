package main.java;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class enterGroupTest {
    private AndroidDriver driver = FB.driver;

    @Test
    public void enterGroup() {
        FBCommonMethods fbCommonMethods = new FBCommonMethods();
        fbCommonMethods.enterMainMenu();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Groups\"]")).click();
        fbCommonMethods.customWait(2);
        //clicking on search
        try {
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText")).click();
        }
        catch(NoSuchElementException exp) {
            System.out.println("We have an exception: " + exp);
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.EditText")).click();

        }
        //use search another search field to enter request
        fbCommonMethods.customWait(2);
        MobileElement searchGroup = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText");
        searchGroup.sendKeys("empty group" +"\n");
        fbCommonMethods.customWait(5);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Groups\"]")).click();
        //Choose ITMO Group
        fbCommonMethods.customWait(2);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup")).click();
        fbCommonMethods.customWait(2);
        //get ask group join menu
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Join Group\"]")).click();
        fbCommonMethods.callFBBackButton(4);
    }
}
