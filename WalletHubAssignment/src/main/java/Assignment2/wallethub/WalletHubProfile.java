package Assignment2.wallethub;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WalletHubProfile {

    Logger logger =  LoggerFactory.getLogger(WalletHubProfile.class);

    private WebDriver driver;
    private FluentWait<WebDriver> uiWait;

    @FindBy(xpath = "//div[contains(@class, \"brgm-user\")]/span[@class=\"brgm-list-title\"]")
    private WebElement userHover;

    @FindBy(xpath="//div[contains(@class,\"brgm-user-list\")]/child::a[text()=\"Profile\"]")
    private WebElement profile;

    @FindBy(className="pr-content-left")
    private WebElement recommendationMsg;

    public WalletHubProfile(WebDriver driver) {
        this.driver = driver;
        this.uiWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(80)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
        PageFactory.initElements(this.driver, this);
    }

    public String getReview(WebDriver driver) throws Exception {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("window.scrollTo( document.body.scrollHeight,0)");
            Actions builder = new Actions(driver);
            builder.moveToElement(this.userHover).clickAndHold().build().perform();
            builder.moveToElement(this.profile).pause(2000).build().perform();
            this.profile.click();
            return this.recommendationMsg.getAttribute("outerHTML");
        }
        catch(Exception ex) {
            logger.error("Unable to get review from user profile");
            throw new Exception(ex.getLocalizedMessage());
        }
    }

}
