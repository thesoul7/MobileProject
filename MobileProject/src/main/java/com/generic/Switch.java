package com.generic;

import java.net.MalformedURLException;
import java.net.URL;
 
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
 
public class Switch {
    public AppiumDriver<MobileElement> driver = null;
 
    String calculatorAppPackageName = "com.sec.android.app.popupcalculator";
    String calculatorAppActivityName = "Calculator";
    String settingsAppPackageName = "com.android.settings";
    String settingsAppActivityName = "com.android.settings.GridSettings";
 
    @BeforeTest
    public void setupstart() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability("appPackage", calculatorAppPackageName);
        capabilities.setCapability("appActivity", calculatorAppActivityName);
        driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
    }
 
    @Test
    public void calcTest1() throws Exception {
        //Multiply 2 numbers in calculator app
        driver.findElement(By.xpath("//android.widget.Button[@text='4']")).click();
        driver.findElement(By.xpath("//android.widget.Button[@content-desc='Multiplication']")).click();
        driver.findElement(By.xpath("//android.widget.Button[@text='4']")).click();
        driver.findElement(By.xpath("//android.widget.Button[@content-desc='Equal']")).click();
 
        // launch settings App
        Activity activity = new Activity(settingsAppPackageName, settingsAppActivityName);
        activity.setStopApp(false);
        ((AndroidDriver<MobileElement>) driver).startActivity(activity);
 
        // Switch OFF WIFI
        driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc='Wi-Fi']")).click();
        driver.findElement(By.className("android.widget.Switch")).click();
 
        // Re launch calculator App
        activity = new Activity(calculatorAppPackageName, calculatorAppActivityName);
        activity.setStopApp(false);
        ((AndroidDriver<MobileElement>) driver).startActivity(activity);
        String result = driver.findElement(By.className("android.widget.EditText")).getText();
        System.out.println("Result : " + result);
 
    }
 
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}