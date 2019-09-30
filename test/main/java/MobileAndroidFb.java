import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.remote.DesiredCapabilities;


import java.net.MalformedURLException;
import java.net.URL;


public class MobileAndroidFb {

    private AndroidDriver driver;
    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "192.168.108.101:5555");
        desiredCapabilities.setCapability("automationName", "appium");
        desiredCapabilities.setCapability("app", "C:\\apk\\vk-4-1.apk");
        desiredCapabilities.setCapability("platformName", "android");
       // desiredCapabilities.setCapability("package", "com.vkontakte.android");

        URL remoteUrl= new URL("http://localhost:4723/wd/hub");

        driver=new AndroidDriver(remoteUrl, desiredCapabilities);
    }
    @Test
    public void sampleTest() {
        //MobileElement el1 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout");
       // el1.sendKeys("+79500414896");
        //MobileElement el2 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout");
       // el2.sendKeys("ataullah1234");



    }

    @After
    public void tearDown() {

        driver.quit();
    }
}
