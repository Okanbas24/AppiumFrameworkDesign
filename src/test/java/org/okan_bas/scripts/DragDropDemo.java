package org.okan_bas.scripts;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.okan_bas.TestUtils.AndroidBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class DragDropDemo extends AndroidBaseTest
{
    @Test
    public void dragDropTest() throws MalformedURLException, InterruptedException
    {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement sourse = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));

        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) sourse).getId(),
                "endX", 484,
                "endY", 424
        ));
        String result = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(result,"Dropped!");
    }
}
