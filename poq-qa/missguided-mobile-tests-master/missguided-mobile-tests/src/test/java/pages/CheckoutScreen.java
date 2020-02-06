package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CheckoutScreen extends BasePage {
    public CheckoutScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.poqstudio.app.platform.missguided:id/activity_modular_bag_checkout_button")
    public MobileElement checkoutButton;
}
