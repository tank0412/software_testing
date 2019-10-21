package main.java;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class removeSuggestedFriendTest {
    private AndroidDriver driver = FB.driver;
    @Test
    public void removeSuggestedFriend() {
        FBCommonMethods fbCommonMethods = new FBCommonMethods();
        try {
            driver.findElementByXPath("//android.view.View[@content-desc=\"Friends, Tab 2 of 6\"]").click();
        }
        catch (NoSuchElementException exp) {
            driver.findElementByXPath("//android.view.View[@content-desc=\"Friends, Tab 2 of 4\"]").click();
        }
        fbCommonMethods.customWait(2);
        List<WebElement> removeButton=driver.findElements(By.className("android.view.ViewGroup"));
        removeButton.get(17).click(); // 15 is to add to friend -> Click remove which removes one friend from list
        FB.notLastTest = false;
    }
}
