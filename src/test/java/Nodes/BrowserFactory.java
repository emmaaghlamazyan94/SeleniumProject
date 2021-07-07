package Nodes;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {

    public static WebDriver getBrowser(String browserName) {
        WebDriver driver;
        DesiredCapabilities desiredCapabilities;
        switch (browserName) {
            case "firefox":
                desiredCapabilities = DesiredCapabilities.firefox();
                break;
            default:
//                WebDriverManager.chromedriver().setup();
//                GridLauncherV3.main(new String[] { "-role", "node", "-hub",
//                        "http://localhost:4444/grid/register", "-browser",
//                        "browserName=chrome", "-port", "5555" });


//                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//                WebDriverManager.chromedriver().setup();
                desiredCapabilities = DesiredCapabilities.chrome();
                break;
        }
        desiredCapabilities.setPlatform(Platform.WINDOWS);
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.1.101:4444/wd/hub"), desiredCapabilities);
            return driver;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}