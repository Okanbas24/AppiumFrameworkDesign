package org.okan_bas.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class AppiumUtils {

    public AppiumDriverLocalService service;

    /**
     * Starts a local Appium server with the specified configuration
     *
     * @param ipAddress The IP address to bind the Appium server to (e.g., "127.0.0.1")
     * @param port The port number to run the Appium server on (e.g., 4723)
     * @param appiumJsPath Full path to the Appium main JS file (e.g., "/usr/local/lib/node_modules/appium/build/lib/main.js")
     * @return The running AppiumDriverLocalService instance
     * @throws AppiumServerHasNotBeenStartedLocallyException If server fails to start
     */
    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port, String appiumJsPath) {

       // Create a new AppiumServiceBuilder to configure the server
        service = new AppiumServiceBuilder()
                // Set path to Appium's main JS file
                .withAppiumJS(new File(appiumJsPath))
                // Set the IP address the server will listen on
                .withIPAddress(ipAddress)
                // Set the port number
                .usingPort(port)
                // Build the service configuration
                .build();


        // Launch the Appium server with the configured settings
        service.start();
        // Note: service.start() will throw an exception if server fails to start
        // Return the running service instance
        return service;
    }



    public Double getFormattedAmount(String amount) {
        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }


    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        // convert json file content to json string
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {

                });

        return data;

    }


    public void waitForElementToAppear(WebElement ele, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
    }

    /**
     * Captures a screenshot, saves it to disk with timestamp, and returns it as Base64 string
     *
     * @param driver The AppiumDriver instance used to capture the screenshot
     * @param testName Name of the test (will be used in filename)
     * @return Base64 encoded string of the screenshot (for HTML embedding)
     * @throws IOException If there are issues with file operations
     */
    public String captureScreenshot(AppiumDriver driver, String testName) throws IOException {
        // 1. Create directory structure
        String screenshotDir = System.getProperty("user.dir") + "/reports/screenshots/";
        new File(screenshotDir).mkdirs();

        // 2. Generate timestamp in dd.MM.yyyy h:mm:ss a format (04.07.2025 2:40:17 PM)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy_h:mm:ss a", Locale.US);
        String timeStamp = dateFormat.format(new Date());

        // 3. Create filename (TestName_dd.MM.yyyy_h.mm.ss a.png)
        String fileName = String.format("%s_%s.png",
                testName.replaceAll("[^a-zA-Z0-9]", "_"), // Sanitize test name
                timeStamp.replaceAll("[: ]", ".") // Replace : and spaces with .
                        .replace("..", ".")); // Handle double dots

        String filePath = screenshotDir + fileName;

        // 4. Capture and save screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(filePath));

        // 5. Return Base64 for ExtentReports
        byte[] fileContent = FileUtils.readFileToByteArray(srcFile);
        String base64 = Base64.getEncoder().encodeToString(fileContent);

        System.out.println("Screenshot saved: " + filePath); // Debug log
        return "data:image/png;base64," + base64;
    }

}

