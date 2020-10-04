package automationUI.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"automationUI"},
        features = {"src/test/resources/features"},
        strict = true,
        monochrome = true
)
public class CucumberTest {
}
