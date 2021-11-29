package wallethub.FBloginassignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FbLogin {

	private WebDriver driver;
	private WebDriverWait uiWait;

	@FindBy(name = "email")
	private WebElement userName;

	@FindBy(name = "pass")
	private WebElement password;

	@FindBy(name = "login")
	private WebElement button;

	FbLogin(WebDriver driver, String url) {
		try {
		this.driver = driver;
		this.driver.navigate().to(url);
		this.driver.manage().window().maximize();
		PageFactory.initElements(this.driver, this);
		}
		catch(Exception e)  {
			System.out.println("WebDriver is not initialised");
			System.out.println(e);
		}
	}

	public void login(String userName, String password) {
		try {
		if(userName!=null && password!=null) {	
		this.uiWait = new WebDriverWait(this.driver, 10);
		this.uiWait.until(ExpectedConditions.titleContains("Facebook"));
		this.userName.sendKeys(userName);
		this.password.sendKeys(password);
		this.button.click();
		System.out.println("Facebook login is successful!");
		}
		}
		catch(Exception e) {
			System.out.println("Login failed : "+ e);
		}
	}

}
