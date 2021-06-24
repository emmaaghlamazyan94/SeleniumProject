import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pagesPastebin.HomePage;

public class BaseTestPastebin {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
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