package org.okan_bas.scripts;


import io.appium.java_client.AppiumBy;

import org.okan_bas.TestUtils.AndroidBaseTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class ScrollDemo extends AndroidBaseTest {
    @Test
    public void ScrollDemoTest() throws MalformedURLException, InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));

    }
}