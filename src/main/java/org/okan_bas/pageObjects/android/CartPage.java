package org.okan_bas.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.okan_bas.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class CartPage extends AndroidActions {


    // Declare AndroidDriver instance that will be used throughout the page class
    AndroidDriver driver;

    /**
     * Constructor for CartPage class
     *
     * @param driver The AndroidDriver instance passed from the test class
     * @throws InterruptedException If thread sleep operations are interrupted
     */
    public CartPage(AndroidDriver driver) throws InterruptedException {

        // 1. CALL PARENT CLASS CONSTRUCTOR
        super(driver); // Passes the driver to the parent class constructor

        // 2. INITIALIZE DRIVER INSTANCE
        this.driver = driver; // Assign the passed driver to class field
        // Makes driver available to all methods in this class

        // 3. INITIALIZE PAGE FACTORY ELEMENTS
        // Initialize all @FindBy annotated elements using AppiumFieldDecorator
        // The decorator handles mobile-specific element location strategies
        PageFactory.initElements(
                new AppiumFieldDecorator(
                        driver,
                        Duration.ofSeconds(0) // Optional: Set implicit wait for element initialization
                ),
                this
        );

        // Note: The Thread.sleep throws InterruptedException is unusual in a constructor
        // Consider removing unless there's a specific need for waiting here
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    public List<WebElement> productList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    public WebElement totalAmount;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    public WebElement terms;

    @AndroidFindBy(id = "android:id/button1")
    public WebElement acceptButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    public WebElement proceed;

    @AndroidFindBy(className = "android.widget.CheckBox")
    public WebElement checkBox;





    public List<WebElement> getProductList() {

        return productList;
    }

    public double getProductsSum() {
        int count = productList.size();
        double totalSum = 0;
        for (int i = 0; i < count; i++) {
            String amountString = productList.get(i).getText();
            Double price = getFormattedAmount(amountString);
            totalSum = totalSum + price;  //160.97 + 120 =280.97

        }
        return totalSum;
    }

    public Double getTotalAmountDisplayed() {
        return getFormattedAmount(totalAmount.getText());
    }

    public void acceptTermsConditions() {
        longPressAction(terms);
        acceptButton.click();
    }


    public void submitOrder() {
        checkBox.click();
        proceed.click();
    }
}