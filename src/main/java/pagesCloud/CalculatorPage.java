package pagesCloud;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CalculatorPage extends BasePage {
    public CalculatorPage(WebDriver driver) {
        super(driver);
    }

    JavascriptExecutor js = (JavascriptExecutor) driver;

    @FindBy(xpath = "(//input[@aria-label='quantity'])[1]")
    private WebElement numberOfInstances;

    @FindBy(name = "label")
    private WebElement whatAreFor;

    @FindBy(id = "select_value_label_58")
    private WebElement OS;

    @FindBy(xpath = "//md-option[@class='md-ink-ripple']")
    private List<WebElement> OSoptions;

    @FindBy(xpath = "//md-select[@placeholder='VM Class']//md-select-value")
    private WebElement machineClass;

    @FindBy(xpath = "//div[@id='select_container_82']//md-option//div")
    private List<WebElement> machineClassOption;

    @FindBy(xpath = "//label[text()='Machine type']/parent::md-input-container")
    private WebElement machineType;

    @FindBy(xpath = "//md-optgroup[@label='standard']//md-option")
    private List<WebElement> machineTypeOption;

    @FindBy(id = "myFrame")
    private WebElement secondFrame;

    @FindBy(css = "iframe[name*=goog_]")
    private WebElement firstFrame;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
    private WebElement addGPUs;

    @FindBy(xpath =
            "//md-input-container[@class='md-input-has-placeholder md-input-has-value flex']//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGPUs;

    @FindBy(xpath = "//md-option[@ng-disabled='item.value != 0 && item.value < listingCtrl.minGpuTenant']")
    private List<WebElement> numberOfGPUsDropbox;

    @FindBy(xpath = "//input[@name='nodesCount']")
    private WebElement numberOfNodes;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']//md-select-value")
    private WebElement GPUType;

    @FindBy(css = "md-option[value*='NVIDIA_TESLA']")
    private List<WebElement> GPUTypeOption;

    @FindBy(xpath = "//md-select[@placeholder='Local SSD']//md-select-value")
    private WebElement localSSD;

    @FindBy(xpath = "//div[@class='md-text' and text()='24x375 GB']")
    private WebElement localSSDType;

    @FindBy(xpath = "//form[@name='SoleTenantForm']//md-select[@placeholder='Datacenter location']//div[@class='md-text ng-binding']")
    private WebElement datacenterLocation;

    @FindBy(xpath = "//md-select-menu[@class='md-overflow']//md-option[@value='europe-west3']//div[contains(text(),'Frankfurt')]")
    private WebElement datacenterLocationOption;

    @FindBy(xpath = "(//md-select[@placeholder='Committed usage'])[2]")
    private WebElement commitedUsage;

    @FindBy(xpath = "//div[@id='select_container_127']//md-option")
    private List<WebElement> commitedUsageOption;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[@aria-label='Add to Estimate']")
    private WebElement addToEstimate1;

    @FindBy(xpath = "//div[@ng-if='listingCtrl.showSoleTenant']//button[@aria-label='Add to Estimate']")
    private WebElement addToEstimate2;

    @FindBy(xpath =
            "//md-content[@id='compute']//md-list-item[@class='md-1-line md-no-proxy ng-scope']//div[@class='md-list-item-text ng-binding']")
    private WebElement actualMachineClassOption;

    @FindBy(xpath =
            "//md-content[@id='compute']//md-list-item[@class='md-1-line md-no-proxy']//div[contains(text(),'Instance type')]")
    private WebElement actualInstanceType;

    @FindBy(xpath = "//md-list-item[@class='md-1-line md-no-proxy']//div[contains(text(),'Frankfurt')]")
    private WebElement actualRegion;

    @FindBy(xpath = "//md-list-item[@class='md-1-line md-no-proxy ng-scope']//div[contains(text(),'Commitment term')]")
    private WebElement actualCommitmentTerm;

    @FindBy(xpath = "//button[@aria-label='Email Estimate']")
    private WebElement sendEmail;

    @FindBy(xpath = "//div[@class='md-toolbar-tools cpc-estimate-header']")
    private WebElement addToEstimateBar;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmailToGeneratedMail;

    public String getActualMachineClassOption() {
        return actualMachineClassOption.getText().toLowerCase();
    }

    public String getActualCommitmentTerm() {
        return actualCommitmentTerm.getText();
    }

    public String getActualInstanceType() {
        return actualInstanceType.getText();
    }

    public String getActualRegion() {
        return actualRegion.getText();
    }

    public void switchToFrame() {
        driver.switchTo().frame(firstFrame).switchTo().frame(secondFrame);
    }

    public void createCalculator(String OSoption, String machineClassOptionText, String machineTypeOptionText) {
        instances(OSoption, machineClassOptionText, machineTypeOptionText);
        soleTenantNodes();
    }

    public void instances(String OSoption, String machineClassOptionText, String machineTypeOptionText) {
        numberOfInstances.sendKeys("4");
        whatAreFor.sendKeys("");
        OS.click();
        for (WebElement option : OSoptions) {
            if (option.getText().equals(OSoption)) {
                option.click();
                break;
            }
        }
        choose(machineClass, machineClassOption, machineClassOptionText);
        choose(machineType, machineTypeOption, machineTypeOptionText);
        js.executeScript("window.scrollBy(0,3000)");
    }

    public void soleTenantNodes() {
        numberOfNodes.sendKeys("1");
        wait.until(ExpectedConditions.visibilityOf(addGPUs));
        addGPUs.click();
        choose(numberOfGPUs, numberOfGPUsDropbox, "4");
        choose(GPUType, GPUTypeOption, "NVIDIA Tesla V100");
        wait.until(ExpectedConditions.elementToBeClickable(localSSD));
        localSSD.click();
        wait.until(ExpectedConditions.elementToBeClickable(localSSDType));
        localSSDType.click();
        datacenterLocation.click();
        wait.until(ExpectedConditions.elementToBeClickable(datacenterLocationOption));
        datacenterLocationOption.click();
        choose(commitedUsage, commitedUsageOption, "1 Year");
    }

    public void setAddToEstimate() {
        addToEstimate2.click();
        addToEstimate1.click();
        wait.until(ExpectedConditions.visibilityOf(addToEstimateBar));
    }

    public String getActualTotalCost() {
        return driver.findElement(By.xpath("//h2[@class='md-title']//b")).getText();
    }

    public void email() {
        sendEmail.click();
    }

    public void sendEmail() {
        driver.switchTo().frame(firstFrame).switchTo().frame(secondFrame);
        js.executeScript("window.scrollBy(0,1200)");
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(Keys.chord(Keys.CONTROL, "v"));
        wait.until(ExpectedConditions.elementToBeClickable(sendEmailToGeneratedMail));
        sendEmailToGeneratedMail.click();
    }

    public void choose(WebElement element, List<WebElement> elementList, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        for (WebElement option : elementList) {
            wait.until(ExpectedConditions.visibilityOf(option));
            if (option.getText().replaceAll("\\s+", "").
                    contains(text.replaceAll("\\s+", ""))) {
                option.click();
                break;
            }
        }
        wait.until(ExpectedConditions.invisibilityOfAllElements(elementList));
    }
}