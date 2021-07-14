package pastebin;

import org.testng.Assert;
import org.testng.annotations.*;
import pagesPastebin.CreatedPastePage;
import pagesPastebin.HomePage;
import pastebin.BaseTestPastebin;

public class ICanWin extends BaseTestPastebin {
    private String pasteExpirationOption = "10 Minutes";
    private String newPasteText = "Hello from WebDriver";
    private String title = "helloweb";

    @Test
    public void newPasteCreateTest() {
        HomePage homePage = new HomePage(driver);
        homePage.createNewPaste(newPasteText, pasteExpirationOption, title);
        CreatedPastePage createdPastePage = new CreatedPastePage(driver);
        createdPastePage.submit();
        String actualTitle = createdPastePage.getActualTitle();
        String actualPaste = createdPastePage.getActualPasteText();
        String expectedTitle = title;
        String expectedPasteText = newPasteText;
        Assert.assertEquals(actualTitle, expectedTitle, "Paste with " + expectedTitle + " is not created");
        Assert.assertEquals(actualPaste, expectedPasteText, "Paste with " + expectedPasteText + " is not created");
    }
}