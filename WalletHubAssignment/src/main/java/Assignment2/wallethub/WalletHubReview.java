package Assignment2.wallethub;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Utiliity;
import java.time.Duration;
import java.util.List;

public class WalletHubReview {

    Logger logger =  LoggerFactory.getLogger(WalletHubReview.class);

    private WebDriver driver;
    private FluentWait<WebDriver> uiWait;
    private Utiliity util = new Utiliity();

    @FindBy(xpath = "//span[@class=\"nav-txt\" and text() = \"Reviews\"]")
    @CacheLookup
    private WebElement clickReview;


    @FindAll(@FindBy(xpath = "//review-star[@class=\"rvs-svg\"]/div//*[name()='path']"))
    private List<WebElement> starHover;

    @FindBy(xpath = "//span[text() = \"Login\"]")
    private WebElement loginButton;

    @FindBy(xpath = "//span[@class=\"dropdown-placeholder\" and contains(text() , \"Select\")]")
    @CacheLookup
    private WebElement selectDropDown;

    @FindBy(xpath = "//div[@class=\"dropdown second opened\"]//*/li[text()=\"Health Insurance\"]")
    private WebElement healthInsurance;

    @FindBy(xpath = "//div[@class=\"android\"]/textarea")
    private WebElement writeReview;

    @FindBy(xpath = "//div[text() = \"Submit\"]")
    private WebElement clickSubmitButton;

    @FindBy(xpath = "//div[text() = \"Continue\"]")
    private WebElement clickContinue;

    public WalletHubReview(WebDriver driver,String url) {
        this.driver = driver;
        this.uiWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(80)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
        this.driver.navigate().to(url);
        PageFactory.initElements(this.driver, this);
    }

    public void postMessage(String message,int reviewStar) throws Exception {
        logger.info("posting review message in wallethub profile in home page");
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, 10);
            util.retry(this.clickReview,wait);
            int revStar = util.getStar(reviewStar);
            new Actions(this.driver).pause(1000).moveToElement(this.starHover.get(revStar-1)).click().build().perform();
            this.selectDropDown.click();
            logger.info("Review star has been given successfully");
            logger.info("Selecting Health insurance criteria");
            this.healthInsurance.click();
            this.writeReview.sendKeys(message);
            logger.info("Message Written Successfully");
            this.clickSubmitButton.click();
            util.retry(this.clickContinue,wait);
            logger.info("Moving back to home screen");
        }
        catch(Exception ex) {
            logger.error("Unable to enter the Review");
            throw new Exception(ex);
        }
    }
}
