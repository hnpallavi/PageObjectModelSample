package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class Utiliity {

    public void waitTillClickElem(WebDriverWait wait, WebElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
    }

    public void waitUntilElemPresence(WebDriverWait wait, By elem) {
        wait.until(ExpectedConditions.presenceOfElementLocated(elem));
    }

    public void waitInvisibilityOf(WebDriverWait wait, WebElement elem) {
        wait.until(ExpectedConditions.invisibilityOf(elem));
    }

    public void waitVisibilityOf(WebDriverWait wait, WebElement elem) {
        wait.until(ExpectedConditions.visibilityOf(elem));
    }

    public void retry(WebElement elem, WebDriverWait wait) throws InterruptedException {
        Boolean retry = true;
        int tries = 1;
        int maxTries = 5;
        while(retry && maxTries < 5) {
            try {
                elem.click();
                retry = false;
            }
            catch(StaleElementReferenceException ex) {
                System.out.println("[WARN] : Element $locatorName is not clickable yet due to StaleElementReferenceException, retrying $tries");
                Thread.sleep(2000);
                tries += 1;
            }
            catch(Exception ex) {
                tries+=1;
                if(tries > maxTries)
                throw ex;
            }
        }
    }

    public int getStar(int num) {
        if(num>=5) return 5;
        else if(num <= 1) return 1;
        else return num;
    }

    public String captureScreen(WebDriver driver, String folderName, String fileName) {
        int fileCount = 0;
        try {
            File file= new File(String.format("./testReports/screenShots/%s",folderName));
            fileCount = file.listFiles().length;
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(String.format("./test-reports/screenshots/%s/%s.%s.jpg",folderName,fileName,fileCount)));
            return String.format("<a target='_blank' href='screenshots/%s/%s.%s.jpg'><img src='screenshots/%s/%s.%s.jpg' alt='%s/%s.jpg' height='42' width='42'/></a>",
                    folderName,fileName,fileCount,folderName,fileName,fileCount,fileName,fileCount);
        }
        catch(Exception ex) {
            return ex.getMessage();
        }
    }
}
