package com.generic;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class SwitchActivity {
	public void switchActivity(AndroidDriver<MobileElement> androidDriver,String appPackage, String appActivity) {
		Activity activity = new Activity(appPackage, appActivity);
		androidDriver.startActivity(activity);
	}
	public void clickOnElementUsingAccessibilityID(AndroidDriver<MobileElement> androidDriver,String accessbilityID) {
		try {
			androidDriver.findElementByAccessibilityId(accessbilityID).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
