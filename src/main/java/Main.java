import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Main implements Constants {
    private static WebDriver driver;
    public static void main(String[] args) {
        System.out.println("Hello World, Lab1!");
        // Optional, if not specified, WebDriver will search your path for chromedriver.
        System.setProperty("webdriver.chrome.driver", "/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize() ;
        driver.get(tumblrUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        TumblrFunc tumblrFunc = new TumblrFunc(driver);
        //register(); // When we do not have account
        tumblrFunc.login(); // When we have account
        //driver.quit();
        tumblrFunc.customWait();
        try {
        /*
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS) ;
        driver.get("http://www.tumblr.com/getting_to_know_tumblr/");
        postRegister();
        */ // To test postRegister after login
            tumblrFunc.interractWithPost(postInterractUrl);

            tumblrFunc.customWait();
            driver.get(tumblrDashboardUrl);
            tumblrFunc.postMusic(songName);
            tumblrFunc.customWait();
            tumblrFunc.postVideo(videoUrl);
            tumblrFunc.customWait();

            tumblrFunc.followAccount(followAccountUrl);
            tumblrFunc.customWait();


            tumblrFunc.blockAccount(followAccountUrl);
            driver.get(tumblrDashboardUrl);
            tumblrFunc.customWait();
            tumblrFunc.postText(text);
            tumblrFunc.customWait();
            tumblrFunc.postImage(imageUrl);
            tumblrFunc.customWait();
            tumblrFunc.postQuote(quoteText, quoteAuthor );
            tumblrFunc.customWait();
            tumblrFunc.postLink(postUrl);
            tumblrFunc.customWait();
            tumblrFunc.postChat(chat);
        }
        catch(UnhandledAlertException e) {
            System.out.println("Exception" + e);
        }
        afterTest(tumblrFunc);
    }

    static void afterTest(TumblrFunc tumblrFunc) {
        tumblrFunc.customWait();
        tumblrFunc.unfollowAccount(followAccountUrl);
        tumblrFunc.customWait();
        tumblrFunc.unlikePost(postInterractUrl);
    }
}
