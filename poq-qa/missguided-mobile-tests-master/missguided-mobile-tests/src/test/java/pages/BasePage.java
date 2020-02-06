package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected AppiumDriver driver;
    protected String platform;

    BasePage(AppiumDriver driver) {
        if (System.getProperty("platform", "android").contains("android")) {
            this.platform = "android";
        } else {
            this.platform = "ios";
        }
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
