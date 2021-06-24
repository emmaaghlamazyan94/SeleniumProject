import org.testng.Assert;
import org.testng.annotations.Test;
import pagesPastebin.CreatedPastePage;
import pagesPastebin.HomePage;

public class BringItOn extends BaseTestPastebin {
    private String newPasteText = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private String pasteExpiration = "10 Minutes";
    private String title = "how to gain dominance among developers";
    private String syntax = "Bash";

    @Test
    public void newPasteCreateTest() {
        HomePage homePage = new HomePage(driver);
        homePage.createNewPaste(newPasteText, pasteExpiration, title);
        homePage.chooseSyntaxHighlightingOption(syntax);
        CreatedPastePage createdPastePage = new CreatedPastePage(driver);
        createdPastePage.submit();
        String actualPasteText = createdPastePage.getActualPasteText();
        String actualTitle = createdPastePage.getActualTitle();
        String actualSyntax = createdPastePage.getActualSyntax();
        String expectedTitle = title;
        String expectedPasteText = newPasteText;
        String expectedSyntax = syntax;
        Assert.assertEquals(actualTitle, expectedTitle, "Paste with " + expectedTitle + " is not created");
        Assert.assertTrue(actualPasteText.contains(expectedPasteText));
        Assert.assertEquals(actualSyntax, expectedSyntax, "Syntax is not right");
    }
}