package main.java;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class fundRaisersTest {
    private AndroidDriver driver = FB.driver;

    @Test
    public void fundRaisers() {
        FBCommonMethods fbCommonMethods = new FBCommonMethods();
        fbCommonMethods.enterMainMenu();
        fbCommonMethods.customWait(5);
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Fundraisers\"]")).click();
        }
        catch(NoSuchElementException exp) {
            fbCommonMethods.seeMoreBtn();
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Fundraisers\"]")).click();
        }
        fbCommonMethods.customWait(2);

        //scroll to first fundraiser
        //String scrollTo= "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.Button";
        String scrollTo = "Fundraiser for";
        try {
            driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + scrollTo + "\").instance(0))").click();

        }
        catch(NoSuchElementException exp) {
            List<WebElement> fundraiser=driver.findElements(By.className("android.view.ViewGroup"));
            fundraiser.get(10).click(); // 10 is first fundraiser -> Click to see more about it
        }
        fbCommonMethods.customWait(5);
        //click share
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"SHARE\"]")).click();
        }
        catch(NoSuchElementException exp) {
            driver.findElementByAccessibilityId("Share").click();
        }
        fbCommonMethods.customWait(2);
        //share it as post
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Share as Post Share fundraiser in Feed\"]")).click();
        fbCommonMethods.customWait(2);
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"POST\"]")).click();
        fbCommonMethods.customWait(2);
        //close share menu
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Close\"]/android.view.ViewGroup")).click();
        fbCommonMethods.customWait(2);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Follow\"]")).click();
        fbCommonMethods.customWait(2);

        fbCommonMethods.callFBBackButton(2);
    }
}
