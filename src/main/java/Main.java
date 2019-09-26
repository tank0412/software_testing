import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Main {
    private static WebDriver driver;
    public static void main(String[] args) {
        System.out.println("Hello World, Lab1!");
        // Optional, if not specified, WebDriver will search your path for chromedriver.
        System.setProperty("webdriver.chrome.driver", "/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize() ;
        driver.get("http://www.tumblr.com/");
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
            tumblrFunc.interractWithPost("http://komanda.tumblr.com/post/186855567115/%D0%BF%D1%80%D0%B8%D0%B2%D0%B5%D1%82-%D1%85%D0%BE%D1%80%D0%BE%D1%88%D0%B0%D1%8F-%D0%BD%D0%BE%D0%B2%D0%BE%D1%81%D1%82%D1%8C-%D1%82%D0%B5%D0%BF%D0%B5%D1%80%D1%8C-%D0%B2%D1%8B-%D0%BC%D0%BE%D0%B6%D0%B5%D1%82%D0%B5");

            tumblrFunc.customWait();
            driver.get("https://www.tumblr.com/dashboard");
            tumblrFunc.postMusic("Кукушка гагарина");
            tumblrFunc.customWait();
            tumblrFunc.postVideo("https://youtu.be/Ra0ozaE-oy0");
            tumblrFunc.customWait();

            tumblrFunc.followAccount("https://fireflysheart.tumblr.com/");
            tumblrFunc.customWait();


            tumblrFunc.blockAccount("https://fireflysheart.tumblr.com/");
            driver.get("https://www.tumblr.com/dashboard");
            tumblrFunc.customWait();
            tumblrFunc.postText("Test");
            tumblrFunc.customWait();
            tumblrFunc.postImage("D:/logoITMO_rus.png");
            tumblrFunc.customWait();
            tumblrFunc.postQuote("Главная проблема цитат в интернете в том, что люди сразу верят в их подлинность", "Джейсон Стейтем" );
            tumblrFunc.customWait();
            tumblrFunc.postLink("google.ru");
            tumblrFunc.customWait();
            tumblrFunc.postChat("деплоймент чат\n" +
                    "\n" +
                    "витя: коллеги, у нас на вечер запланирован релиз. нам нужно от вас ответственное лицо, чтобы кто-то мог нас подстраховать если что.\n" +
                    "Алексей Егоров: Если что?\n" +
                    "витя: если что-то пойдет не так.\n" +
                    "Алексей Егоров: Если что пойдёт не так?\n" +
                    "nikki: Коллеги, в самом деле, какого рода проблемы вы ожидаете? Релиз протестирован?\n" +
                    "витя: слушайте, протестирован. но если что-то будет с тестами, может потребоваться ваше содействие!\n" +
                    "Алексей Егоров: Если что будет с тестами?\n" +
                    "\n" +
                    "продакшн чат\n" +
                    "\n" +
                    "maxpatrol: отличный бот\n" +
                    "nikki: да\n" +
                    "zoppo2: мне кажется, они начнут догадываться, когда Алексей Егоров не явится на третий подряд корпоратив\n" +
                    "maxpatrol: сомневаюсь\n" +
                    "Cthuthu: не при нашей текучке кадров\n" +
                    "stty: да\n" +
                    "nikki: да");
        }
        catch(UnhandledAlertException  e) {
            //do nothing
        }
        afterTest(tumblrFunc);
        return;
    }

    static void afterTest(TumblrFunc tumblrFunc) {
        tumblrFunc.customWait();
        tumblrFunc.unfollowAccount("https://fireflysheart.tumblr.com/");
        tumblrFunc.customWait();
        tumblrFunc.unlikePost("https://komanda.tumblr.com/post/186855567115/%D0%BF%D1%80%D0%B8%D0%B2%D0%B5%D1%82-%D1%85%D0%BE%D1%80%D0%BE%D1%88%D0%B0%D1%8F-%D0%BD%D0%BE%D0%B2%D0%BE%D1%81%D1%82%D1%8C-%D1%82%D0%B5%D0%BF%D0%B5%D1%80%D1%8C-%D0%B2%D1%8B-%D0%BC%D0%BE%D0%B6%D0%B5%D1%82%D0%B5");
    }
}
