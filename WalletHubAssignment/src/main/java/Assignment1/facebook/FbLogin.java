package Assignment1.facebook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FbLogin {

    Logger logger =  LoggerFactory.getLogger(FbLogin.class);
    private WebDriver  driver;
    private WebDriverWait uiWait;

    @FindBy(name="email")
    private WebElement userName;

    @FindBy(name="pass")
    private WebElement password;

    @FindBy(name="login")
    private WebElement button;

    /**
     *
     * @param driver - Web Driver instance
     * @param url - FB login Url
     * @throws Exception
     */
    public FbLogin(WebDriver driver, String url) throws Exception {
        try {
            this.driver = driver;
            this.driver.navigate().to(url);
            this.driver.manage().window().maximize();
            PageFactory.initElements(this.driver, this);
        }
        catch(Exception e)  {
            logger.error("WebDriver is not initialised");
            throw new Exception(e.getLocalizedMessage());
        }
    }

    /**
     *
     * @param userName - FB login user name
     * @param password - FB login password
     * @throws Exception
     */
    public void login(String userName, String password) throws Exception {
        try {
            if(userName!=null && password!=null) {
                this.uiWait = new WebDriverWait(this.driver, 10);
                this.uiWait.until(ExpectedConditions.titleContains("Facebook"));
                this.userName.sendKeys(userName);
                this.password.sendKeys(password);
                this.button.click();
                logger.info("Facebook login is successful!");
            }
        }
        catch(Exception e) {
            logger.error("Login failed : "+ e);
            throw new Exception(e.getLocalizedMessage());
        }
    }
}
