package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class StartingScreen extends BasePage {
    public StartingScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.poqstudio.app.platform.missguided:id/content_block_banner_image_view")
    public MobileElement imageBanner;

    @AndroidFindBy(id = "com.poqstudio.app.platform.missguided:id/onboarding_activity_get_started_btn")
    public MobileElement getStartedButton;
}
