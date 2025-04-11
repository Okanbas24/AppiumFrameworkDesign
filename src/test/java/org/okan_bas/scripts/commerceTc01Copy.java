package org.okan_bas.scripts;

import org.okan_bas.TestUtils.AndroidBaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class commerceTc01Copy extends AndroidBaseTest
{
    @Test()
    public void FillForm_ErrorValidation() throws MalformedURLException, InterruptedException {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        formPage.setCountrySelection("Argentina");
        formPage.setGender("Female");
        formPage.submitForm();
        String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[@text='Please enter your name']")).getText();
        Assert.assertEquals(toastMessage, "Please enter your name");
    }
}

