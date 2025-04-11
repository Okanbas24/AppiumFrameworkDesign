package org.okan_bas.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.okan_bas.pageObjects.android.*;
import org.okan_bas.utils.AppiumUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AndroidBaseTest extends AppiumUtils {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public UiAutomator2Options options;

    // Page Objects
    public FormPage formPage;
    public ProductCatalogPage productCatalogPage;
    public CartPage cartPage;



    /**
     * Configures Appium server and initializes test environment before test class execution
     * @throws IOException          If properties file cannot be read
     * @throws InterruptedException If thread operations are interrupted
     */
    @BeforeClass(alwaysRun = true)
    public void configureAppium() throws IOException, InterruptedException {

        // 1. LOAD CONFIGURATION PROPERTIES
        Properties prop = new Properties();
        // Load properties file from resources directory
        FileInputStream file = new FileInputStream(
                System.getProperty("user.dir") +
                        "\\src\\main\\java\\org\\okan_bas\\resources\\data.properties");
        prop.load(file);


        // 2. GET CONFIGURATION VALUES
        String ipAddress = prop.getProperty("ipAddress");        // Server IP (e.g., "127.0.0.1")
        String port = prop.getProperty("port");                  // Appium port (e.g., "4723")
        String deviceName = prop.getProperty("AndroidDeviceName"); // Device name from config
        String appName = prop.getProperty("appName");            // Application filename
        String appiumJsPath = prop.getProperty("appiumJsPath");  // Path to Appium JS file

        // 3. START APPIUM SERVER
        service = startAppiumServer(
                ipAddress,
                Integer.parseInt(port),  // Convert port string to integer
                appiumJsPath
        );

        // 4. CONFIGURE DRIVER CAPABILITIES
        options = new UiAutomator2Options();
        options.setDeviceName(deviceName);  // Set desired device name
        options.setApp(System.getProperty("user.dir") +
                "\\src\\test\\java\\org\\okan_bas\\resources\\" + appName);  // Set app path


        // 5. INITIALIZE ANDROID DRIVER
        driver = new AndroidDriver(service.getUrl(), options);  // Connect to local Appium server

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // Set default wait


        // 6. INITIALIZE PAGE OBJECTS
        formPage = new FormPage(driver);
        productCatalogPage = new ProductCatalogPage(driver);
        cartPage = new CartPage(driver);
    }

    /**
     * Cleans up test environment after test class execution
     */
    @AfterClass(alwaysRun = true)
    public void tearDown() {

        // 1. CLOSE DRIVER SESSION
        if (driver != null) {
            driver.quit();  // Properly closes app and session
        }

        // 2. STOP APPIUM SERVER
        if (service != null) {
            service.stop();  // Shuts down local Appium server
        }
    }
}