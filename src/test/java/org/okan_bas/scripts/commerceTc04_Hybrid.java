package org.okan_bas.scripts;

import io.appium.java_client.AppiumBy;
import org.okan_bas.TestUtils.AndroidBaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class commerceTc04_Hybrid extends AndroidBaseTest
{

    @BeforeMethod
    public void preSetup()
    {
        formPage.setActivity();
    }

    @Test(dataProvider = "getData")
    public void FillForm(HashMap<String,String> input) throws MalformedURLException, InterruptedException {

       driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        formPage.setCountrySelection(input.get("name"));
        formPage.setNameField(input.get("gender"));
        formPage.setGender(input.get("country"));

        formPage.submitForm();
         //this scenario adding 2 items in the cart.
        productCatalogPage.addItemToCartByIndex(0);
        productCatalogPage.addItemToCartByIndex(0);
        productCatalogPage.goToCartPage();

        Thread.sleep(3000);
        // Following pages has same id locators.
        // Id's it is waiting until next page load and the attribute  change to value match.
        //  driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.attributeContains(
//                By.id("com.androidsample.generalstore:id/toolbar_title"),
//                "text",
//                "Cart"
//        ));

        //
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();


//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
//        Set<String> contexts = driver.getContextHandles();
//
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
//        for (String contextName : contexts)
//        {
//            System.out.println("Available context: "+ contextName);
//        }

       // driver.context("WEBVIEW_com.androidsample.generalstore");
       // driver.findElement(By.name("q")).sendKeys("Hello World");

        ///  Hybrid - Google  page ->


    }
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")
                    +"C:\\Users\\okanbas\\IdeaProjects\\AppiumFramwork\\src\\test\\java\\org\\okan_bas\\testData\\eCommerce.json");
        return new Object[][]
                {
                    {data.get(0)},
                    {data.get(1)}

                };
    }

}


