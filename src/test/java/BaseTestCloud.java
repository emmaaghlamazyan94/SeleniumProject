import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pagesCloud.HomePage;

public class BaseTestCloud {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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
