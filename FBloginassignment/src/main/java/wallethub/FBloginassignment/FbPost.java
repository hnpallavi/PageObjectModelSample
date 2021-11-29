package wallethub.FBloginassignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FbPost {

    private WebDriver driver;
    private WebDriverWait uiWait;

    @FindBy(xpath="//div[@role=\"button\"]/div/span[contains(text(),\"What's on your mind\")]")
    private WebElement statusName;

    @FindBy(xpath="//div[contains(@aria-label,\"What's on your mind\") and @role=\"textbox\"]")
    private WebElement write;

    @FindBy(xpath="//span[text()=\"Post\"]/../../span[@dir=\"auto\"]")
    private WebElement post;
    
    /*@FindBy(xpath="//div[@aria-label=\"Account\"]//div")
    private WebElement Account;
    
    @FindBy(xpath="//span[@dir=\"auto\" and text()=\"Log Out\"]")
    private WebElement logout;*/

    FbPost(WebDriver driver) {
        this.driver = driver;
        this.uiWait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(this.driver, this);

    }

    public void postMessage(String message) {
        this.statusName.click();
        this.write.sendKeys(message);
        this.uiWait.until(ExpectedConditions.elementToBeClickable(post));
        this.post.click();
        System.out.println("Message posted succesfully!");
//        this.uiWait.until(ExpectedConditions.elementToBeClickable(Account));
//        this.Account.click();
//        this.uiWait.until(ExpectedConditions.elementToBeClickable(logout));
//        this.logout.click();
        this.driver.quit();
    }


}
