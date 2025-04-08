package org.okan_bas.pageObjects.android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.okan_bas.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class FormPage extends AndroidActions
{
    AndroidDriver driver;

    //Contractor
    public FormPage(AndroidDriver driver) throws InterruptedException
    {
        super(driver); // call parent class constructor
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this); //
    }

    /**
        *  Encapsulating the locator as using private access modifier. hiding the implementation details.
            they can be only accessible from public action methods

    */


    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleOption;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement  maleOption;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement  shopButton;

    @AndroidFindBy(id = "android:id/text1")
    private WebElement  countrySelection;

    @AndroidFindBy(xpath = "//android.widget.Toast[@text='Please enter your name']")
    private WebElement toastMessage;



    ///  Public Actions Methods
    public void setNameField(String name)
    {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }


    public void setGender(String gender)
    {
        if(gender.contains("Female"))
            femaleOption.click();

        else
            maleOption.click();
    }

    public void setCountrySelection(String countryName)
    {
        countrySelection.click();
        scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();

    }

    public  void submitForm()
    {
        shopButton.click();
    }

    public void getToastMessage(String toast)
    {

    }

    public void setActivity()
    {

        Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
        //driver.startActivity(activity);
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity",
                ImmutableMap.of(
                        "intent",
                        "com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
    }


}
