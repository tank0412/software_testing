import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TumblrPanel implements Constants {
    private WebDriver driver;
    public TumblrPanel(WebDriver testDriver) {
        driver = testDriver;
    }
    void customWait() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        customWait();
        WebElement elTextBodyUrl = driver.findElement(By.cssSelector(".editor.editor-plaintext")); //Ссылка на видео
        elTextBodyUrl.sendKeys(videoUrl);
        elTextBodyUrl.sendKeys(Keys.ENTER);
        customWait();
        WebElement postTextButton = driver.findElement(By.cssSelector(".button-area.create_post_button"));
        actions = new Actions(driver);
        actions.moveToElement(postTextButton).click().build().perform();
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
        postMusic(SONGNAME);
        customWait();
        WebElement postText = ((ChromeDriver) driver).findElementByXPath("//a[@data-post-endpoint='text']");
        postText.click();
        postText(TEXT);
        customWait();
        WebElement postImage = ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_photo']"); //use another way to clck button
        postImage.click();
        postImage(IMAGEURL);
        customWait();
        WebElement postQuote= ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_quote']");
        postQuote.click();
        postQuote(QUOTETEXT, QUOTEAUTHOR);
        customWait();
        WebElement postVideo = ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_video']");
        postVideo.click();
        postVideo(VIDEOURL);
        customWait();
        WebElement postChat = ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_chat']");
        postChat.click();
        postChat(CHAT);
        customWait();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
        WebElement postLink = ((ChromeDriver) driver).findElementByXPath("//a[@id='new_post_label_link']");
        postLink.click();
        postLink(POSTURL);
        customWait();

    }
    void testAnotherPostPanel() {
        composeBtnClck();
        WebElement postText = ((ChromeDriver) driver).findElementByXPath("//div[@data-post-type='text']");
        postText.click();
        postText(TEXT);
        customWait();
        composeBtnClck();
        WebElement postPhoto = ((ChromeDriver) driver).findElementByXPath("//div[@data-post-type='photo']");
        postPhoto.click();
        postImage(IMAGEURL);
        customWait();
        composeBtnClck();
        WebElement postQuote = ((ChromeDriver) driver).findElementByXPath("//div[@data-post-type='quote']");
        postQuote.click();
        postQuote(QUOTETEXT, QUOTEAUTHOR);
        customWait();
        composeBtnClck();
        WebElement postVideo = ((ChromeDriver) driver).findElementByXPath("//div[@data-post-type='video']");
        postVideo.click();
        postVideo(VIDEOURL);
        customWait();
        deleteFirstPost();
        customWait();
        driver.get(TUMBLRDASHBOARDURL);
        customWait();
        composeBtnClck();
        WebElement postChat = ((ChromeDriver) driver).findElementByXPath("//div[@data-post-type='chat']");
        postChat.click();
        postChat(CHAT);
        customWait();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
        composeBtnClck();
        WebElement postAudio = ((ChromeDriver) driver).findElementByXPath("//div[@data-post-type='audio']");
        postAudio.click();
        postMusic(SONGNAME);
        customWait();
        deleteFirstPost();
        customWait();
        driver.get(TUMBLRDASHBOARDURL);
        customWait();
        composeBtnClck();
        WebElement postLink = ((ChromeDriver) driver).findElementByXPath("//div[@data-post-type='link']");
        postLink.click();
        postLink(POSTURL);
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
}
