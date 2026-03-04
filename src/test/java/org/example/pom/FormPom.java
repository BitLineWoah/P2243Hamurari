package org.example.pom;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.example.utils.Utils;

import java.io.ByteArrayInputStream;

public class FormPom {

    static public WebDriver driver;
    static public JavascriptExecutor js;

    @FindBy(xpath = "//*[text()='Forms']")
    WebElement forms;

    @FindBy(xpath = "//*[text()='Practice Form']")
    WebElement practiceForm;

    @FindBy(xpath = "//*[@id='firstName']")
    WebElement firstName;

    @FindBy(xpath = "//*[@id='lastName']")
    WebElement lastName;

    @FindBy(xpath = "//*[@id='userEmail']")
    WebElement userEmail;

    @FindBy(xpath = "//*[@id='userNumber']")
    WebElement userNumber;

    @FindBy(id = "dateOfBirthInput")
    WebElement dateOfBirthInput;

    @FindBy(className = "react-datepicker__month-select")
    WebElement monthSelect;

    @FindBy(className = "react-datepicker__year-select")
    WebElement yearSelect;
/*
    @FindBy(xpath = "//*[@id='hobbies-checkbox-1']")
    WebElement hobby;
*/
    @FindBy(xpath = "//*[@id='submit']")
    WebElement buttonSubmit;

    @FindBy(xpath = "//*[@id='subjectsInput']")
    WebElement subjectsInput;

    @FindBy(xpath = "//*[@id='state']")
    WebElement state;

    @FindBy(xpath = "//*[@id='city']")
    WebElement city;


    public FormPom(WebDriver driverParam) {
        driver = driverParam;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public String getTableDataByLabel(String labelParam){
        WebElement data = driver.findElement(By.xpath("//table//*[text()='" + labelParam + "']/../*[2]"));
        return data.getText();
    }

    @Step("Set City")
    public void setCity(String cityParam){
        takeScreenshot("Before City");
        city.click();
        WebElement ddCity = city.findElement(By.xpath("//*[text()='" + cityParam + "']"));
        ddCity.click();
        takeScreenshot("After City");
    }

    @Step("Set State")
    public void setState(String stateParam){
        takeScreenshot("Before State");
        state.click();
        WebElement ddState = state.findElement(By.xpath("//*[text()='" + stateParam + "']"));
        ddState.click();
        takeScreenshot("After State");
    }
/*
    public void setHobbies(String hobbiesParam){
        WebElement hobby = driver.findElement(By.xpath("//*[@id='hobbiesWrapper']//label[text()=" + hobbiesParam + "'Sports']/../input"));
        hobby.sendKeys(" ");
    }
*/
    @Step("Set Subject")
    public void setSubject(String subjectParam){
        takeScreenshot("Before Subject");
        subjectsInput.sendKeys(subjectParam);
        subjectsInput.sendKeys(Keys.ENTER);
        takeScreenshot("After Subject");
    }

    public void clickButtonSubmit() {buttonSubmit.click();}

    @Step("Set Date of Birth")
    public void setDateOfBirth(String day, String month, String year) {
        takeScreenshot("Before Date of Birth");
        dateOfBirthInput.click();

        org.openqa.selenium.support.ui.Select yearDropdown =
                new org.openqa.selenium.support.ui.Select(yearSelect);
        yearDropdown.selectByVisibleText(year);

        org.openqa.selenium.support.ui.Select monthDropdown =
                new org.openqa.selenium.support.ui.Select(monthSelect);
        monthDropdown.selectByVisibleText(month);

        WebElement dayElement = driver.findElement(By.xpath(
                "//div[contains(@class,'react-datepicker__day') and text()='" + day + "']"
        ));
        dayElement.click();
        takeScreenshot("After Date of Birth");
    }

    @Step("Set User Number")
    public void setUserNumber(String numberParam){
        takeScreenshot("Before User Number");
        userNumber.clear();
        userNumber.sendKeys(numberParam);
        takeScreenshot("After User Number");
    }

    @Step("Set Gender")
    public void setGender(String genderParam) {
        takeScreenshot("Before Gender");
        WebElement gender = driver.findElement(By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']"));
        gender.click();
        takeScreenshot("After Gender");
    }

    @Step("Set Email")
    public void setEmail(String emailParam) {
        takeScreenshot("Before Email");
        userEmail.clear();
        userEmail.sendKeys(emailParam);
        takeScreenshot("After Email");
    }

    @Step("Set Last Name")
    public void setLastName(String lastNameParam) {
        takeScreenshot("Before Last Name");
        lastName.clear();
        lastName.sendKeys(lastNameParam);
        takeScreenshot("After Last Name");
    }

    @Step("Set First Name")
    public void setFirstName(String firstNameParam) {
        takeScreenshot("Before First Name");
        firstName.clear();
        firstName.sendKeys(firstNameParam);
        takeScreenshot("After First Name");
    }

    public void clickPracticeForm() {
        /*Utils.exceptionWait(driver, ExpectedConditions.visibilityOf(practiceForm), 10);*/
        practiceForm.click();
    }
    public void clickForms() {
        forms.click();
    }

    public void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

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

    private void takeScreenshot(String stepName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(stepName, "image/png", new ByteArrayInputStream(screenshot), ".png");
        } catch (Exception e) {
            Allure.addAttachment("Screenshot Error", e.toString());
        }
    }

}
