import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.ListensToLogcatMessages;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;

public class FB {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
       DesiredCapabilities desiredCapabilities = new DesiredCapabilities();


        desiredCapabilities.setCapability( MobileCapabilityType.DEVICE_NAME, " 192.168.51.101:5555");

       // desiredCapabilities.setCapability(MobileCapabilityType.APP, "D:\\facebook/Facebook+135.0.0.22.90.zip.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "com.facebook.katana");
        //desiredCapabilities.setCapability("appActivity", "com.facebook.katana.app.FacebookSplashScreenActivity"); //first screen
        //desiredCapabilities.setCapability("appActivity", "com.facebook.katana.dbl.activity.FacebookLoginActivity"); // for old FB version
        desiredCapabilities.setCapability("appActivity", "com.facebook.account.login.activity.SimpleLoginActivity");

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        //desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, "true");


        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.NANOSECONDS);
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Ignore
    @Test
    public void register() {
        customWait(10);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Create New Facebook Account\"]")).click();
        customWait(10);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.Button")).click();
        /*
        try {
            driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click(); // It ask for permissions during first sign up only
            driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
        }
        catch(NoSuchElementException exp) {
            System.out.println("We have an exception " + exp );
        }
        */

        //driver.findElementById("com.google.android.gms:id/cancel").click();


        MobileElement el1 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText");
        el1.sendKeys("Ivan");
        customWait(10);

        MobileElement el2 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText");
        el2.sendKeys("Ivanov");
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button")).click();
        customWait(10);
        TouchAction touchAction = new TouchAction(driver);
        touchAction.longPress(PointOption.point(726, 490)).perform(); //or tap
        customWait(5);
        touchAction.longPress(PointOption.point(465, 492)).perform();
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button")).click();

        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.RadioGroup/android.widget.RadioButton[2]")).click();
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button")).click();

        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[3]/android.widget.Button")).click();

        MobileElement emailInput = (MobileElement) driver.findElementByXPath("//android.widget.EditText[@content-desc=\"Email Address\"]");
        emailInput.sendKeys("adasddfeadmhffdhfh@yandex.com");
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button")).click();

        MobileElement pwInput = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.EditText");
        pwInput.sendKeys("TystusO4523");
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button")).click();

        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Sign up without uploading my contacts\"]")).click();

    }

    @Test
    public void login() {
        customWait(10);

        MobileElement el1 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[@content-desc=\"Username\"]");
        el1.sendKeys("romanivanov726@mail.ru");
        MobileElement el2 = (MobileElement) driver.findElementByXPath("//android.widget.EditText[@content-desc=\"Password\"]");
        el2.sendKeys("romanIv0125");

        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Login\"]")).click();
    }

    @Test
    public void findFriend() {
        customWait(5);
        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiSelector().resourceId(\"com.facebook.katana:id/(name removed)\").instance(1)")).click(); // enter friends menu
        customWait(2);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Find Friends\"]")).click();
        customWait(2);
        //clicking on search
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText")).click();
        //use search another search field to enter request
        customWait(2);
        MobileElement searchFriends = (MobileElement) driver.findElement(By.xpath(" /hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText"));
        searchFriends.sendKeys("Roman Bukhtiarov");
        customWait(10);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"See results for roman bukhtiarov\"]")).click();
        driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"Add friend request\"])[1]")).click();

        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Back\"]")).click(); //exit
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Back\"]")).click();
        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Back\"]")).click();
    }





    @After
    public void tearDown() {

        //driver.quit();
    }

    void customWait(int digit) {
        try {
            Thread.sleep(digit * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
