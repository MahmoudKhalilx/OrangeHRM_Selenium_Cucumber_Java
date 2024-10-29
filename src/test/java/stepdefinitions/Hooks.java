package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.DriverManager;

import java.io.IOException;
import java.time.Duration;

import static utilities.DataUtils.getPropertyValue;
import static utilities.DriverManager.getDriver;
import static utilities.DriverManager.setupDriver;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) throws IOException {
        setupDriver(getPropertyValue("TestData", "Browser"));
        getDriver().get(getPropertyValue("TestData", "BASE_URL"));
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Starting scenario: " + scenario.getName());

    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take screenshot if scenario fails
            final byte[] screenshot = ((TakesScreenshot) getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }

        DriverManager.quitDriver();
    }
}