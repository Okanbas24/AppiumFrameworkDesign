package org.okan_bas.scripts;

import org.okan_bas.TestUtils.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class commerceTc03 extends AndroidBaseTest {


    @BeforeMethod(alwaysRun = true)
    public void preSetup()
    {
        formPage.setActivity();
    }

    @Test(dataProvider = "getData", groups = {"Smoke"})
    public void FillForm(String name, String gender, String country) throws MalformedURLException, InterruptedException
    {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        formPage.setCountrySelection(country);
        formPage.setNameField(name);
        formPage.setGender(gender);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        formPage.submitForm();

        //this scenario adding 2 items in the cart.
        productCatalogPage.addItemToCartByIndex(0);
        productCatalogPage.addItemToCartByIndex(0);
        productCatalogPage.goToCartPage();

        // Following pages has same id locators.
        // Id's it is waiting until next page load and the attribute  change to value match.

        Thread.sleep(2000);
//       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
//           wait.until(ExpectedConditions.attributeContains(
//                By.id("com.androidsample.generalstore:id/toolbar_title"),
//                "text",
//                "Cart"
//       ));
        double totalSum = cartPage.getTotalAmountDisplayed();
        cartPage.getTotalAmountDisplayed();
        double displaysFormattedSum = cartPage.getProductsSum();
        Assert.assertEquals(totalSum, displaysFormattedSum);
        cartPage.submitOrder();

        Thread.sleep(2000);

    }

  @DataProvider
  public Object[][] getData() {
    return new Object[][]
            {
                    {"Lara", "Female", "Argentina"},
                    {"Fori", "Male", "Argentina"},
            };


    }

}