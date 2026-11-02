
package steps;

import driver.DriverFactory;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.AmazonHomePage;

public class AmazonUiSteps {

    private AmazonHomePage page;

    // Initialize the Page Object only when required
    private AmazonHomePage page() {

        if (page == null) {
            page = new AmazonHomePage(DriverFactory.getDriver());
        }

        return page;
    }

    // Open Amazon home page
    @Given("I open the Amazon home page")
    public void openHome() {
        page().openHomePage();
    }

    // Verify Amazon home page navigation elements
    @Then("I should verify the Amazon home page navigation elements")
    public void verifyHome() {

        Assert.assertTrue(
            page().verifyNavigationElements(),
            "Amazon navigation elements are missing"
        );
    }

    // Capture full page screenshot
    @And("I capture the full page screenshot")
    public void fullScreenshot() {
        page().fullScreenshot();
    }

    // Navigate to Mobiles category
    @When("I navigate to the Mobiles category")
    public void mobiles() {
        page().navigateToMobiles();
    }

    // Verify Mobiles category page
    @Then("I should verify the category page is displayed")
    public void verifyCategory() {

        Assert.assertTrue(
            page().isCategoryPageDisplayed(),
            "Mobiles category page not displayed"
        );
    }

    // Capture category page screenshot
    @And("I capture the category screenshot")
    public void categoryScreenshot() {
        page().categoryScreenshot();
    }

    // Search for a product
    @When("I search for product {string}")
    public void search(String term) {
        page().searchProduct(term);
    }

    // Verify product search results
    @Then("I should verify the search results for {string}")
    public void verifySearch(String term) {

        Assert.assertTrue(
            page().verifySearchResults(term),
            "Search results not displayed as expected"
        );
    }

    // Capture search results screenshot
    @And("I capture the search results screenshot")
    public void searchScreenshot() {
        page().searchScreenshot();
    }
}
