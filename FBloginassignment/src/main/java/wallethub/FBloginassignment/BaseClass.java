package wallethub.FBloginassignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import java.util.concurrent.TimeUnit;

public class BaseClass {

	private WebDriver driver;

	private String browser = ReadPropertyFile.getInstance().getProperty("browser");

	public enum Browser {
		chrome, firefox
	}

	public WebDriver init() {
		 //System.out.println(Browser.chrome.name());
		try {
			if (Browser.chrome.name().equalsIgnoreCase(browser)) {
				driver = chromeDriver();
			} else if (Browser.firefox.name().equalsIgnoreCase(browser)) {
				driver = fireFoxDriver();
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return driver;
		} catch (Exception e) {
			System.out.println("Please use either chrome or FireFox driver for testing");
			System.out.println(e);
			throw new WebDriverException();
		}

	}

	public WebDriver chromeDriver() {

		System.setProperty("webdriver.chrome.driver", ReadPropertyFile.getInstance().getProperty("chromeDriver"));
		System.setProperty("webdriver.chrome.logfile", "loggerLocation");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito", "--window-size=1920,1080", "--disable-popup-blocking", "--start-maximized",
				"--no-sandbox");
		options.setAcceptInsecureCerts(true);
		return new ChromeDriver(options);
	}

	private WebDriver fireFoxDriver() {
		System.setProperty("webdriver.gecko.driver", "webDriver");
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "loggerLocation");
		FirefoxBinary binary = new FirefoxBinary();
		FirefoxOptions firefox = new FirefoxOptions();
		FirefoxProfile profile = new FirefoxProfile();
		firefox.setBinary(binary).setProfile(profile);
		return new FirefoxDriver(firefox);
	}
}
