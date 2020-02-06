#!/bin/bash
echo "Trying to install app from Google Play store. This requires the device to be connected via USB and Play store to be configured..."
adb uninstall com.poqstudio.app.platform.missguided
adb shell am start -a android.intent.action.VIEW -d 'market://details?id=com.poqstudio.app.platform.missguided'
sleep 5
adb exec-out uiautomator dump /dev/tty > /tmp/view.xml
coords=$(perl -ne 'printf "%d %d\n", ($1+$3)/2, ($2+$4)/2 if /text="Install"[^>]*bounds="\[(\d+),(\d+)\]\[(\d+),(\d+)\]"/' /tmp/view.xml)
echo $coords
adb shell input tap $coords
sleep 10