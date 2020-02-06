package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class ProductScreen extends BasePage {
    public ProductScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.poqstudio.app.platform.missguided:id/action_bag")
    public MobileElement bag;

    @AndroidFindBy(xpath = "//*[@resource-id=\"com.poqstudio.app.platform.missguided:id/size_selector_bottom_sheet_dialog_fragment_recycler_view\"]/*")
    public List<MobileElement> sizeOptions;

    @AndroidFindBy(id = "com.poqstudio.app.platform.missguided:id/product_info_section_add_to_bag_button")
    public MobileElement addToBagButton;


    public MobileElement getRandomOption() {
        Random random = new Random();
        return sizeOptions.get(random.nextInt(sizeOptions.size() - 1));
    }
}
