package cloud;

import org.testng.Assert;
import org.testng.annotations.Test;
import pagesCloud.CalculatorPage;
import pagesCloud.HomePage;
import pagesCloud.TabPage;

public class Hardcore extends BaseTestCloud {

    @Test
    public void googleCloudCalculatorTest() {
        CalculatorPage calculatorPage = new HomePage(driver)
                .searchCalculator()
                .switchToFrame()
                .createCalculator()
                .setAddToEstimate()
                .email()
                .generateEmail()
                .sendEmail();
        TabPage tabPage = new TabPage(driver)
                .getSentEmail();
        Assert.assertTrue(tabPage.getMessageTotalCost().contains(calculatorPage.getActualTotalCost()));
    }
}