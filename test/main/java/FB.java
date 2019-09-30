import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.ListensToLogcatMessages;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FB {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
       DesiredCapabilities desiredCapabilities = new DesiredCapabilities();


        desiredCapabilities.setCapability( MobileCapabilityType.DEVICE_NAME, " 192.168.51.101:5555");

       // desiredCapabilities.setCapability(MobileCapabilityType.APP, "D:\\facebook/Facebook+135.0.0.22.90.zip.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "com.facebook.katana");
        //desiredCapabilities.setCapability("appActivity", "com.facebook.katana.app.FacebookSplashScreenActivity");
        desiredCapabilities.setCapability("appActivity", "com.facebook.katana.dbl.activity.FacebookLoginActivity");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        //desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, "true");


        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.NANOSECONDS);
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void login() {
        customWait();

        MobileElement el1 = (MobileElement) driver.findElementById("com.facebook.katana:id/login_username");
        el1.sendKeys("romanivanov726@mail.ru");
        MobileElement el2 = (MobileElement) driver.findElementById("com.facebook.katana:id/login_password");
        el2.sendKeys("romanIv0125");

    
        driver.findElement(By.id("com.facebook.katana:id/login_login")).click();

        //driver.resetApp();



    }

    @After
    public void tearDown() {

        //driver.quit();
    }

    void customWait() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
