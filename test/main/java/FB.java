package main.java;
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
import org.junit.*;
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

    private static AndroidDriver driver;
    private static boolean setUpIsDone = false;
    private static boolean notLastTest = true;

    @Before
    public void setUp() throws MalformedURLException {
        if(setUpIsDone) {
            return;
        }
       DesiredCapabilities desiredCapabilities = new DesiredCapabilities();


        desiredCapabilities.setCapability( MobileCapabilityType.DEVICE_NAME, "192.168.51.101:5555"); //emulator
       // desiredCapabilities.setCapability(MobileCapabilityType.APP, "D:\\facebook/Facebook+135.0.0.22.90.zip.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "com.facebook.katana");
        //desiredCapabilities.setCapability("appActivity", "com.facebook.katana.app.FacebookSplashScreenActivity"); //first screen
        //desiredCapabilities.setCapability("appActivity", "com.facebook.katana.dbl.activity.FacebookLoginActivity"); // for old FB version
        desiredCapabilities.setCapability("appActivity", "com.facebook.account.login.activity.SimpleLoginActivity");

        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        //desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0"); //emulator

        desiredCapabilities.setCapability("fastReset", "true");
        desiredCapabilities.setCapability("autoGrantPermissions", "true");
        desiredCapabilities.setCapability("noReset", "false");


        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.NANOSECONDS);
        setUpIsDone = true;
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        customWait(20);
        FBAuth fbAuth = new FBAuth(driver);
        fbAuth.login();
        //fbAuth.register();
    }

    @Test
    public void findFriend() {
        enterMainMenu();
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

        callFBBackButton(3);
    }

    @Test
    public void enterGroup() {
        enterMainMenu();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Groups\"]")).click();
        customWait(2);
        //clicking on search
        try {
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText")).click();
        }
        catch(NoSuchElementException exp) {
            System.out.println("We have an exception: " + exp);
            driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.widget.EditText")).click();

        }
        //use search another search field to enter request
        customWait(2);
        MobileElement searchGroup = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText");
        searchGroup.sendKeys("empty group" +"\n");
        customWait(5);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Groups\"]")).click();
        //Choose ITMO Group
        customWait(2);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup")).click();
        customWait(2);
        //get ask group join menu
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Join Group\"]")).click();
        callFBBackButton(4);
    }


    @Test
    public void videos() {
        enterMainMenu();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Videos on Watch\"]")).click();
        customWait(2);
        MobileElement searchVideo = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.EditText");
        searchVideo.sendKeys("ITMO UNIVERSITY" +"\n");
        customWait(5);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.view.ViewGroup")).click();
        customWait(4);
        boolean success = true;
        while(success) {
            try {
                driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Clear text\"]")).click(); //Click video
                driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Clear text\"]")).click(); // enter menu
                driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Save video, Add this to your saved videos.\"]/android.widget.TextView[1]")).click();
                customWait(1);
                success = false;
            }
            catch (NoSuchElementException exp) {
                System.out.println("Failed to save video. Attempting again....");

            }
        }
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]")).click();
        callAndroidBackButton(7);
    }

    @Test
    public void events() {
        enterMainMenu();
        seeMoreBtn();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Events\"]")).click();
        customWait(2);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Tomorrow\"]")).click();
        customWait(2);
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup")).click();
        customWait(2);
        callFBBackButton(2);
    }

    @Test
    public void collections() {
        seeMoreBtn();
        //enterMainMenu();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Saved\"]")).click();
        customWait(2);
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Create\"]/android.widget.TextView")).click();
        customWait(1);
        //enter collection name
        MobileElement collName = (MobileElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText"));
        collName.sendKeys("TEST");
        customWait(3);
        //click create collection button
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.Button[2]/android.widget.TextView\n")).click();
        customWait(1);
        callAndroidBackButton(2);
    }

    @Test
    public void gaming() {
        enterMainMenu();
        seeMoreBtn();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Gaming\"]")).click();
        customWait(2);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Follow Games\"]")).click();
        customWait(2);
        //follow first game from list
        try {
            driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"Add\"])[1]")).click();
        }
        catch (NoSuchElementException exp){
            System.out.println("Tryin to clck follow button instead of add button");
            driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"Follow\"])[1]")).click();
        }
        customWait(2);
        callFBBackButton(1);
        customWait(2);

        //enter game streamers menu
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Streamers\"]")).click();
        customWait(2);
        //follow first streamer from list
        driver.findElement(By.xpath("(//android.view.ViewGroup[@content-desc=\"Follow\"])[1]")).click();
        //testing of 'You' menu was deprecated
        customWait(2);
        callFBBackButton(2);

    }

    @Test
    public void jobs() {
        enterMainMenu();
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Jobs\"]")).click();
        }
        catch(NoSuchElementException exp) {
            seeMoreBtn();
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Jobs\"]")).click();
        }
        customWait(2);
        //click on settings
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.Button")).click();
        customWait(2);
        //click on jobs which we are interested
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.Button")).click();
        customWait(2);
        //enter job
        MobileElement enterJob = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.EditText");
        enterJob.sendKeys("Cashier");
        customWait(4);
        //click on job which we entered
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.widget.ImageView")).click();
        customWait(2);
        //disable notifications to receive a it when job is available
        driver.findElement(By.xpath("//X.6Nr[@content-desc=\"Toggle button\"]")).click();
        customWait(2);
        //click save button
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.Button[2]/android.widget.TextView")).click();
        customWait(2);
        callFBBackButton(2);
    }

    @Test
    public void messangerInstall() {
        enterMainMenu();
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Install Messenger\"]")).click();
        }
        catch(NoSuchElementException exp) {
            seeMoreBtn();
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Install Messenger\"]")).click();
        }
        customWait(4);
        //click install button in Google Play
        driver.findElement(By.id("com.android.vending:id/right_button")).click();
        customWait(4);
        //click cancel to not wait
        driver.findElement(By.id("com.android.vending:id/left_button")).click();
        customWait(2);
        callAndroidBackButton(1);
        customWait(2);
        callFBBackButton(1);
    }

    @Test
    public void cityGuides() {
        enterMainMenu();
        customWait(2);
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"City Guides\"]")).click();
        }
        catch (NoSuchElementException exp) {
            seeMoreBtn();
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"City Guides\"]")).click();
        }
        customWait(2);
        //click Saint Petersburg button
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")).click();
        customWait(4);
        //press like button for city
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Like\"]/android.view.ViewGroup")).click();
        customWait(2);

        //make check-in
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Check In\"]/android.view.ViewGroup")).click();
        customWait(2);
        //write check in post
        MobileElement checkInPost = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.EditText");
        checkInPost.sendKeys("Saint Petersburg is an awesome city!");
        customWait(5);
        //click post button
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"POST\"]")).click();
        customWait(2);

        //share a city
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Share\"]/android.view.ViewGroup")).click();
        customWait(2);
        //enter Feeling/Activity/Sticker menu
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Feeling/Activity/Sticker\"]")).click();
        customWait(2);
        //enter sticker menu
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Stickers, Tab 2 of 3\"]")).click();
        customWait(4);
        //choose happy sticker
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+"Happy"+"\").instance(0)").click();
        customWait(2);
        //choose a last happy sticker in a list
        driver.findElement(By.xpath("//android.widget.RelativeLayout[@content-desc=\"Sticker 23 of 23\"]/android.widget.ImageView")).click();
        customWait(2);
        MobileElement shareCityPost = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.EditText");
        shareCityPost.sendKeys("What a beautiful city!");
        customWait(5);
        //click post button
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"POST\"]")).click();
        customWait(2);

        //save a city
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Save\"]/android.view.ViewGroup")).click();
        customWait(2);

        //save a first restaurant from a list
        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.Button/android.view.ViewGroup[4]/android.widget.ImageView")).click();
        customWait(2);

        callFBBackButton(2);
    }

    @Test
    public void fundRaisers() {
        enterMainMenu();
        customWait(5);
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Fundraisers\"]")).click();
        }
        catch(NoSuchElementException exp) {
            seeMoreBtn();
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Fundraisers\"]")).click();
        }
        customWait(2);

        //scroll to first fundraiser
        //String scrollTo= "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.Button";
        String scrollTo = "raised of";
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+scrollTo+"\").instance(0))").click();
        customWait(2);
        //click share
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"SHARE\"]")).click();
        customWait(2);
        //share it as post
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Share as Post Share fundraiser in Feed\"]")).click();
        customWait(2);
        driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"POST\"]")).click();
        customWait(2);
        //close share menu
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Close\"]/android.view.ViewGroup")).click();
        customWait(2);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Follow\"]")).click();
        customWait(2);

        callFBBackButton(2);
    }

    @Test
    public void liveVideos() {
        enterMainMenu();
        customWait(5);
        try {
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Live Videos\"]")).click();
        }
        catch(NoSuchElementException exp) {
            seeMoreBtn();
            driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Live Videos\"]")).click();
        }
        customWait(4);
        //like and follow on first post
        try {
            driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\"" + "follow" + "\").instance(0)").click();
        }
        catch(NoSuchElementException exp) {
            try {
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.view.ViewGroup[2]").click();
            }
            catch (NoSuchElementException exp2) {
                System.out.println("Failed to follow");
            }
        }
        customWait(2);
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+"like"+"\").instance(0)").click();
        //click share button
        customWait(2);
        driver.findElementByAndroidUIAutomator("new UiSelector().textContains(\""+"share"+"\").instance(0)").click();
        customWait(2);
        //write post during sharing
        MobileElement shareVideoPost = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.EditText");
        shareVideoPost.sendKeys("Not bad");
        customWait(5);
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"SHARE NOW\"]")).click();
        customWait(2);
        callAndroidBackButton(1);
    }

    @Test
    public void removeSuggestedFriend() {
        try {
            driver.findElementByXPath("//android.view.View[@content-desc=\"Friends, Tab 2 of 6\"]").click();
        }
        catch (NoSuchElementException exp) {
            driver.findElementByXPath("//android.view.View[@content-desc=\"Friends, Tab 2 of 4\"]").click();
        }
        customWait(2);
        List<WebElement> removeButton=driver.findElements(By.className("android.view.ViewGroup"));
        removeButton.get(17).click(); // 15 is to add to friend -> Click remove which removes one friend from list
        notLastTest = false;
    }

    @Ignore
    @Test
    public void testFBShortcut() {
        customWait(2);
        driver.pressKey(new KeyEvent(AndroidKey.HOME)); // press home
        customWait(2);
        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Apps\"]").click();
        customWait(2);
        MobileElement fbApp = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Facebook\"]");
        new TouchAction((MobileDriver) driver).longPress(PointOption.point(450,722)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(5000))).moveTo(PointOption.point(479, 1312)).release().perform();
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



    @After
    public void tearDown() {
        FBAuth fbAuth = new FBAuth(driver);
        if(notLastTest) return;
        fbAuth.logOut();
        testFBShortcut();
        //driver.quit();
    }

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
}
