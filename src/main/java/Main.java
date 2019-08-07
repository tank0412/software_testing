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
        //register(); // When we do not have account
        login(); // When we have account
        customWait();
        //driver.quit();
        return;
    }
    static void customWait() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static void register() {
        WebElement registerButton = driver.findElement(By.id("signup_forms_submit"));
        registerButton.click();
        //((ChromeDriver) driver).findElementByXPath("//span[contains(.,'Зарегистрироваться')]").click();
        WebElement elEmail = driver.findElement(By.id("signup_email"));
        elEmail.sendKeys("asdmasdlasd@yandex.com");
        WebElement elPwd = driver.findElement(By.id("signup_password"));
        elPwd.sendKeys("BlnSNs@1398");
        WebElement elNick = driver.findElement(By.id("signup_username"));
        elNick.sendKeys("testlmspor"); //Test89456
        registerButton.click();
        WebElement elAge = driver.findElement(By.id("signup_age"));
        elAge.sendKeys("18");
        WebElement element = ((ChromeDriver) driver).findElementByXPath("//input[@id='signup_tos']");
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        registerButton.click();
    }
    static void login() {
        WebElement loginButton = driver.findElement(By.id("signup_login_button"));
        loginButton.click();
        WebElement elEmail = driver.findElement(By.id("signup_determine_email"));
        elEmail.sendKeys("fivadimuf@armail.in");
        try {
            loginButton.click();
        }
        catch (StaleElementReferenceException exception) {
            System.out.println("Exception proceded");
            WebElement loginButtonExp = driver.findElement(By.id("account_actions_login_and_register"));
            loginButtonExp.click();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        WebElement logWithpwd = driver.findElement(By.className("magiclink_password_container"));
        try {
            logWithpwd.click();
        }
        catch (ElementClickInterceptedException exception) {
            System.out.println("Exception proceded #2");
            WebElement loginButtonExp = driver.findElement(By.cssSelector(".account_actions_login_and_register.chrome"));
            loginButtonExp.click();
        }
        WebElement elPwd = driver.findElement(By.id("signup_password"));
        elPwd.sendKeys("l4oKDSxGulVY");
        WebElement finalLogButton = driver.findElement(By.id("signup_forms_submit"));
        finalLogButton.click();

    }
}
