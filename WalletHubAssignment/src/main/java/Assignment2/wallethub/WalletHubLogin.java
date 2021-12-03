package Assignment2.wallethub;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.Utiliity;

import java.time.Duration;

public class WalletHubLogin {

    Logger logger =  LoggerFactory.getLogger(WalletHubLogin.class);
    private Utiliity util = new Utiliity();

    private WebDriver driver;
    private FluentWait<WebDriver> uiWait;

    @FindBy(id="email")
    private WebElement userName;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(xpath = "//span[text() = \"Login\"]")
    private WebElement loginButton;

    /**
     *
     * @param driver - WebDriver Instance
     * @param url - Wallethub login url
     */
    public WalletHubLogin(WebDriver driver, String url) {
        this.driver = driver;
        this.uiWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(80)).pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
        this.driver.get(url);
        this.driver.manage().window().maximize();
        PageFactory.initElements(this.driver, this);
    }

    /**
     *
     * @param userName - WalletHub userName
     * @param password - WalletHub Password
     */
    public void login(String userName, String password) {
        logger.info("Logging to WalletHub Login portal");
        WebDriverWait wait = new WebDriverWait(this.driver, 5);
        this.userName.sendKeys(userName);
        this.password.sendKeys(password);
        this.loginButton.click();
        util.waitInvisibilityOf(wait,this.loginButton);
    }
}
