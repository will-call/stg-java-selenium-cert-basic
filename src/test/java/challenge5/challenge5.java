package challenge5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class challenge5  {
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
        driver.findElement(By.id("input-search")).sendKeys("porsche" + Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[name='serverSideDataTable_length']")));
        driver.findElement(By.cssSelector(".top select[name='serverSideDataTable_length'] option[value='100']")).click();

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bottom .dataTables_info"))).getText().contains("Showing 1 to 100"));
        ArrayList<String> models = new ArrayList<>();
        // get a list of all the models
        models.addAll(driver.findElements(By.cssSelector("td [data-uname='lotsearchLotmodel']")).stream().map(WebElement::getText).sorted().collect(Collectors.toList()));
        // create a set of only the distinct model names
        Set<String> modelSet = new HashSet<>(models);
        // use the modelSet to gather the frequency/count of each model against the full list
        modelSet.forEach(m -> System.out.println(m + ": " + Collections.frequency(models, m)));

        ArrayList<String> damages = new ArrayList<>();
        //get a list of all the damages
        damages.addAll(driver.findElements(By.cssSelector("td [data-uname='lotsearchLotdamagedescription']")).stream().map(WebElement::getText).sorted().collect(Collectors.toList()));

        int index = 0;
        int rearEnd = 0, frontEnd = 0, minorDentScratches = 0, undercarriage = 0, misc = 0;
        while (index < damages.size()) {

            switch (damages.get(index)) {
                case "REAR END":
                    rearEnd++;
                    break;
                case "FRONT END":
                    frontEnd++;
                    break;
                case "MINOR DENT/SCRATCHES":
                    minorDentScratches++;
                    break;
                case "UNDERCARRIAGE":
                    undercarriage++;
                    break;
                default:
                    misc++;
            }
            index++;
        }
        System.out.println("\nRear Ends: " + rearEnd);
        System.out.println("Front Ends: " + frontEnd);
        System.out.println("Minor Dent/Scratches: " + minorDentScratches);
        System.out.println("Undercarriage: " + undercarriage);
        System.out.println("Misc: " + misc);

    }
}
