package org.okan_bas.scripts;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;

import org.okan_bas.TestUtils.AndroidBaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class LongPress extends AndroidBaseTest
{

    @Test
    public void LongPressGesture() throws MalformedURLException, InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
    WebElement ele = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='People Names']"));
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of("element",
                ((RemoteWebElement) ele).getId(),"duration",2000));
        Thread.sleep(2000);


    }
}
