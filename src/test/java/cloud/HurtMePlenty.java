package cloud;

import org.testng.Assert;
import org.testng.annotations.Test;
import pagesCloud.CalculatorPage;
import pagesCloud.HomePage;
import service.CalculatorCreation;

public class HurtMePlenty extends BaseTestCloud {

    @Test
    public void googleCloudCalculatorTest() {
        CalculatorPage calculatorPage = new HomePage(driver)
                .searchCalculator()
                .switchToFrame()
                .createCalculator()
                .setAddToEstimate();
        String actualMachineClassOption = calculatorPage.getActualMachineClassOption();
        String actualMachineTypeOption = calculatorPage.getActualInstanceType();
        String actualRegion = calculatorPage.getActualRegion();
        String actualCommitmentTerm = calculatorPage.getActualCommitmentTerm();
        Assert.assertTrue(actualMachineClassOption.contains(CalculatorCreation.MACHINE_CLASS_OPTION.toLowerCase()));
        Assert.assertTrue(actualMachineTypeOption.contains(CalculatorCreation.MACHINE_TYPE_OPTION));
        Assert.assertTrue(actualRegion.contains("Frankfurt"));
        Assert.assertTrue(actualCommitmentTerm.contains("1 Year"));
    }
}