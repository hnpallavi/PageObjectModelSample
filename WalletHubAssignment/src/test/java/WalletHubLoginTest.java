import Assignment1.facebook.FbLogin;
import Assignment1.facebook.FbPost;
import Assignment2.wallethub.WalletHubLogin;
import Assignment2.wallethub.WalletHubProfile;
import Assignment2.wallethub.WalletHubReview;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.ReadPropertyFile;
import util.Utiliity;

import static org.testng.Assert.assertTrue;

public class WalletHubLoginTest {
   private WalletHubLogin walletLogin;
   private WalletHubReview review;
   private WalletHubProfile profile;
   private BaseClass driver = null;
   private WebDriver webdriver = null;

   @BeforeTest
   public void initDriver() {
      driver = new BaseClass();
      webdriver = driver.init();
   }

   @AfterTest
   public void closeDriver() {
         driver.close(webdriver);
   }

   @Test(priority = 0)
   public void walletReviewTest() throws Exception {
      try {
         String reviewMsg = "Hello Greetings to Everyone check This is for testing the review section This is for testing the review section This is for testing the review section This is for testing the review section This is for testing the review section review section";
         int star = 4;
         String email = ReadPropertyFile.getInstance().getProperty("walletHubemail");
         String pwd = ReadPropertyFile.getInstance().getProperty("walletHubPassword");
         String reviewURL = "https://wallethub.com/profile/13732055i";
         String loginUrl = "https://wallethub.com/join/login";
         WalletHubLogin walletLogin = new WalletHubLogin(webdriver, loginUrl);
         walletLogin.login(email, pwd);
         WalletHubReview review = new WalletHubReview(webdriver,reviewURL);
         review.postMessage(reviewMsg, star);
         WalletHubProfile profile = new WalletHubProfile(webdriver);
         String value = profile.getReview(webdriver);
         assertTrue(value.contains("I RECOMMEND"));
      } catch (Exception ex) {
         new Utiliity().captureScreen(webdriver,this.getClass().getCanonicalName(),"Screenshots");
         throw new Exception(ex);
      }
   }

   @Test(priority = 1)
   public void fbStatus() throws Exception {
      try {
         String userName = ReadPropertyFile.getInstance().getProperty("userName");
         String password = ReadPropertyFile.getInstance().getProperty("password");
         FbLogin lgn = new FbLogin(webdriver, ReadPropertyFile.getInstance().getProperty("Fblogin"));
         lgn.login(userName, password);
         FbPost post = new FbPost(webdriver);
         post.postMessage(ReadPropertyFile.getInstance().getProperty("PostMessage"));
      } catch (Exception ex) {
         new Utiliity().captureScreen(webdriver, this.getClass().getCanonicalName(), "Screenshots");
         throw new Exception(ex);
      }
   }
}