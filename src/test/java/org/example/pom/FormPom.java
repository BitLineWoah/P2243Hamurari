package org.example.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormPom {

    static WebDriver driver;
    static public JavascriptExecutor js;

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
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void setGender(String genderParam){
        WebElement gender driver.findElement(By.xpath("//*[@id='genderWrapper']//label[text()=" + genderParam + "]"));
        gender.click();
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

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // в басик поме должно следующее быть

    public void closeAdvert() {
        try {
            js.executeScript("var elem = document.evaluate(\"//*[@id='adplus-anchor']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
        try {
            js.executeScript("var elem = document.evaluate(\"//footer\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                    "elem.parentNode.removeChild(elem);");
        } catch (Exception ignored) {}
    }


}
