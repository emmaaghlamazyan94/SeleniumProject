package cloud;

import driver.DriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pagesCloud.HomePage;
import utils.TestListener;

@Listeners(TestListener.class)
public class BaseTestCloud {
    protected WebDriver driver;
    protected Logger log = LogManager.getRootLogger();

    @BeforeMethod
    public void setUp() {
//        try {
//            driver = new RemoteWebDriver(new URL("http://192.168.1.100:4444/wd/hub"), new ChromeOptions());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        driver = DriverSingleton.getDriver();
        driver.get("https://cloud.google.com/");
        HomePage homePage = new HomePage(driver);
        homePage.waitPageLoad();
    }

    @AfterMethod
    public void tearDown() {
        DriverSingleton.closeDriver();
    }
}