import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pagesPastebin.HomePage;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTestPastebin {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.1.100:4444/wd/hub"), chromeOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
        driver.get("https://pastebin.com/");
        HomePage homePage = new HomePage(driver);
        homePage.waitPageLoad();
    }

    @AfterMethod()
    public void tearDown() {
        driver.quit();
    }
}