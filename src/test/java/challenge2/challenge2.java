package challenge2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class challenge2 {
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
    public void copartSearchForExoticsHasPorsche() throws Exception{
        driver.get("https://www.copart.com");
        driver.findElement(By.id("input-search")).sendKeys("exotics" + Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span[data-uname='lotsearchLotmake']")));
        Assert.assertTrue(driver.findElements(By.cssSelector("span[data-uname='lotsearchLotmake']"))
                .stream().anyMatch(e -> e.getText().equals("PORSCHE")));
    }

}
