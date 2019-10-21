package main.java;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class FBCommonMethods {
    private AndroidDriver driver = FB.driver;

    void customWait(int digit) {
        try {
            Thread.sleep(digit * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void callFBBackButton(int times) {
        customWait(2);
        for(int i = 0; i < times; ++i) {
            driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Back\"]")).click(); //exit
        }
    }
    void callAndroidBackButton(int times) {
        customWait(2);
        for(int i = 0; i < times; ++i) {
            driver.pressKey(new KeyEvent(AndroidKey.BACK)); //exit
        }
    }
    void enterMainMenu() {
        customWait(5);
        try {
            driver.findElement(By.xpath("//android.view.View[@content-desc=\"Menu, Tab 6 of 6\"]")).click();
        }
        catch(NoSuchElementException exp) {
            System.out.println("We have an Main Menu exception" + exp);
            driver.findElement(By.xpath("//android.view.View[@content-desc=\"Menu, Tab 4 of 4\"]")).click();
        }
        customWait(2);
    }
    public void seeMoreBtn() {
        customWait(5);
        //click see more button
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"See More, Header. Section is collapsed. Double-tap to expand the section.\"]")).click();
        }
        catch (NoSuchElementException exp) {
            System.out.println("Do not need to press see more button");
        }
    }
}
