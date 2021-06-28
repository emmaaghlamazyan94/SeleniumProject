import org.testng.Assert;
import org.testng.annotations.Test;
import pagesCloud.CalculatorPage;
import pagesCloud.HomePage;
import util.Constant;

public class HurtMePlenty extends BaseTestCloud {

    @Test
    public void googleCloudCalculatorTest() {
        HomePage homePage = new HomePage(driver);
        homePage.searchCalculator();
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        calculatorPage.switchToFrame();
        calculatorPage.createCalculator(Constant.OS_OPTION, Constant.MACHINE_CLASS_OPTION, Constant.MACHINE_TYPE_OPTION);
        calculatorPage.setAddToEstimate();
        String actualMachineClassOption = calculatorPage.getActualMachineClassOption();
        String actualMachineTypeOption = calculatorPage.getActualInstanceType();
        String actualRegion = calculatorPage.getActualRegion();
        String actualCommitmentTerm = calculatorPage.getActualCommitmentTerm();
        Assert.assertTrue(actualMachineClassOption.contains(Constant.MACHINE_CLASS_OPTION.toLowerCase()));
        Assert.assertTrue(actualMachineTypeOption.contains(Constant.MACHINE_TYPE_OPTION));
        Assert.assertTrue(actualRegion.contains("Frankfurt"));
        Assert.assertTrue(actualCommitmentTerm.contains("1 Year"));
    }
}