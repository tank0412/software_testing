import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

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
        catch (NoSuchElementException exp) {
            System.out.println("Exception proceded" + exp);
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
        imageBtn.sendKeys(IMAGEURL);
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
