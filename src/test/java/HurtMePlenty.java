import org.testng.Assert;
import org.testng.annotations.Test;
import pagesCloud.CalculatorPage;
import pagesCloud.HomePage;

public class HurtMePlenty extends BaseTestCloud {
    private final String OSoption = "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS";
    private final String machineClassOption = "Regular";
    private final String machineTypeOption = "e2-standard-8";

    @Test
    public void googleCloudCalculatorTest() {
        HomePage homePage = new HomePage(driver);
        homePage.searchCalculator();
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        calculatorPage.switchToFrame();
        calculatorPage.createCalculator(OSoption, machineClassOption, machineTypeOption);
        calculatorPage.setAddToEstimate();
        String actualMachineClassOption = calculatorPage.getActualMachineClassOption();
        String actualMachineTypeOption = calculatorPage.getActualInstanceType();
        String actualRegion = calculatorPage.getActualRegion();
        String actualCommitmentTerm = calculatorPage.getActualCommitmentTerm();
        Assert.assertTrue(actualMachineClassOption.contains(machineClassOption.toLowerCase()));
        Assert.assertTrue(actualMachineTypeOption.contains(machineTypeOption));
        Assert.assertTrue(actualRegion.contains("Frankfurt"));
        Assert.assertTrue(actualCommitmentTerm.contains("1 Year"));
    }
}