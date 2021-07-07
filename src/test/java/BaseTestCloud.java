import Nodes.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pagesCloud.HomePage;

public class BaseTestCloud {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browserName = System.getProperty("browser");
        driver = BrowserFactory.getBrowser("chrome");
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