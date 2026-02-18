package org.example.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormPom {

    static WebDriver driver;

    @FindBy(xpath = "//*[text()='Forms']")
    WebElement forms;

    @FindBy(xpath = "//*[text()='Practice Form']")
    WebElement practiceForm;

    @FindBy(xpath = "//*[@id='firstName']")
    WebElement firstName;

    @FindBy(xpath = "//*[text()='lastName']")
    WebElement lastName;

    @FindBy(xpath = "//*[text()='userEmail']")
    WebElement userEmail;

    public FormPom(WebDriver driverParam){
        driver = driverParam;
        PageFactory.initElements(driver, this);
    }

    public void setFirstName (String firstNameParam){
        firstName.clear();
        firstName.sendKeys(firstNameParam);
    }

    public void setLastName (String lastNameParam){
        lastName.clear();
        lastName.sendKeys(lastNameParam);
    }

    public void setEmail (String EmailParam){
        userEmail.clear();
        userEmail.sendKeys(EmailParam);
    }

    public void clickPracticeForm(){
        practiceForm.click();
    }

    public void clickForms(){
        forms.click();
    }

    public void pause(int ms){
        try {
            Thread.sleep(ms);
        }
        catch ()
    }

}
