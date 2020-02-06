# Missguided App - Mobile Tests
 
 ### Approach and Implementation Details
  The tests are written in Java and use:
  - Appium for UI automation on iOS and Android 
  - Cucumber and Gherkin feature files for implementing BDD scenarios
  - Maven for building the project and running the tests
  
  We used the given specification to create and implement a BDD scenario testing the functionality of adding an item to a bag before an account has been created. 
  
  We implemented the tests using the Page-Object model design pattern that makes the tests more maintainable and the code easier to follow and understand. 
  
  A video showing the tests running is available at this url: https://drive.google.com/file/d/1jNMsmXS6beTWdHMnPJOGK97C3z7zpdAd/view?usp=sharing 
  
  Some aspects from the instructions couldn't be implemented as requested:
  1. While on Android it is possible to use the Google Play released application to run automated tests on, on iOS side the application installed from AppStore is not debuggable and cannot be used for automation. This is due to Apple's security policies. An .ipa file signed with developer certificate is needed before automated tests can be run on native iOS applications (See "Configuring the app under test" from https://appium.readthedocs.io/en/latest/en/drivers/ios-xcuitest-real-devices/ )
     - However, we implemented the tests for Android with multiplatfrom support in mind, so that the same tests could be used for both iOS and Android simply by adding both Android and iOS locators for all the mobile elements used in the tests.
     - For example, elements are identified like this when the same button is available on both platforms
        ```    
            @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")
            @iOSXCUITFindBy() //TODO Add iOS locator when known
            public MobileElement menuButton;
        ```
     - If customization is needed or the flow is different from one platform to the other, this approach is used:
          ```

            public By menuCategoryLocator(String category) {
                if (platform.equals("android")) {
                    return MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                                    + "new UiSelector().text(\""+category+"\"));");
                } else { //ios
                    //TODO: Add and return iOS locator when known
                    return null;
                }
            }
         ```
    
  2. On Android side, in order to run the tests on multiple devices and multiple operating system versions as requested, we suggest using a Device Cloud service like Bitbar cloud (https://bitbar.com/) or AWS Device farm (https://aws.amazon.com/device-farm/) (both supporting Appium automation). We wanted to have a short demo showing the implemented tests running on cloud devices, but for this to work correctly we would also needed to have a version of the .apk file for the application that was not downloaded from the Play store. If we used the Google Play released apk, the app would crash on any of the devices tried in the cloud. 
     We included the scripts and support for Bitbar cloud already in the sample provided, so we could show a demo running on multiple devices in the cloud if we received a debug version of the app. 
     
  3. Currently, the tests produce a few basic reports (using Extent plugin and using Cucumber's built in HTML report) but more suitable reports could be integrated once we understand how the tests would be run and what kind of reporting would be needed. 
 
  4. Some of the details in the requirements didn't match what we found in the app:
     - the "Clearance" category only had 5 items in it, not 7. We created the tests so that they choose the last item in the Clearance category, if the number specified in the scenario is not available. 
     - the Android app didn't ask for allowing notifications (we noticed that the iOS version does, but we couldn't implement the tests against that one) so that part was left out. It wouldn't be difficult to add support for that using Appium though, if we had the iOS app ready for automation. 
     - the instructions didn't mention what size to choose for the selected item. We implemented the tests so that they choose a random size from the available ones. 
     - we didn't have on-site all the devices mentioned in the instructions, but the tests are not device dependent and can be run on any Android (and iOS) device. We included a video with them running on a Motorola Zoom One (Android 9). Using a device cloud service would mean that the tests can be run on a large amount of devices in parallel, so that would definitely be our recommendation if device compatibility is important. 
   
     
  ## USAGE
      
  ### Pre-requisites:
   - Java version 13, jdk version 13, maven
   - Appium
   
   ### Run locally:
   - Start Appium on default port:
   
       ```appium```
   
   - Connect an Android device
   
   - Run all the tests: 
   
      ```mvn clean test```
  
   - Generate report:
   
       ```mvn cluecumber-report:reporting```
       
       Report will be generated in `/target/generated-report/index.html`
   
   ### Run in the cloud:
   
   The tests can also be run in Bitbar cloud, using the `create_cloud_package.sh` to create a .zip archive that can be then uploaded to cloud.bitbar.com. A more indepth demo can be done for this when we get the debug version of the apk for Android or ipa for iOS> 