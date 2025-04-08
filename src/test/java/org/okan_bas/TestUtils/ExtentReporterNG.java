package org.okan_bas.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.IOException;



public class ExtentReporterNG     {

    static ExtentReports extent;




    /**
     * Creates and configures an ExtentReports instance for generating HTML test reports
     *
     * @return Configured ExtentReports instance ready for test logging
     * @throws IOException If report file cannot be created
     */
    public static ExtentReports getReporterObject() throws IOException {


        // SET REPORT FILE PATH
        // Creates path to report HTML file in project_root/reports/index.html
        String path = System.getProperty("user.dir") + "//reports//index.html";


        // CONFIGURE REPORT VISUALIZATION
        // Create ExtentSparkReporter which handles the HTML file generation
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);

        // Set display name that appears in the report
        reporter.config().setReportName("Mobile Automation Results");

        // Set browser tab title
        reporter.config().setDocumentTitle("Test Results");


        // INITIALIZE MAIN REPORTING ENGINE
        // Create the main ExtentReports instance that will track all test data
        extent = new ExtentReports();

        // Attach the configured Spark reporter to the ExtentReports instance
        extent.attachReporter(reporter);

        // Add system/environment information to report header
        extent.setSystemInfo("Test Automation Engineer", "Okan Bas");


        // RETURN CONFIGURED REPORTER
        return extent;
    }

}
