
package utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Base64;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Constructor - initialize WebDriver and explicit wait
    public CommonMethods(WebDriver d) {

        driver = d;

        wait = new WebDriverWait(
                d,
                Duration.ofSeconds(
                        ConfigReader.getIntProperty("explicit.wait")
                )
        );
    }

    // Wait until element is visible
    public WebElement waitForElement(By by) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(by)
        );
    }

    // Click an element after it becomes clickable
    public void clickElement(By by) {

        wait.until(
                ExpectedConditions.elementToBeClickable(by)
        ).click();
    }

    // Clear existing text and enter new text
    public void enterText(By by, String text) {

        WebElement element = waitForElement(by);

        element.clear();
        element.sendKeys(text);
    }

    // Verify whether an element is displayed
    public boolean isElementDisplayed(By by) {

        try {
            return waitForElement(by).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    // Wait until the URL contains the expected text
    public boolean waitForUrlContains(String text) {

        return wait.until(
                ExpectedConditions.urlContains(text)
        );
    }

    // Capture normal screenshot
    public void captureScreenshot(String name) {

        try {

            Path dir = Path.of("verification");

            Files.createDirectories(dir);

            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            Files.write(
                    dir.resolve(name + ".png"),
                    screenshot
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Screenshot failed",
                    e
            );
        }
    }

    // Capture full-page screenshot using Chrome DevTools Protocol
    @SuppressWarnings("unchecked")
    public void captureFullPageScreenshot(String name) {

        try {

            if (!(driver instanceof ChromeDriver)) {
                throw new IllegalStateException(
                        "Chrome is required for full-page screenshot"
                );
            }

            ChromeDriver chrome = (ChromeDriver) driver;

            // Get page dimensions
            Map<String, Object> metrics =
                    chrome.executeCdpCommand(
                            "Page.getLayoutMetrics",
                            Map.of()
                    );

            Map<String, Object> size =
                    (Map<String, Object>) metrics.get("cssContentSize");

            double width =
                    ((Number) size.get("width")).doubleValue();

            double height =
                    ((Number) size.get("height")).doubleValue();

            // Capture complete page
            Map<String, Object> result =
                    chrome.executeCdpCommand(
                            "Page.captureScreenshot",
                            Map.of(
                                    "format", "png",
                                    "captureBeyondViewport", true,
                                    "clip", Map.of(
                                            "x", 0,
                                            "y", 0,
                                            "width", width,
                                            "height", height,
                                            "scale", 1
                                    )
                            )
                    );

            // Decode Base64 screenshot
            byte[] screenshot = Base64.getDecoder().decode(
                    (String) result.get("data")
            );

            // Save screenshot in verification folder
            Path dir = Path.of("verification");

            Files.createDirectories(dir);

            Files.write(
                    dir.resolve(name + ".png"),
                    screenshot
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Full-page screenshot failed",
                    e
            );
        }
    }
}
