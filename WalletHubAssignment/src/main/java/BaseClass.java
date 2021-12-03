import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.FluentWait;
import util.ReadPropertyFile;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    private WebDriver driver;
    private FluentWait<WebDriver> wait;
    private String currentDirectory = System.getProperty("user.dir");

    private String browser = ReadPropertyFile.getInstance().getProperty("browser");

    public enum Browser {
        chrome,firefox
    }

    /**
     *
     * @return WebDriver instance
     */
    public WebDriver init() {
        System.out.println(currentDirectory);
        if(Browser.chrome.name().equalsIgnoreCase(browser)) driver= chromeDriver();
        else if(Browser.firefox.name().equalsIgnoreCase(browser)) driver = fireFoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    /**
     *
     * @return Chrome driver instance
     */
    private WebDriver chromeDriver() {
        System.setProperty("webdriver.chrome.driver", currentDirectory + ReadPropertyFile.getInstance().getProperty("chromeDriver"));
        System.setProperty("webdriver.chrome.logfile", "chromedriver.log");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito", "--window-size=1920,1080", "--disable-popup-blocking", "--start-maximized","--no-sandbox");
        options.setAcceptInsecureCerts(true);
        return new ChromeDriver(options);
    }

    /**
     *
     * @return Firefox driver instance
     */
    private WebDriver fireFoxDriver() {
        System.setProperty("webdriver.gecko.driver", currentDirectory + ReadPropertyFile.getInstance().getProperty("geckodriver"));
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"firefoxdriver.log");
        FirefoxBinary binary = new FirefoxBinary();
        FirefoxOptions firefox = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        firefox.setBinary(binary).setProfile(profile);
        return new FirefoxDriver(firefox);
    }

    /**
     *
     * @param driver - Web drver instance
     */
    public void close(WebDriver driver) {
       if(driver!=null) driver.close();
    }
}
