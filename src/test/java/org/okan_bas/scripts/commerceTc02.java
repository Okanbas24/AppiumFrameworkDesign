package org.okan_bas.scripts;

import io.appium.java_client.AppiumBy;
import org.okan_bas.TestUtils.AndroidBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class commerceTc02 extends AndroidBaseTest
{

    @BeforeMethod
    public void preSetup()
    {
        formPage.setActivity();
    }
    @Test
    public void FillForm() throws MalformedURLException, InterruptedException {


       driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        formPage.setNameField("John Doe");
        formPage.setGender("Male");
        formPage.setCountrySelection("Argentina");
        formPage.submitForm();

       driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));

       int countProduct =  driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

       for (int i = 0; i < countProduct; i++ )
       {
         String productName =  driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
           if (productName.equalsIgnoreCase("Jordan 6 Rings"))
           {
               driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
           }

       }

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        // It is waiting until next page load with attribute value match.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
           wait.until(ExpectedConditions.attributeContains(
                By.id("com.androidsample.generalstore:id/toolbar_title"),
                "text",
                "Cart"
        ));

        String lastProduct = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(lastProduct,"Jordan 6 Rings");


    }
}