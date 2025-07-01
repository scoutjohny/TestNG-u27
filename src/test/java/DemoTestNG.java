import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DemoTestNG {
    WebDriver driver;
    SoftAssert softAssert;

    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser){
        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver138.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver036.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        //Implicitno čekanje - dinamički čeka da se svaki element učita određeni broj sekundi
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://www.saucedemo.com/");
        softAssert = new SoftAssert();

//        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver138.exe");
        //Ako hoćemo da nam se brauzer upali ali u pozadini, dodajemo sledeći kod:
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
//        driver = new ChromeDriver(chromeOptions);
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://www.saucedemo.com/");
//        softAssert = new SoftAssert();
//        System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver036.exe");
//        driver = new FirefoxDriver();
//        driver.manage().window().maximize();
    }

    @AfterMethod
    @Parameters({"quit"})
    public void tearDown(String quit){
        if(quit.equalsIgnoreCase("yes")){
            driver.quit();
        }
    }

    @Test
    @Parameters({"username","password","url","errorMessage","testType"})
    public void loginToSauceDemo(String username, String password, String url, @Optional String errorMessage, String tetsType) throws InterruptedException {
        if(!username.equals("empty")){
            driver.findElement(By.cssSelector("#user-name")).sendKeys(username);
        }
        if(!password.equals("empty")){
            driver.findElement(By.id("password")).sendKeys(password);
        }
        driver.findElement(By.className("btn_action")).click();

        //Grubo čekanje - zamrzava izvršavanje programa na određeni broj sekundi
        //Thread.sleep(5000);

        Assert.assertEquals(driver.getCurrentUrl(),url);
        if(tetsType.equals("negative")){
            //Eksplicitno čekanje - dinamički čeka da se određeni element učita određeni broj sekundi
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3")));

            Assert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),errorMessage);
        }
    }

//    @Test
//    public void validLoginToSauceDemo(){
//        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
//        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        driver.findElement(By.className("btn_action")).click();
////        driver.findElement(By.cssSelector(".btn_action")).click();
//
////        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html","Wrong page loaded!");
////        Assert.assertEquals(driver.findElement(By.cssSelector(".title")).getText(),"Products");
//        softAssert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html","Wrong page loaded!");
//        softAssert.assertEquals(driver.findElement(By.cssSelector(".title")).getText(),"Products");
//        softAssert.assertAll();
//    }
//
//    @Test
//    public void loginNoUsername(){
//
//        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        driver.findElement(By.className("btn_action")).click();
//
//        softAssert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Username is required");
//        softAssert.assertAll();
//    }
//
//    @Test
//    public void loginNoPassword(){
//        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
//        driver.findElement(By.className("btn_action")).click();
//
//        softAssert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Password is required");
//        softAssert.assertAll();
//    }
//
//    @Test
//    public void loginNoUsernameNorPassword(){
//
//        driver.findElement(By.className("btn_action")).click();
//
//        softAssert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Username is required");
//        softAssert.assertAll();
//    }
//
//    @Test
//    public void loginWrongUsername(){
//        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user1");
//        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        driver.findElement(By.className("btn_action")).click();
//
//        softAssert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Username and password do not match any user in this service");
//        softAssert.assertAll();
//    }
//
//    @Test
//    public void loginWrongPassword(){
//        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
//        driver.findElement(By.id("password")).sendKeys("secret_sauce1");
//        driver.findElement(By.className("btn_action")).click();
//
//        softAssert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Username and password do not match any user in this service");
//        softAssert.assertAll();
//    }
//
//    @Test
//    public void loginWrongUsernameAndPassword(){
//        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user1");
//        driver.findElement(By.id("password")).sendKeys("secret_sauce1");
//        driver.findElement(By.className("btn_action")).click();
//
//        softAssert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),"Epic sadface: Username and password do not match any user in this service");
//        softAssert.assertAll();
//    }
}
