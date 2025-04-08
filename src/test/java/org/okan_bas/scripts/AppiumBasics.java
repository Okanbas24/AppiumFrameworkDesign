package org.okan_bas;

import io.appium.java_client.AppiumBy;
import org.okan_bas.TestUtils.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

public class AppiumBasics extends AndroidBaseTest {
    /// Appium supports -> Xpath, id, accessibilityId, androidUIAutomator  locators.
    /// TagName[@Attribute='value']  -> create your on xpath

    @Test
    public void WifiSettingName() throws MalformedURLException {

        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();
        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertTitle, "WiFi settings");

        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Okan Bas");
        driver.findElement(AppiumBy.id("android:id/button1")).click();

    }


}