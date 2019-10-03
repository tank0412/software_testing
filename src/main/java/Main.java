import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Main implements Constants {
    private static WebDriver driver;
    public static void main(String[] args) {
        System.out.println("Hello World, Lab1!");
        System.setProperty("webdriver.chrome.driver", "/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize() ;
        driver.get(tumblrUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        TumblrFunc tumblrFunc = new TumblrFunc(driver);
        TumblrPanel tumblrPanel = new TumblrPanel(driver);
        TumblrAuth tumblrAuth = new TumblrAuth(driver);
        //tumblrAuth.register(); // When we do not have account
        tumblrAuth.login(); // When we have account
        tumblrFunc.customWait();
        try {
        /*
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS) ;
        driver.get("http://www.tumblr.com/getting_to_know_tumblr/");
        tumblrAuth.postRegister();
        */ // To test postRegister after login


            tumblrFunc.interractWithPost(postInterractUrl);

            tumblrFunc.customWait();
            driver.get(tumblrDashboardUrl);

            tumblrFunc.followAccount(followAccountUrl);
            tumblrFunc.customWait();


            tumblrFunc.blockAccount(followAccountUrl);
            driver.get(tumblrDashboardUrl);
            tumblrFunc.customWait();


            tumblrPanel.testPostPanel();

            tumblrPanel.testAnotherPostPanel();


            tumblrFunc.followRecommendedBlog();
            tumblrFunc.customWait();
            tumblrFunc.followRadarBlog();
            tumblrFunc.customWait();

            driver.get(tumblrDashboardUrl);
            tumblrFunc.customWait();
            tumblrFunc.searchAndLikeAndReblog();

            tumblrFunc.customWait();
            tumblrFunc.sendNewMessage();
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
