package Assignment1.facebook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Utiliity;

import java.time.Duration;

public class FbPost {

    Logger logger =  LoggerFactory.getLogger(FbPost.class);
    private WebDriver driver;
    private WebDriverWait uiWait;
    private Utiliity util = new Utiliity();

    @FindBy(xpath="//div[@role=\"button\"]/div/span[contains(text(),\"What's on your mind\")]")
    private WebElement statusName;

    @FindBy(xpath="//div[contains(@aria-label,\"What's on your mind\") and @role=\"textbox\"]")
    private WebElement write;

    @FindBy(xpath="//span[text()=\"Post\"]/../../span[@dir=\"auto\"]")
    private WebElement post;

    /**
     *
     * @param driver - Web driver instance
     */
    public FbPost(WebDriver driver) {
        this.driver = driver;
        this.uiWait = new WebDriverWait(this.driver,5);
        PageFactory.initElements(this.driver, this);
    }

    /**
     *
     * @param message - Message to be posted in Fb status page
     * @throws Exception
     */
    public void postMessage(String message) throws Exception {
        try {
            logger.info("Posting " +  message + " in FB account");
            this.statusName.click();
            this.write.sendKeys(message);
            this.uiWait.until(ExpectedConditions.elementToBeClickable(this.post));
            util.retry(this.post,this.uiWait);
            logger.info(message + " is successfully posted");
        }
        catch(Exception ex) {
            logger.error("unable to post Message in FB");
            throw new Exception(ex.getLocalizedMessage());
        }
    }


}
