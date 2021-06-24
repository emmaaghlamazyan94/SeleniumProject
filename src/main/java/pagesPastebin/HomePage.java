package pagesPastebin;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@class='select2-results']//li")
    private List<WebElement> pasteExpiration;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement dropBoxPasteExpiration;

    @FindBy(id = "postform-text")
    private WebElement newPaste;

    @FindBy(id = "postform-name")
    private WebElement pasteName;

    @FindBy(id = "select2-postform-format-container")
    private WebElement dropBoxSyntax;

    @FindBy(xpath = "//ul[@class='select2-results__options select2-results__options--nested']//li")
    private List<WebElement> syntaxHighlighting;

    @FindBy(className = "post-form__left")
    private WebElement optionalPasteSettings;

    public void createNewPaste(String newPasteText, String pasteExpirationOption, String title) {
        newPaste.sendKeys(newPasteText);
        dropBoxPasteExpiration.click();
        for (WebElement option : pasteExpiration) {
            if (option.getText().equals(pasteExpirationOption)) {
                option.click();
                break;
            }
        }
        pasteName.sendKeys(title);
    }

    public void chooseSyntaxHighlightingOption(String syntaxHighlightingOption) {
        js.executeScript("window.scrollBy(0,-500)");
        dropBoxSyntax.click();
        for (WebElement option : syntaxHighlighting) {
            if (option.getText().equals(syntaxHighlightingOption)) {
                option.click();
                break;
            }
        }
    }

    public void waitPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(newPaste));
        wait.until(ExpectedConditions.visibilityOf(optionalPasteSettings));
    }
}