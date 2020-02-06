package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CategoryScreen extends BasePage {

    public CategoryScreen(AppiumDriver driver) {
        super(driver);
    }

    public By currentDisplayedItemsLocator() {
        if (platform.equals("android")) {
            return By.xpath("//*[@resource-id=\"com.poqstudio.app.platform.missguided:id/product_activity_list_swipe_refresh_layout\"]/androidx.recyclerview.widget.RecyclerView/*");
        } else { //ios
            //TODO: Add and return iOS locator when known
            return null;
        }
    }

    public By currentDisplayedItemsTextsLocator() {
        if (platform.equals("android")) {
            return By.xpath("//*[@resource-id=\"com.poqstudio.app.platform.missguided:id/product_activity_list_swipe_refresh_layout\"]/androidx.recyclerview.widget.RecyclerView/*/android.widget.LinearLayout/android.widget.TextView");
        } else { //ios
            //TODO: Add and return iOS locator when known
            return null;
        }
    }

    public void clickNthItem(int itemNumber) {
        getNthItem(itemNumber).click();
    }

    private MobileElement getNthItem(int itemNumber) {
        List<String> allItems = new ArrayList<>();
        List<MobileElement> items = new ArrayList<>();
        do {
            List<MobileElement> currentDisplayedItems = driver.findElements(currentDisplayedItemsLocator());
            List<MobileElement> currentDisplayedItemsText = driver.findElements(currentDisplayedItemsTextsLocator());

            if (allItems.contains(currentDisplayedItemsText.get(currentDisplayedItemsText.size() - 1).getText())) {
                break;
            }
            for (MobileElement element : currentDisplayedItemsText
            ) {
                if (!allItems.contains(element.getText())) {
                    allItems.add(element.getText());
                    items.add(currentDisplayedItems.get(currentDisplayedItemsText.indexOf(element)));

                }

            }
            if (allItems.size() >= itemNumber) {
                break;
            }

            Point p1 = getPoint(currentDisplayedItems.get(currentDisplayedItems.size() - 1));
            Point p2 = getPoint(currentDisplayedItems.get(0));
            new TouchAction<>(driver)
                    .press(PointOption.point(p1.x, p1.y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(5000)))
                    .moveTo(PointOption.point(p2.x, p2.y)).release().perform();
            currentDisplayedItemsText.clear();

        } while (true);


        if (allItems.size() < itemNumber) {
            System.out.println("Click on the last product in the list");
            return items.get(allItems.size() - 1);
        } else {
            System.out.println("Click on item: " + itemNumber);
            return items.get(itemNumber - 1);
        }
    }

    private Point getPoint(MobileElement element) {

        return element.getRect().getPoint();

    }
}
