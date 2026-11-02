
package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.ConfigReader;

public class DriverFactory {

    private static WebDriver driver;

    // Initialize and return WebDriver instance
    public static WebDriver getDriver() {

        if (driver == null) {

            String browser = ConfigReader.getProperty("browser");

            if ("chrome".equalsIgnoreCase(browser)) {

                driver = new ChromeDriver();
                driver.manage().window().maximize();

            } else {

                throw new RuntimeException(
                    "Unsupported browser: " + browser
                );
            }
        }

        return driver;
    }

    // Close the browser and release WebDriver
    public static void closeBrowser() {

        if (driver != null) {

            driver.quit();
            driver = null;
        }
    }
}
