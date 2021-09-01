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
                .searchCalculator();
        log.info("Link is opened");
        calculatorPage
                .switchToFrame();
        log.info("Switched to frame");
        calculatorPage
                .createCalculator()
                .setAddToEstimate();
        log.info("Added to estimation");
        calculatorPage
                .email()
                .generateEmail();
        log.debug("Email is generated and copied");
        calculatorPage
                .sendEmail();
        log.info("Email is sent");
        TabPage tabPage = new TabPage(driver)
                .getSentEmail();
        log.debug("There is problem with getting sent email");
        Assert.assertTrue(tabPage.getMessageTotalCost().contains(calculatorPage.getActualTotalCost()));
    }
}