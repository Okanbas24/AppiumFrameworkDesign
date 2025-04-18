package org.okan_bas.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.okan_bas.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogPage extends AndroidActions
{
    AndroidDriver driver;




    @AndroidFindBy(xpath = "//android.widget.TextView[@text ='ADD TO CART']")
    private List<WebElement> addToCart;


    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private   WebElement cart;

    //Contractor
    public ProductCatalogPage(AndroidDriver driver) throws InterruptedException
    {
        super(driver); // call parent class constructor
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this); //
    }

    public void addItemToCartByIndex(int index)
    {
        addToCart.get(index).click();
    }

    public void goToCartPage()
    {
        cart.click();
    }
}
