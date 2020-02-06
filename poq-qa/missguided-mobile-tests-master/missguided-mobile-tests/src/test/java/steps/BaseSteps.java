package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseSteps {

    private AppiumDriver driver;

    @Before
    public void beforeScenario() throws IOException
    {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        if (System.getProperty("platform", "android").contains("android")) {
            desiredCapabilities.setCapability("platformName", "Android");
            desiredCapabilities.setCapability("automationName", "Appium");
            desiredCapabilities.setCapability("deviceName", "Android");
            desiredCapabilities.setCapability("app", getApplicationPath());
            driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        } else {
            desiredCapabilities.setCapability("platformName", "iOS");
            desiredCapabilities.setCapability("automationName", "XCUITest");
            desiredCapabilities.setCapability("deviceName", "iPhone");
            desiredCapabilities.setCapability("udid", System.getProperty("UDID"));
            desiredCapabilities.setCapability("app", getApplicationPath());
        }
    }

    private String getApplicationPath() throws IOException {
        String path;
        String platform = System.getProperty("platform", "android");
        if (platform.contains("android")) {
            if (platform.contains("Cloud")) {
                path = System.getProperty("user.dir") + "/application.apk";
            } else {
                File app = new File(new File("./app").getCanonicalPath(), "missguided.apk");
                path = app.getAbsolutePath();
            }
        } else {
            if (platform.contains("Cloud")) {
                path = System.getProperty("user.dir") + "/application.ipa";
            } else {
                File app = new File(new File("./app").getCanonicalPath(), "missguided.ipa");
                path = app.getAbsolutePath();
            }
        }
        return path;
    }

    @AfterStep
    public void screenshot(Scenario scenario){
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }

    @After
    public void afterScenario(Scenario scenario){
        screenshot(scenario);
        driver.quit();
    }


    @Given("I have just started the app for the first time")
    public void iHaveJustStartedTheAppForTheFirstTime() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        StartingScreen startingScreen=new StartingScreen(driver);
        Assert.assertTrue(startingScreen.imageBanner.isDisplayed());
    }

    @When("I navigate to the shop")
    public void iNavigateToTheShop() {
        StartingScreen startingScreen=new StartingScreen(driver);
        startingScreen.getStartedButton.click();

    }

    @And("I select {string} from the menu")
    public void iSelectFromTheMenu(String category) {
        HomeScreen homeScreen=new HomeScreen(driver);
        homeScreen.menuButton.click();
        homeScreen.clickMenuCategory(category);


    }

    @And("I select the {string}th item in the list")
    public void iSelectTheThItemInTheList(String n) {
        int number_order=Integer.parseInt(n);
        CategoryScreen categoryScreen=new CategoryScreen(driver);
        categoryScreen.clickNthItem(number_order);

    }

    @And("I add it to the bag")
    public void iAddItToTheBag() {
        ProductScreen productScreen=new ProductScreen(driver);
        productScreen.addToBagButton.click();
    }

    @And("I select the size")
    public void iSelectTheSize() {
        ProductScreen productScreen=new ProductScreen(driver);
        productScreen.getRandomOption().click();
    }

    @And("I select the bag")
    public void iSelectTheBag() {
        ProductScreen productScreen=new ProductScreen(driver);
        productScreen.bag.click();
    }

    @And("I select Pay")
    public void iSelectPay() {
        CheckoutScreen checkoutScreen=new CheckoutScreen(driver);
        checkoutScreen.checkoutButton.click();
    }

    @Then("the Sign in and Register page is displayed")
    public void theSignInAndRegisterPageIsDisplayed() {
        RegisterSigninScreen registerSigninScreen=new RegisterSigninScreen(driver);
        Assert.assertTrue(registerSigninScreen.isPageDisplayed());
    }

}
