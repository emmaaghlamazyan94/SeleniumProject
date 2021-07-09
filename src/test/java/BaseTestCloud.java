import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pagesCloud.HomePage;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTestCloud {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.1.100:4444/wd/hub"), new ChromeOptions());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
        driver.get("https://cloud.google.com/");
        HomePage homePage = new HomePage(driver);
        homePage.waitPageLoad();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}