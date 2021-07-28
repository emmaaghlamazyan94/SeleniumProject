package steps;

import driver.DriverSingleton;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pagesCloud.CalculatorPage;
import pagesCloud.HomePage;
import pagesCloud.TabPage;

public class Steps {

    @Given("Initialize driver")
    public WebDriver initializeDriver() {
        return DriverSingleton.getDriver();
    }

    CalculatorPage calculatorPage = new CalculatorPage(initializeDriver());
    HomePage homePage = new HomePage(initializeDriver());
    TabPage tabPage = new TabPage(initializeDriver());


    @Given("^The user opens (.*)$")
    public void theUserOpensLink(String src) {
        initializeDriver().get(src);
        homePage.waitPageLoad();
    }

    @When("The user searches text")
    public void searchCalculator() {
        homePage.searchCalculator();
    }

    @When("Switch to frame")
    public void switchToFrame() {
        calculatorPage.switchToFrame();
    }

    @When("Calculator information is filled in")
    public void createCalculator() {
        calculatorPage.createCalculator();
    }

    @When("Send filled information to estimation")
    public void sendToEstimation() {
        calculatorPage.setAddToEstimate();
    }

    @When("Send email about the calculator cost information")
    public void sendEmail() {
        calculatorPage.email()
                .generateEmail()
                .sendEmail();
    }

    @Then("Calculator's estimated cost is the same as expected")
    public void estimatedCostTest() {
        Assert.assertTrue(tabPage.getMessageTotalCost().contains(calculatorPage.getActualTotalCost()));
    }

    @Then("Calculator's estimated cost from received email is the same as expected")
    public void costFromReceivedEmailTest() {
        tabPage.getSentEmail();
    }
}