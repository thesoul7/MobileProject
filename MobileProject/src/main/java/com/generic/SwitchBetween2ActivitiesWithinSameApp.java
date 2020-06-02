package com.generic;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class SwitchBetween2ActivitiesWithinSameApp {
public static AndroidDriver<MobileElement> androidDriver;
public static String appPackage1 = "com.";
public static String appActivity1 = "com.";
public static String appPackage2 = "com.";
public static String appActivity2 = "com.";
@BeforeMethod
public void openApp() throws Exception {
	DesiredCapabilities cap = new DesiredCapabilities();
	cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
	cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
	cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
	cap.setCapability(appPackage1, "");
	cap.setCapability(appActivity1, "");
	URL url =  new URL("127.0.0.1:4723/wd/hub");
	androidDriver = new AndroidDriver<MobileElement>(url,cap);
}

@Test
public void openApp2() {
	Activity activity = new Activity(appPackage2, appActivity2);
	activity.setStopApp(false);
	((AndroidDriver<MobileElement>)androidDriver).startActivity(activity);
}
}
