package pagesCloud;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class TabPage extends BasePage {

    public TabPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "item_to_copy")
    private WebElement copyButton;

    @FindBy(id = "myFrame")
    private WebElement secondFrame;

    @FindBy(css = "iframe[name*=goog_]")
    private WebElement firstFrame;

    @FindBy(id = "mail_list")
    private WebElement mailBox;

    @FindBy(xpath = "//span[@class='view_msg_subject']")
    private WebElement inbox;

    @FindBy(id = "view_body")
    private WebElement frame;

    @FindBy(xpath = "//tr[@id='mobilepadding']//h2")
    private WebElement message;

    @FindBy(id = "ajax")
    private WebElement totalCostMessage;

    public CalculatorPage generateEmail() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get("https://tempmailgen.com/");
        wait.until(ExpectedConditions.elementToBeClickable(copyButton));
        log.debug("Email is generated and copied");
        Actions actions = new Actions(driver);
        actions.click(copyButton).build().perform();
        driver.switchTo().window(tabs.get(0));
        return new CalculatorPage(driver);
    }

    public TabPage getSentEmail() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.visibilityOf(inbox));
        inbox.click();
        log.debug("There is problem with getting sent email");
        return new TabPage(driver);
    }

    public String getMessageTotalCost() {
        wait.until(ExpectedConditions.visibilityOf(totalCostMessage));
        driver.switchTo().frame(frame);
        log.error("Error: message is not received");
        return message.getText();
    }
}