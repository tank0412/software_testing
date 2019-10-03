import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class TumblrFunc implements Constants {
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
    void longCustomWait() {
        try {
            Thread.sleep(20000);
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
        WebElement elTextBody = driver.findElement(By.cssSelector(".editor.editor-richtext"));
        elTextBody.sendKeys("Test");
        WebElement elTextheading = driver.findElement(By.cssSelector(".editor.editor-plaintext"));
        elTextheading.sendKeys("Test heading");
        WebElement postTextButton = driver.findElement(By.cssSelector(".button-area.create_post_button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(postTextButton).click().build().perform();
    }
    void postImage(String imageUrl) {
        WebElement elPhotoUpload = ((ChromeDriver) driver).findElementByXPath("//input[@name='photo']");
        elPhotoUpload.sendKeys(imageUrl);
        pressCreatePostButton();
    }
    void postQuote(String quote, String author) {
        WebElement elTextBody = driver.findElement(By.cssSelector(".editor.editor-plaintext")); //Цитата
        elTextBody.sendKeys(quote);

        WebElement elTextheading = driver.findElement(By.cssSelector(".editor.editor-richtext"));
        elTextheading.sendKeys(author);
        WebElement postTextButton = driver.findElement(By.cssSelector(".button-area.create_post_button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(postTextButton).click().build().perform();
    }
    void postLink(String url) {
        WebElement elTextBody = driver.findElement(By.cssSelector(".editor.editor-plaintext")); //Ссылка
        elTextBody.sendKeys(url);
        pressCreatePostButton();
    }
    void postChat(String chat) {
        //WebElement elTextBody = driver.findElement(By.cssSelector(".editor.editor-plaintext")); //Ссылка
        WebElement elTextBody = ((ChromeDriver) driver).findElementByXPath("//div[@aria-label='Текст поста']");
        elTextBody.sendKeys(chat);
        pressCreatePostButton();
    }
    void postMusic(String musicName) {
        WebElement elTextBody = ((ChromeDriver) driver).findElementByXPath("//div[@aria-label='Найдите песню или вставьте URL-адрес']");
        elTextBody.sendKeys(musicName);
        WebElement getMusicResult = driver.findElement(By.cssSelector(".result.audio-result"));
        getMusicResult.click();
        pressCreatePostButton();
    }
    void postVideo(String videoUrl) {
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
    void pressCreatePostButton() {
        customWait();
        WebElement postTextButton = driver.findElement(By.cssSelector(".button-area.create_post_button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(postTextButton).click().build().perform();
    }
    void testPostPanel() {
        WebElement postMusic = ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_audio']");
        postMusic.click();
        postMusic(songName);
        customWait();
        WebElement postText = ((ChromeDriver) driver).findElementByXPath("//a[@data-post-endpoint='text']");
        postText.click();
        postText(text);
        customWait();
        WebElement postImage = ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_photo']"); //use another way to clck button
        postImage.click();
        postImage(imageUrl);
        customWait();
        WebElement postQuote= ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_quote']");
        postQuote.click();
        postQuote(quoteText, quoteAuthor);
        longCustomWait();
        WebElement postVideo = ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_video']");
        postVideo.click();
        postVideo(videoUrl);
        customWait();
        WebElement postChat = ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_chat']");
        postChat.click();
        postChat(chat);
        customWait();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
        WebElement postLink = ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_link']");
        postLink.click();
        postLink(postUrl);
        customWait();

    }
    void testAnotherPostPanel() {
        composeBtnClck();
        WebElement postText = ((ChromeDriver) driver).findElementByXPath("//div[@data-post-type='text']");
        postText.click();
        postText(text);
        customWait();
        composeBtnClck();
        WebElement postPhoto = ((ChromeDriver) driver).findElementByXPath("//div[@data-post-type='photo']");
        postPhoto.click();
        postImage(imageUrl);
        customWait();
        composeBtnClck();
        WebElement postQuote = ((ChromeDriver) driver).findElementByXPath("//div[@data-post-type='quote']");
        postQuote.click();
        postQuote(quoteText, quoteAuthor);
        customWait();
        composeBtnClck();
        WebElement postVideo = ((ChromeDriver) driver).findElementByXPath("//div[@data-post-type='video']");
        postVideo.click();
        postVideo(videoUrl);
        customWait();
        deleteFirstPost();
        customWait();
        driver.get(tumblrDashboardUrl);
        customWait();
        composeBtnClck();
        WebElement postChat = ((ChromeDriver) driver).findElementByXPath("//div[@data-post-type='chat']");
        postChat.click();
        postChat(chat);
        customWait();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
        composeBtnClck();
        WebElement postAudio = ((ChromeDriver) driver).findElementByXPath("//div[@data-post-type='audio']");
        postAudio.click();
        postMusic(songName);
        customWait();
        deleteFirstPost();
        customWait();
        driver.get(tumblrDashboardUrl);
        customWait();
        composeBtnClck();
        WebElement postLink = ((ChromeDriver) driver).findElementByXPath("//div[@data-post-type='link']");
        postLink.click();
        postLink(postUrl);
        customWait();

    }
    void composeBtnClck() {
        WebElement postbtn = driver.findElement(By.className("compose-button"));
        postbtn.click();
    }
    void deleteFirstPost() {
        WebElement postSettings = driver.findElement(By.cssSelector(".post_control.post-control-icon.post_control_menu.creator"));
        postSettings.click();
        WebElement postdelete = driver.findElement(By.cssSelector(".post_control.delete.show_label"));
        postdelete.click();
        WebElement okButton= ((ChromeDriver) driver).findElementByXPath("//button[@data-btn-id='1']");
        //okButton.click();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", okButton);
    }
    void followRecommendedBlog() {
        WebElement blogLink = ((ChromeDriver) driver).findElementByXPath("//a[@title='Читать']");
        //blogLink.click();
        Actions actions = new Actions(driver);
        actions.moveToElement(blogLink).click().build().perform();
    }
    void followRadarBlog() {
        WebElement blogLink = ((ChromeDriver) driver).findElementByClassName("radar_avatar");
        Actions actions = new Actions(driver);
        actions.moveToElement(blogLink).click().build().perform();
        WebElement followButton= ((ChromeDriver) driver).findElementByCssSelector(".chrome.follow");
        try {
            actions.moveToElement(followButton).click().build().perform();
        }
        catch(JavascriptException exp) {// if we have already followed this radar blog
            System.out.println("We have an exception " + exp);
        }
    }

    void searchAndLikeAndReblog() {
        WebElement search = ((ChromeDriver) driver).findElementByCssSelector(".search_query.popover_button");
        search.sendKeys("gaming");
        search.sendKeys(Keys.ENTER);
        WebElement likeFirstPost = ((ChromeDriver) driver).findElementByCssSelector(".post_control.post-control-icon.like");
        Actions actions = new Actions(driver);
        actions.moveToElement(likeFirstPost).click().build().perform();
        WebElement reblogFirstPost= ((ChromeDriver) driver).findElementByXPath("//a[@title='Реблог']");
        actions.moveToElement(reblogFirstPost).click().build().perform();
        WebElement congirmReblog = driver.findElement(By.cssSelector(".button-area.create_post_button"));
        customWait();
        try {
            congirmReblog.click(); // click confirm reblog
        }
        catch(ElementClickInterceptedException exp) {
            System.out.println("Exception proceded #6 " + exp);
            customWait();
            actions.moveToElement(congirmReblog).click().build().perform();
        }
    }
    void sendNewMessage() {
        WebElement msgbtn = driver.findElement(By.id("messaging_button"));
        msgbtn.click();
        WebElement msgmenu = driver.findElement(By.cssSelector(".compose-toggle.compose-start"));
        msgmenu.click();
        WebElement receiver = driver.findElement(By.className("input"));
        receiver.sendKeys("tank0412");
        customWait();
        receiver.sendKeys(Keys.ENTER);
        customWait();
        WebElement msgText = driver.findElement(By.className("text-input"));
        msgText.sendKeys("Hello");
        WebElement sendBtn= ((ChromeDriver) driver).findElementByXPath("//button[@type='submit']");
        sendBtn.click();

        customWait();
        WebElement gifBtn= ((ChromeDriver) driver).findElementByCssSelector(".conversation-compose-plugin.conversation-compose-plugin--gif-search");
        gifBtn.click();
        WebElement gifTextBody = driver.findElement(By.cssSelector(".editor.editor-plaintext"));
        gifTextBody.sendKeys("gaming");
        customWait();
        WebElement gif = driver.findElement(By.cssSelector(".gif-search-result.gif-search-result--selectable")); //гифка
        gif.click();
        customWait();
        sendBtn.click();

        customWait();
        WebElement imageBtn= ((ChromeDriver) driver).findElementByXPath("//input[@name='messaging-photo']");
        imageBtn.sendKeys(imageUrl);
        customWait();
        sendBtn.click();
        customWait();

        WebElement stickersBtn = ((ChromeDriver) driver).findElementByCssSelector(".conversation-compose-plugin.conversation-compose-plugin--stickers");
        stickersBtn.click();
        WebElement sticker = driver.findElement(By.cssSelector(".messaging-stickers-result"));
        sticker.click();
        customWait();
        sendBtn.click();

    }
}
