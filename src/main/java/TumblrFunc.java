import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class TumblrFunc {
    private WebDriver driver;
    public TumblrFunc(WebDriver testDriver) {
        driver = testDriver;
    }
    void customWait() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void register() {
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
    void login() {
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
    void interractWithPost(String url) {
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
        customWait();
        try {
            congirmReblog.click(); // click confirm reblog
        }
        catch(ElementClickInterceptedException exp) {
            System.out.println("Exception proceded #5");
            Actions actions = new Actions(driver);
            actions.moveToElement(congirmReblog).click().build().perform();
        }
    }
    void followAccount(String url) {
        driver.get(url);
        driver.switchTo().frame("unified-controls");
        //WebElement followButton = driver.findElement(By.cssSelector(".tx-button.follow-button "));
        WebElement followButton = driver.findElement(By.xpath("//button[contains(.,'Читать')]"));
        followButton.click(); // click like
    }
    void blockAccount(String url) {
        driver.get(url);
        driver.switchTo().frame("unified-controls");
        //WebElement blockButton = driver.findElement(By.xpath("//button[contains(.,'Заблокировать')]"));
        WebElement blogButton =  driver.findElement(By.cssSelector(".tx-icon-button.snowman-button.non-essential"));
        blogButton.click();
        WebElement blockButton =  driver.findElement(By.cssSelector(".tx-button.tx-button--tertiary.block-button"));
        blockButton.click(); // click like
    }
    void postText(String test) {
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
    void postImage(String imageUrl) {
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
    void postQuote(String quote, String author) {
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
    void postLink(String url) {
        WebElement postLink= ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_link']");
        postLink.click();
        WebElement elTextBody = driver.findElement(By.cssSelector(".editor.editor-plaintext")); //Ссылка
        elTextBody.sendKeys(url);
        customWait();
        WebElement postTextButton = driver.findElement(By.cssSelector(".button-area.create_post_button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(postTextButton).click().build().perform();
    }
    void postChat(String chat) {
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
    void postMusic(String musicName) {
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
    void postVideo(String videoUrl) {
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
    void unfollowAccount(String url) {
        driver.get(url);
        driver.switchTo().frame("unified-controls");
        //WebElement blockButton = driver.findElement(By.xpath("//button[contains(.,'Заблокировать')]"));
        WebElement blogButton =  driver.findElement(By.cssSelector(".tx-icon-button.snowman-button.non-essential"));
        blogButton.click();
        //WebElement blockButton =  driver.findElement(By.cssSelector(".tx-button.tx-button--tertiary.unfollow-button "));
        //blockButton.click(); // click like
    }
    void unlikePost(String url) {
        driver.get(url);
        //driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.switchTo().frame("unified-controls");
        WebElement likePost = driver.findElement(By.cssSelector(".tx-icon-button.unlike-button"));
        likePost.click(); // click like
    }
}
