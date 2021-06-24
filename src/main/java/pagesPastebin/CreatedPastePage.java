package pagesPastebin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreatedPastePage extends BasePage {
    public CreatedPastePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='info-top']/h1")
    private WebElement createdPasteTitle;

    @FindBy(xpath = "//div[@class ='source']")
    private WebElement createdPasteText;

    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement submit;

    @FindBy(className = "highlighted-code")
    private WebElement pasteTextBar;

    @FindBy(xpath = "//div[@class='left']//a[@class='btn -small h_800']")
    private WebElement createdSyntax;

    public String getActualTitle() {
        return createdPasteTitle.getText();
    }

    public String getActualPasteText() {
        return createdPasteText.getText();
    }

    public String getActualSyntax() {
        return createdSyntax.getText();
    }

    public void submit() {
        submit.click();
        waitPageLoad();
    }

    public void waitPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(pasteTextBar));
    }
}