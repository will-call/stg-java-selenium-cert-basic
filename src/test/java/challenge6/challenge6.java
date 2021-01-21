package challenge6;

import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.CaptureScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class challenge6 {
    public WebDriver driver;

    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("All done!!!");
    }

    @BeforeClass
    public void startClass() throws Exception{
        System.setProperty("webdriver.chrome.driver", "./bin/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void stopClass(){
        driver.quit();
    }

    @Test()
    public void copartSearchForExoticsHasPorsche() throws Exception {
        driver.get("https://www.copart.com");
        driver.findElement(By.id("input-search")).sendKeys("nissan" + Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-uname='ModelFilter']")));
        driver.findElement(By.cssSelector("[data-uname='ModelFilter']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li//*[text()='Model']//following::input[1]")));
        driver.findElement(By.xpath("//li//*[text()='Model']//following::input[1]")).sendKeys("skyline" + Keys.ENTER);
        try{
            //skylin to show picture is taken
            driver.findElement(By.cssSelector("input[value='Skylin']")).click();
        } catch (Exception e){
            System.out.println("Nissan Skyline is unavailable");
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.getFileUtils().copyFile(screenshot, new File("./skyline.png"));
        }


    }
}
