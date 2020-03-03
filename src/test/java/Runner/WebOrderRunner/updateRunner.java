package Runner.WebOrderRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com.cucumber.features.WebOrder/UpdateOrder.feature",
        glue = "StepDefinitions.WebOrderStepDefs",
        dryRun = false,
        monochrome = true
)
public class updateRunner {
}