package org.okan_bas.scripts;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.okan_bas.TestUtils.AndroidBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

public class miscellaneousAppiumActios extends AndroidBaseTest
{


    @Test
    public void Miscellaneous() throws MalformedURLException
    {
        //App Package & App Activity
        // adb shell dumpsys window | find  "mCurrentFocus" -> windows
        // adb shell "dumpsys window | grep 'mCurrentFocus'" -> windows
        // adb shell dumpsys window | grep -E  'mCurrentFocus' -> Mac
        Activity activity = new Activity(
                "io.appium.android.apis",
                "io.appium.android.apis.preference.PreferenceDependencies");
               // driver.startActivity(activity);
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity",
                ImmutableMap.of(
                "intent",
                "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));

        //driver.findElement(AppiumBy.accessibilityId("Preference")).click(); Below code take care of this step
        //driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click(); Below code take care of this step

        driver.findElement(AppiumBy.id("android:id/checkbox")).click();

        DeviceRotation landScape = new DeviceRotation(0,0,90);
        driver.rotate(landScape);

        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertTitle,"WiFi settings");

        ///  copy paste
        ///  copy to clipboard - paste it clipboard
        driver.setClipboardText("Hello World");
        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());

        driver.pressKey(new KeyEvent(AndroidKey.ENTER));

        driver.findElement(AppiumBy.id("android:id/button1")).click();

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));

    }

}
