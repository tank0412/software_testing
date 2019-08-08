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
        postRegister();
    }
    static void login() {
        WebElement loginButton = driver.findElement(By.id("signup_login_button"));
        loginButton.click();
        WebElement elEmail = driver.findElement(By.id("signup_determine_email"));
        elEmail.sendKeys("fivadimuf@armail.in"); // login - LpiNKEfl
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
            try {
                WebElement loginButtonExp = driver.findElement(By.cssSelector(".account_actions_login_and_register.chrome"));
                loginButtonExp.click();
            }
            catch (NoSuchElementException exp) {
                System.out.println("Exception proceded #3");
                logWithpwd.click();
            }
        }
        WebElement elPwd = driver.findElement(By.id("signup_password"));
        elPwd.sendKeys("l4oKDSxGulVY");
        WebElement finalLogButton = driver.findElement(By.id("signup_forms_submit"));
        finalLogButton.click();
        /*
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS) ;
        driver.get("http://www.tumblr.com/getting_to_know_tumblr/");
        postRegister();
        */ // To test postRegister after login

        //interractWithPost("http://komanda.tumblr.com/post/186855567115/%D0%BF%D1%80%D0%B8%D0%B2%D0%B5%D1%82-%D1%85%D0%BE%D1%80%D0%BE%D1%88%D0%B0%D1%8F-%D0%BD%D0%BE%D0%B2%D0%BE%D1%81%D1%82%D1%8C-%D1%82%D0%B5%D0%BF%D0%B5%D1%80%D1%8C-%D0%B2%D1%8B-%D0%BC%D0%BE%D0%B6%D0%B5%D1%82%D0%B5");
        //followAccount("https://picsthatmakeyougohmm.tumblr.com/");
        //blockAccount("https://fireflysheart.tumblr.com/");

        //postText("Test");
        //postImage("D:/logoITMO_rus.png");
        //postQuote("Главная проблема цитат в интернете в том, что люди сразу верят в их подлинность", "Джейсон Стейтем" );
        //postLink("google.ru");
        /*
        postChat("деплоймент чат\n" +
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

       */
        //postMusic("Кукушка гагарина");
        postVideo("https://youtu.be/Ra0ozaE-oy0");
    }

    static void postRegister() {
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
    static void interractWithPost(String url) {
        driver.get(url);
        //driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.switchTo().frame("unified-controls");
        WebElement likePost = driver.findElement(By.cssSelector(".tx-icon-button.like-button"));
        likePost.click(); // click like
        try {
            WebElement reblogPost = driver.findElement(By.cssSelector(".tx-icon-button.reblog-button"));
            reblogPost.click(); // click reblog
        }
        catch (ElementNotInteractableException exp) {
            System.out.println("Exception proceded #4");
            WebElement reblogPostAnother = ((ChromeDriver) driver).findElementByXPath("//a[@aria-label='Реблог']");
            reblogPostAnother.click(); // call reblog
        }
        //WebElement congirmReblog = driver.findElement(By.xpath("//button[contains(.,'Реблог')]"));
        //WebElement congirmReblog = driver.findElement(By.cssSelector(".post-form--save-button"));

        WebElement congirmReblog = driver.findElement(By.cssSelector(".button-area.create_post_button"));
        try {
            congirmReblog.click(); // click confirm reblog
        }
        catch(ElementClickInterceptedException exp) {
            System.out.println("Exception proceded #5");
            Actions actions = new Actions(driver);
            actions.moveToElement(congirmReblog).click().build().perform();
        }
    }
    static void followAccount(String url) {
        driver.get(url);
        driver.switchTo().frame("unified-controls");
        //WebElement followButton = driver.findElement(By.cssSelector(".tx-button.follow-button "));
        WebElement followButton = driver.findElement(By.xpath("//button[contains(.,'Читать')]"));
        followButton.click(); // click like
    }
    static void blockAccount(String url) {
        driver.get(url);
        driver.switchTo().frame("unified-controls");
        //WebElement blockButton = driver.findElement(By.xpath("//button[contains(.,'Заблокировать')]"));
        WebElement blogButton =  driver.findElement(By.cssSelector(".tx-icon-button.snowman-button.non-essential"));
        blogButton.click();
        WebElement blockButton =  driver.findElement(By.cssSelector(".tx-button.tx-button--tertiary.block-button"));
        blockButton.click(); // click like
    }
    static void postText(String test) {
        WebElement postText = ((ChromeDriver) driver).findElementByXPath("//a[@data-post-endpoint='text']");
        postText.click();
        WebElement elTextBody = driver.findElement(By.cssSelector(".editor.editor-richtext"));
        elTextBody.sendKeys("Test");
        WebElement elTextheading = driver.findElement(By.cssSelector(".editor.editor-plaintext"));
        elTextheading.sendKeys("Test heading");
        WebElement postTextButton = driver.findElement(By.cssSelector(".button-area.create_post_button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(postTextButton).click().build().perform();
    }
    static void postImage(String imageUrl) {
        WebElement postImagr = ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_photo']"); //use another way to clck button
        postImagr.click();
        WebElement elPhotoUpload = ((ChromeDriver) driver).findElementByXPath("//input[@name='photo']");
        elPhotoUpload.sendKeys(imageUrl);
        customWait();
        WebElement postPhotoButton = driver.findElement(By.cssSelector(".button-area.create_post_button"));
        //WebElement postPhotoButton = driver.findElement(By.xpath("//button[contains(.,'Опубликовать')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(postPhotoButton).click().build().perform();
    }
    static void postQuote(String quote, String author) {
        WebElement postQuote= ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_quote']");
        postQuote.click();
        WebElement elTextBody = driver.findElement(By.cssSelector(".editor.editor-plaintext")); //Цитата
        elTextBody.sendKeys(quote);

        WebElement elTextheading = driver.findElement(By.cssSelector(".editor.editor-richtext"));
        elTextheading.sendKeys(author);
        WebElement postTextButton = driver.findElement(By.cssSelector(".button-area.create_post_button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(postTextButton).click().build().perform();
    }
    static void postLink(String url) {
        WebElement postLink= ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_link']");
        postLink.click();
        WebElement elTextBody = driver.findElement(By.cssSelector(".editor.editor-plaintext")); //Ссылка
        elTextBody.sendKeys(url);
        customWait();
        WebElement postTextButton = driver.findElement(By.cssSelector(".button-area.create_post_button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(postTextButton).click().build().perform();
    }
    static void postChat(String chat) {
        WebElement postChat = ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_chat']");
        postChat.click();
        //WebElement elTextBody = driver.findElement(By.cssSelector(".editor.editor-plaintext")); //Ссылка
        WebElement elTextBody = ((ChromeDriver) driver).findElementByXPath("//div[@aria-label='Текст поста']");
        elTextBody.sendKeys(chat);
        customWait();
        WebElement postTextButton = driver.findElement(By.cssSelector(".button-area.create_post_button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(postTextButton).click().build().perform();
    }
    static void postMusic(String musicName) {
        WebElement postMusic = ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_audio']");
        postMusic.click();
        WebElement elTextBody = ((ChromeDriver) driver).findElementByXPath("//div[@aria-label='Найдите песню или вставьте URL-адрес']");
        elTextBody.sendKeys(musicName);
        WebElement getMusicResult = driver.findElement(By.cssSelector(".result.audio-result"));
        getMusicResult.click();
        customWait();
        WebElement postTextButton = driver.findElement(By.cssSelector(".button-area.create_post_button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(postTextButton).click().build().perform();
    }
    static void postVideo(String videoUrl) {
        WebElement postVideo = ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_video']");
        postVideo.click();
        WebElement elTextBody = driver.findElement(By.cssSelector(".split-cell.media-url-button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(elTextBody).click().build().perform();
        WebElement elTextBodyUrl = driver.findElement(By.cssSelector(".editor.editor-plaintext")); //Ссылка на видео
        elTextBodyUrl.sendKeys(videoUrl);
        elTextBodyUrl.sendKeys(Keys.ENTER);
        customWait();
        WebElement postTextButton = driver.findElement(By.cssSelector(".button-area.create_post_button"));
        actions = new Actions(driver);
        actions.moveToElement(postTextButton).click().build().perform();
    }
}
