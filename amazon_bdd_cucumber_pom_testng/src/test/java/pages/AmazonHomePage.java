
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.CommonMethods;
import utils.ConfigReader;

public class AmazonHomePage {

    private final WebDriver driver;
    private final CommonMethods common;

    // Locators - Amazon Home Page
    private final By logo = By.xpath("//a[@id='nav-logo-sprites']");

    private final By search = By.xpath("//input[@id='twotabsearchtextbox']");

    private final By cart = By.xpath("//a[@id='nav-cart']");

    private final By menu = By.xpath("//a[@id='nav-hamburger-menu']");

    // Category locator
    private final By mobiles = By.xpath(
        "//a[normalize-space()='Mobiles' or contains(@href,'mobile-phones')]"
    );

    // Search results locator
    private final By results = By.xpath(
        "//div[@data-component-type='s-search-result']"
    );

    // Constructor
    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
        this.common = new CommonMethods(driver);
    }

    // Open Amazon home page
    public void openHomePage() {

        driver.get(ConfigReader.getProperty("ui.url"));

        common.waitForElement(logo);
    }

    // Verify home page navigation elements
    public boolean verifyNavigationElements() {

        return common.isElementDisplayed(logo)
            && common.isElementDisplayed(search)
            && common.isElementDisplayed(cart)
            && common.isElementDisplayed(menu);
    }

    // Navigate to Mobiles category
    public void navigateToMobiles() {

        common.clickElement(mobiles);
    }

    // Verify category page
    public boolean isCategoryPageDisplayed() {

        try {
            return common.waitForUrlContains("mobile");

        } catch (Exception e) {

            By categoryHeading = By.xpath(
                "//*[self::h1 or self::span]" +
                "[contains(translate(normalize-space(.)," +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
                "'abcdefghijklmnopqrstuvwxyz'),'mobile')]"
            );

            return common.isElementDisplayed(categoryHeading);
        }
    }

    // Search for a product
    public void searchProduct(String productName) {

        common.enterText(search, productName);

        common.waitForElement(search).submit();

        common.waitForElement(results);
    }

    // Verify search results
    public boolean verifySearchResults(String productName) {

        String actualSearchText =
            common.waitForElement(search).getAttribute("value");

        return common.isElementDisplayed(results)
            && actualSearchText != null
            && actualSearchText.equalsIgnoreCase(productName);
    }

    // Capture full page screenshot
    public void fullScreenshot() {

        common.captureFullPageScreenshot("Amazon_Home_Page");
    }

    // Capture category page screenshot
    public void categoryScreenshot() {

        common.captureScreenshot("Amazon_Category_Page");
    }

    // Capture search results screenshot
    public void searchScreenshot() {

        common.captureScreenshot("Amazon_Search_Results");
    }
}
