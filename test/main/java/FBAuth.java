package main.java;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class FBAuth {
    private AndroidDriver driver;
    private FBCommonMethods fb;

    public FBAuth(AndroidDriver adriver) {
        fb = new FBCommonMethods();
        driver = adriver;
    }

    @Ignore
    @Test
    public void register() {
        fb.customWait(10);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Create New Facebook Account\"]")).click();
        fb.customWait(10);
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + "Next" + "\").instance(0)").click();
        /*
        try {
            driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click(); // It ask for permissions during first sign up only
            driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        }
        catch(NoSuchElementException exp) {
            System.out.println("We have an exception " + exp );
        }
        */

        try {
            driver.findElementById("com.google.android.gms:id/cancel").click();
        }
        catch(NoSuchElementException exp) {
            System.out.println("No need to press btn to deny continue with google account");
        }


        MobileElement el1 = (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + "First Name" + "\").instance(0)");
        el1.sendKeys("Ivan");
        fb.customWait(10);

        MobileElement el2 = (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + "Last Name" + "\").instance(0)");
        el2.sendKeys("Ivanov");
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + "Next" + "\").instance(0)").click();
        fb.customWait(10);
        TouchAction touchAction = new TouchAction(driver);
        touchAction.longPress(PointOption.point(726, 490)).perform(); //or tap
        fb.customWait(5);
        touchAction.longPress(PointOption.point(465, 492)).perform();
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + "Next" + "\").instance(0)").click();

        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + "Male" + "\").instance(0)").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + "Next" + "\").instance(0)").click();

        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + "Sign Up With Email Address" + "\").instance(0)").click();

        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + "Use a different email" + "\").instance(0)").click();

        MobileElement emailInput = (MobileElement) driver.findElementByXPath("//android.widget.EditText[@content-desc=\"Email Address\"]");
        emailInput.sendKeys("adasddfeadmhffdhfh@yandex.com");
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + "Next" + "\").instance(0)").click();

        MobileElement pwInput = (MobileElement) driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + "Password" + "\").instance(0)");
        pwInput.sendKeys("TystusO4523");
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + "Next" + "\").instance(0)").click();

        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign up without uploading my contacts\"]")).click();

    }

    @Ignore
    @Test
    public void login() {
        try {
            MobileElement el1 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[@content-desc=\"Username\"]");
            el1.sendKeys("romanivanov726@mail.ru");
            MobileElement el2 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[@content-desc=\"Password\"]");
            el2.sendKeys("romanIv0125");

            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Login\"]")).click();
            fb.customWait(10);
        }
        catch(NoSuchElementException exp) {
            System.out.println("We have already made log in");
        }
    }

    @Ignore
    @Test
    public void logOut() {
        fb.enterMainMenu();
        //click log out button
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().description(\""+"Log Out, Button 1 of 1"+"\").instance(0))").click();
        fb.customWait(2);
        try {
            driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Log Out\")").click();
            fb.customWait(2);
        }
        catch (NoSuchElementException exp) {
            System.out.println("Facebook did not display logout menu so do nothing");
        }

    }
}
