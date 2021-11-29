package wallethub.FBloginassignment;

import org.openqa.selenium.WebDriver;

public class FbLoginTest {

	public static void main(String[] args) {
		BaseClass driver = new BaseClass();
		String userName = ReadPropertyFile.getInstance().getProperty("userName");
		String password = ReadPropertyFile.getInstance().getProperty("password");
		WebDriver driver1 = driver.init();
		FbLogin lgn = new FbLogin(driver1, ReadPropertyFile.getInstance().getProperty("Fblogin"));
		lgn.login(userName, password);
		FbPost post = new FbPost(driver1);
		post.postMessage(ReadPropertyFile.getInstance().getProperty("PostMessage"));

	}

}
