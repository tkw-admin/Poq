
#!/bin/bash
installApp(){
  echo "Installing app.."
  adb uninstall com.poqstudio.app.platform.missguided
  adb shell am start -a android.intent.action.VIEW -d 'market://details?id=com.poqstudio.app.platform.missguided'
  sleep 5
  adb exec-out uiautomator dump /dev/tty > /tmp/view.xml
  coords=$(perl -ne 'printf "%d %d\n", ($1+$3)/2, ($2+$4)/2 if /text="Install"[^>]*bounds="\[(\d+),(\d+)\]\[(\d+),(\d+)\]"/' /tmp/view.xml)
  adb shell input tap $coords
  sleep 10
}

startAppium(){
	if [ "$(uname)" == "Darwin" ]; then
		startAppiumOSX
	elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
		startAppiumLinux
	else
		echo "Unknown OS system, exiting..."
		exit 1
	fi
}

startAppiumOSX(){
	if [ -z ${UDID} ] ; then
		export UDID=${IOS_UDID}
	fi
		echo "UDID is ${UDID}"
	# Create the screenshots directory, if it doesn't exist'
	mkdir -p .screenshots
        echo "Starting Appium on Mac..."
        export AUTOMATION_NAME="XCUITest"
	appium -U ${UDID} --log-no-colors --log-timestamp
}

startAppiumLinux(){
	# Create the screenshots directory, if it doesn't exist'
	mkdir -p .screenshots
        echo "Starting Appium on Linux..."
	appium --log-no-colors --log-timestamp
}

executeTests(){
	echo "Extracting tests.zip..."
	unzip tests.zip
	if [ "$(uname)" == "Darwin" ]; then
	   	echo "Running iOS Tests..."
		mvn clean test -Dcucumber.options="--tags @DemoTests --plugin com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:output --glue steps" -Dplatform=iosCloud
	elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
	    echo "Running Android Tests..."
		mvn clean test -Dcucumber.options="--tags @DemoTests --plugin com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:output --glue steps" -Dplatform=androidCloud
	fi
	echo "Finished Running Tests!"
	cp target/surefire-reports/junitreports/TEST-*.xml TEST-all.xml
}

installApp
startAppium
executeTests