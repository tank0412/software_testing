package main.java;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class gamingTest {
    private AndroidDriver driver = FB.driver;

    @Test
    public void gaming() {
        FBCommonMethods fbCommonMethods = new FBCommonMethods();
        fbCommonMethods.enterMainMenu();
        fbCommonMethods.seeMoreBtn();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Gaming\"]")).click();
        fbCommonMethods.customWait(2);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Follow Games\"]")).click();
        fbCommonMethods.customWait(2);
        //follow first game from list
        try {
            driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"Add\"])[1]")).click();
        }
        catch (NoSuchElementException exp){
            System.out.println("Tryin to clck follow button instead of add button");
            driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"Follow\"])[1]")).click();
        }
        fbCommonMethods.customWait(2);
        fbCommonMethods.callFBBackButton(1);
        fbCommonMethods.customWait(2);

        //enter game streamers menu
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Streamers\"]")).click();
        fbCommonMethods.customWait(2);
        //follow first streamer from list
        driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"Follow\"])[1]")).click();
        //testing of 'You' menu was deprecated
        fbCommonMethods.customWait(2);
        fbCommonMethods.callFBBackButton(2);

    }
}
