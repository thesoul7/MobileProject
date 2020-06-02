package com.generic;

import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class SwitchFromWebToNative {
public AndroidDriver<MobileElement> driver ;
String messageAppPackageName = "com.google.android.apps.messaging";
String messageAppActivityName = "com.google.android.apps.messaging.ui.ConversationListActivity";
String chromeAppPackageName = "com.android.chrome";//"com.justdial.search";
String chromeAppActivityName = "com.google.android.apps.chrome.IntentDispatcher";//"com.google.android.apps.chrome.Main";//"com.justdial.search.SplashScreenNewActivity";
@BeforeMethod
public void setupstart() throws Exception {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
    capabilities.setCapability("appPackage", chromeAppPackageName);
    capabilities.setCapability("appActivity", chromeAppActivityName);
    capabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
    capabilities.setCapability(MobileCapabilityType.FULL_RESET, "false");
    driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   System.out.println(driver.getCurrentPackage());
   /* try {
		driver.findElement(By.id("com.google.android.apps.messaging:id/conversation_list_spam_popup_positive_button")).click();
	} catch (Exception e) {
		e.printStackTrace();
	}*/
    //driver.findElementByAccessibilityId("Start chat").click();
  //  driver.findElement(By.xpath("//com.google.android.material.chip.Chip[@text='Images']")).click();
 
 
  try {
	  driver.findElement(By.id("com.android.chrome:id/terms_accept")).click();
	  driver.findElement(By.id("com.android.chrome:id/next_button")).click();
	  driver.findElement(By.id("com.android.chrome:id/negative_button")).click();
	  driver.findElement(By.id("com.android.chrome:id/negative_button")).click();
} catch (Exception e) {
	e.printStackTrace();
}
  System.out.println(driver.getCurrentPackage());
   //driver.findElement(By.id("com.justdial.search:id/terms_accept_btn")).click();
}
@Test
public void LaunchApps() throws Exception {
	//driver.startActivity(new Activity(messageAppPackageName,messageAppActivityName));
	Activity activity = new Activity(messageAppPackageName, messageAppActivityName);
    activity.setStopApp(false);
    ((AndroidDriver<MobileElement>) driver).startActivity(activity);
	 System.out.println(driver.getCurrentPackage());
	// driver.findElement(By.xpath("//com.google.android.material.chip.Chip[@text='Images']")).click();
	  try {
			driver.findElement(By.id("com.google.android.apps.messaging:id/conversation_list_spam_popup_positive_button")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 String text= driver.findElementByAccessibilityId("Start chat").getText();
	 System.out.println(text);
	  //driver.findElement(MobileBy.AndroidUIAutomator("")).click();
	 Activity activity1 = new Activity("com.android.chrome", "com.google.android.apps.chrome.Main");
	    activity.setStopApp(false);
	    ((AndroidDriver<MobileElement>) driver).startActivity(activity1);
	    System.out.println(driver.getCurrentPackage());
	    try {
	    	 driver.findElement(By.id("com.android.chrome:id/negative_button")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    driver.findElement(By.id("com.android.chrome:id/search_box_text")).click();
	    driver.findElement(By.id("com.android.chrome:id/url_bar")).sendKeys(text);
//	    driver.startActivity(new Activity("com.justdial.search","com.justdial.search.SplashScreenNewActivity"));
//	    driver.findElement(By.id("com.justdial.search:id/terms_accept_btn")).click();
}
@AfterTest
public void closeApp() {
	driver.quit();
}
}
