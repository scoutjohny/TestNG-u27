import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class ElementiICekanja {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver138.exe");
            driver = new ChromeDriver();

        driver.manage().window().maximize();
        //Implicitno čekanje - dinamički čeka da se svaki element učita određeni broj sekundi
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://demoqa.com/select-menu");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(enabled = true)
    public void demoQaSelectAndWaitTest() throws InterruptedException {
        //Grubo čekanje - zamrzava izvršavanje programa na određeni broj sekundi
        //Thread.sleep(5000);
        //Eksplicitno čekanje - dinamički čeka da se određeni element učita određeni broj sekundi
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("oldSelectMenu")));

        //SELECT - Padajući meni
        Select select = new Select(driver.findElement(By.cssSelector("#oldSelectMenu")));
        select.selectByVisibleText("White");

        //Radio dugmići - mogu da se obeleže ali ne i da se odobeleže (odčekiraju)
        driver.get("https://demoqa.com/radio-button");
        if(!driver.findElement(By.cssSelector("#yesRadio")).isSelected()){
            driver.findElement(By.cssSelector("[for='yesRadio']")).click();
        }

        //Checkbox - Suštinski isti element kao i radio button ali može da se odobeleži (odčekira)
        driver.get("https://demoqa.com/checkbox");
        if(!driver.findElement(By.cssSelector(".rct-checkbox")).isSelected()){
            driver.findElement(By.cssSelector(".rct-checkbox")).click();
        }
    }
}
