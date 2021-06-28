import org.testng.Assert;
import org.testng.annotations.Test;
import pagesCloud.CalculatorPage;
import pagesCloud.HomePage;
import pagesCloud.TabPage;
import util.Constant;

public class Hardcore extends BaseTestCloud {

    @Test
    public void googleCloudCalculatorTest() {
        HomePage homePage = new HomePage(driver);
        homePage.searchCalculator();
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        calculatorPage.switchToFrame();
        calculatorPage.createCalculator(Constant.OS_OPTION, Constant.MACHINE_CLASS_OPTION, Constant.MACHINE_TYPE_OPTION);
        calculatorPage.setAddToEstimate();
        calculatorPage.email();
        TabPage tabPage = new TabPage(driver);
        tabPage.generateEmail();
        calculatorPage.sendEmail();
        tabPage.getSentEmail();
        Assert.assertTrue(tabPage.getMessageTotalCost().contains(calculatorPage.getActualTotalCost()));
    }
}