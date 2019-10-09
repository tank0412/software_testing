import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Main implements Constants {
    private static WebDriver driver;
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize() ;
        driver.get(TUMBLRURL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        TumblrFunc tumblrFunc = new TumblrFunc(driver);
        TumblrPanel tumblrPanel = new TumblrPanel(driver);
        TumblrAuth tumblrAuth = new TumblrAuth(driver);
        //tumblrAuth.register(); // When we do not have account
        tumblrAuth.login(); // When we have account
        tumblrFunc.customWait();
        try {
          //tumblrAuth.runPostRegister(); // To test postRegister after login
            tumblrFunc.runPartOne();
            tumblrPanel.run();
            tumblrFunc.runPartTwo();
        }
        catch(UnhandledAlertException e) {
            System.out.println("Exception" + e.getMessage());
        }
        afterTest(tumblrFunc);
    }

    static void afterTest(TumblrFunc tumblrFunc) {
        tumblrFunc.customWait();
        tumblrFunc.unfollowAccount(FOLLOWACCOUNTURL);
        tumblrFunc.customWait();
        tumblrFunc.unlikePost(POSTINTERRACTURL);
    }
}
