import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "json:target/cucumber-report/cucumber.json",
                "pretty:target/cucumber-report/pretty.txt",
                "html:target/cucumber-report//cucumber-html"
        },
        features = {"src/test/features"},
        glue = {"steps"}
)

public class RunCucumberTests {

}