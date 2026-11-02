
package steps;

import driver.DriverFactory;
import io.cucumber.java.*;
import utils.ApiUtils;

public class Hooks {

    // Start browser before every UI scenario
    @Before("@ui")
    public void startBrowser() {
        DriverFactory.getDriver();
    }

    // Close browser after every UI scenario
    @After("@ui")
    public void stopBrowser() {
        DriverFactory.closeBrowser();
    }

    // Configure API before every API scenario
    @Before("@api")
    public void setupApi() {
        ApiUtils.setupApi();
    }
}
