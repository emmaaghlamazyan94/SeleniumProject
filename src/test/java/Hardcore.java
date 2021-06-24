import org.testng.Assert;
import org.testng.annotations.Test;
import pagesCloud.CalculatorPage;
import pagesCloud.HomePage;
import pagesCloud.TabPage;

public class Hardcore extends BaseTestCloud {
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
        calculatorPage.email();
        TabPage tabPage = new TabPage(driver);
        tabPage.generateEmail();
        calculatorPage.sendEmail();
        tabPage.getSentEmail();
        Assert.assertTrue(tabPage.getMessageTotalCost().contains(calculatorPage.getActualTotalCost()));
    }
}