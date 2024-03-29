import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class TumblrAuth {
    private WebDriver driver;
    public TumblrAuth(WebDriver testDriver) {
        driver = testDriver;
    }

    void register() {
        WebElement registerButton = driver.findElement(By.id("signup_forms_submit"));
        registerButton.click();
        //((ChromeDriver) driver).findElementByXPath("//span[contains(.,'Зарегистрироваться')]").click();
        WebElement elEmail = driver.findElement(By.id("signup_email"));
        elEmail.sendKeys("asdmasdlqsd@yandex.com");
        WebElement elPwd = driver.findElement(By.id("signup_password"));
        elPwd.sendKeys("BlnSNs@1398");
        WebElement elNick = driver.findElement(By.id("signup_username"));
        elNick.sendKeys("testlqspor"); //Test89456
        registerButton.click();
        WebElement elAge = driver.findElement(By.id("signup_age"));
        elAge.sendKeys("18");
        WebElement element = ((ChromeDriver) driver).findElementByXPath("//input[@id='signup_tos']");
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        registerButton.click();
        postRegister();
    }
    void login() {
        WebElement loginButton = driver.findElement(By.id("signup_login_button"));
        loginButton.click();
        WebElement elEmail = driver.findElement(By.id("signup_determine_email"));
        elEmail.sendKeys("roreta@w6mail.com"); // login - LpiNKEfl
        try {
            loginButton.click();
        }
        catch (StaleElementReferenceException exception) {
            System.out.println("Exception proceded " + exception);
            WebElement loginButtonExp = driver.findElement(By.id("account_actions_login_and_register"));
            loginButtonExp.click();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        WebElement logWithpwd = driver.findElement(By.className("magiclink_password_container"));
        try {
            logWithpwd.click();
        }
        catch (ElementClickInterceptedException exception) {
            System.out.println("Exception proceded #2 " + exception);
            try {
                WebElement loginButtonExp = driver.findElement(By.cssSelector(".account_actions_login_and_register.chrome"));
                loginButtonExp.click();
            }
            catch (NoSuchElementException exp) {
                System.out.println("Exception proceded #3 " + exp);
                logWithpwd.click();
            }
        }
        WebElement elPwd = driver.findElement(By.id("signup_password"));
        elPwd.sendKeys("l4oKDSxGulVY");
        WebElement finalLogButton = driver.findElement(By.id("signup_forms_submit"));
        finalLogButton.click();
    }

    void postRegister() {
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS) ;
        WebElement elementHandMade = ((ChromeDriver) driver).findElementByXPath("//div[@data-name='handmade']");
        //System.out.println("Trying to clck");
        elementHandMade.click();
        WebElement elementStudent = ((ChromeDriver) driver).findElementByXPath("//div[@data-name='student']");
        elementStudent.click();
        WebElement elementTravel = ((ChromeDriver) driver).findElementByXPath("//div[@data-name='travel']");
        elementTravel.click();
        WebElement elementNature= ((ChromeDriver) driver).findElementByXPath("//div[@data-name='nature']");
        elementNature.click();
        WebElement elementMusicians = ((ChromeDriver) driver).findElementByXPath("//div[@data-name='musicians']");
        elementMusicians.click();
        WebElement nextButton = driver.findElement(By.className("onboarding-progress-button"));
        nextButton.click();
    }

    void runPostRegister() {
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS) ;
        driver.get("http://www.tumblr.com/getting_to_know_tumblr/");
        postRegister();
    }
}
