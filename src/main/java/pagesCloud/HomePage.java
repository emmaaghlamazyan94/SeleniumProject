package pagesCloud;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "q")
    private WebElement search;

    @FindBy(xpath = "//div[@class='gsc-thumbnail-inside']//a[@class='gs-title']//b[text()='Google Cloud Platform Pricing Calculator']")
    private WebElement searchedLink;

    @FindBy(className = "devsite-top-logo-row-wrapper-wrapper")
    private WebElement header;

    @FindBy(xpath = "//div[@class='gs-title']//a[@target='_self']")
    private List<WebElement> links;

    public void searchCalculator() {
        search.sendKeys("Google Cloud Platform Pricing Calculator" + Keys.ENTER);
        waitLinksAppeared();
        searchedLink.click();
    }

    public void waitPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(header));
    }

    public void waitLinksAppeared() {
        wait.until(ExpectedConditions.visibilityOfAllElements(links));
    }
}