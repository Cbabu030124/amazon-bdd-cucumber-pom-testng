
package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(

    // Location of feature files
    features = "src/test/resources/features",

    // Location of step definition and hooks classes
    glue = {"steps"},

    // Cucumber execution report
    plugin = {
        "pretty",
        "html:target/cucumber-report.html"
    },

    // Display readable console output
    monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {

    // Execute scenarios sequentially
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {

        return super.scenarios();
    }
}
