package pagesCloud;

import model.Calculator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import service.CalculatorCreation;

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

    public CalculatorPage searchCalculator() {
        Calculator calculator = CalculatorCreation.calculatorData();
        Actions actions = new Actions(driver);
        actions.sendKeys(search, calculator.getSearchText() + Keys.ENTER)
                .build()
                .perform();
        waitLinksAppeared();
        searchedLink.click();
        log.info("Link is opened");
        return new CalculatorPage(driver);
    }

    public void waitPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(header));
    }

    private void waitLinksAppeared() {
        wait.until(ExpectedConditions.visibilityOfAllElements(links));
    }
}