import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DemoTestNG {
    WebDriver driver;
    SoftAssert softAssert;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver138.exe");
        //Ako hoćemo da nam se brauzer upali ali u pozadini, dodajemo sledeći kod:
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
//        driver = new ChromeDriver(chromeOptions);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        softAssert = new SoftAssert();
//        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver036.exe");
//        driver = new FirefoxDriver();
//        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void validLoginToSauceDemo(){
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("btn_action")).click();
//        driver.findElement(By.cssSelector(".btn_action")).click();

//        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html","Wrong page loaded!");
//        Assert.assertEquals(driver.findElement(By.cssSelector(".title")).getText(),"Products");
        softAssert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html","Wrong page loaded!");
        softAssert.assertEquals(driver.findElement(By.cssSelector(".title")).getText(),"Products");
        softAssert.assertAll();
    }

    @Test
    public void loginNoUsername(){

        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("btn_action")).click();

        softAssert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Username is required");
        softAssert.assertAll();
    }

    @Test
    public void loginNoPassword(){
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.className("btn_action")).click();

        softAssert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Password is required");
        softAssert.assertAll();
    }

    @Test
    public void loginNoUsernameNorPassword(){

        driver.findElement(By.className("btn_action")).click();

        softAssert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Username is required");
        softAssert.assertAll();
    }

    @Test
    public void loginWrongUsername(){
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user1");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("btn_action")).click();

        softAssert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Username and password do not match any user in this service");
        softAssert.assertAll();
    }

    @Test
    public void loginWrongPassword(){
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce1");
        driver.findElement(By.className("btn_action")).click();

        softAssert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Username and password do not match any user in this service");
        softAssert.assertAll();
    }

    @Test
    public void loginWrongUsernameAndPassword(){
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user1");
        driver.findElement(By.id("password")).sendKeys("secret_sauce1");
        driver.findElement(By.className("btn_action")).click();

        softAssert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Username and password do not match any user in this service");
        softAssert.assertAll();
    }
}
