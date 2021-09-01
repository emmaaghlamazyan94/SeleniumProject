package pagesCloud;

import model.Calculator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import service.CalculatorCreation;

import java.util.List;

public class CalculatorPage extends BasePage {
    public CalculatorPage(WebDriver driver) {
        super(driver);
    }

    private JavascriptExecutor js = (JavascriptExecutor) driver;
    @FindBy(xpath = "(//input[@aria-label='quantity'])[1]")
    private WebElement numberOfInstances;
    @FindBy(xpath = "//md-select[contains(@aria-label,'Operating')]")
    private WebElement OS;
    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option//div")
    private List<WebElement> OSoptions;
    @FindBy(xpath = "//md-select[@placeholder='VM Class']")
    private WebElement machineClass;
    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option")
    private List<WebElement> machineClassOption;
    @FindBy(xpath = "//md-select[@placeholder='Instance type']")
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
    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option")
    private List<WebElement> commitedUsageOption;
    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[@aria-label='Add to Estimate']")
    private WebElement addToEstimate1;
    @FindBy(xpath = "//div[@ng-if='listingCtrl.showSoleTenant']//button[@aria-label='Add to Estimate']")
    private WebElement addToEstimate2;
    @FindBy(xpath = "//div[contains(text(),'VM class')]")
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
    @FindBy(xpath = "//h2[@class='md-title']//b")
    private WebElement totalCost;

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

    public CalculatorPage switchToFrame() {
        driver.switchTo().frame(firstFrame).switchTo().frame(secondFrame);
        return new CalculatorPage(driver);
    }

    public CalculatorPage createCalculator() {
        Calculator calculator = CalculatorCreation.calculatorData();
        instances(calculator);
        soleTenantNodes();
        return new CalculatorPage(driver);
    }

    public void instances(Calculator calculator) {
        numberOfInstances.sendKeys("4");
        choose(OS, OSoptions, calculator.getOSoption());
        wait.until(ExpectedConditions.invisibilityOfAllElements(OSoptions));
        choose(machineClass, machineClassOption, calculator.getMachineClassOptionText());
        choose(machineType, machineTypeOption, calculator.getMachineTypeOptionText());
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

    public CalculatorPage setAddToEstimate() {
        addToEstimate2.click();
        addToEstimate1.click();
        wait.until(ExpectedConditions.visibilityOf(addToEstimateBar));
        return new CalculatorPage(driver);
    }

    public String getActualTotalCost() {
        return totalCost.getText();
    }

    public TabPage email() {
        sendEmail.click();
        return new TabPage(driver);
    }

    public CalculatorPage sendEmail() {
        driver.switchTo().frame(firstFrame).switchTo().frame(secondFrame);
        js.executeScript("window.scrollBy(0,1200)");
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(Keys.chord(Keys.CONTROL, "v"));
        wait.until(ExpectedConditions.elementToBeClickable(sendEmailToGeneratedMail));
        sendEmailToGeneratedMail.click();
        return new CalculatorPage(driver);
    }

    private void choose(WebElement element, List<WebElement> elementList, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        for (WebElement option : elementList) {
            wait.until(ExpectedConditions.elementToBeClickable(option));
            if (option.getText().replaceAll("\\s+", "").
                    contains(text.replaceAll("\\s+", ""))) {
                option.click();
                break;
            }
        }
        wait.until(ExpectedConditions.invisibilityOfAllElements(elementList));
    }
}