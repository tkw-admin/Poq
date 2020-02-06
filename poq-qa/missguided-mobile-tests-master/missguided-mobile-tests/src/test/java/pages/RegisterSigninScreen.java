package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RegisterSigninScreen extends BasePage {
    public RegisterSigninScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.poqstudio.app.platform.missguided:id/content_block_login_view_register_button")
    MobileElement registerButton;

    @AndroidFindBy(id = "com.poqstudio.app.platform.missguided:id/content_block_login_view_sign_in_button")
    MobileElement signInButton;

    public boolean isPageDisplayed() {
        return registerButton.isDisplayed() && signInButton.isDisplayed();
    }

}
