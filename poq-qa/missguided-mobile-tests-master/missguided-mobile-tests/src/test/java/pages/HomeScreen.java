package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

public class HomeScreen extends BasePage {
    public HomeScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")
//    @iOSXCUITFindBy() /TODO Add iOS locator when known
    public MobileElement menuButton;

    public By menuCategoryLocator(String category) {
        if (platform.equals("android")) {
            return MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                    + "new UiSelector().text(\"" + category + "\"));");
        } else { //ios
            //TODO: Add and return iOS locator when known
            return null;
        }
    }

    public void clickMenuCategory(String category) {
        driver.findElement(menuCategoryLocator(category)).click();
    }

}
