package com.atlassian.vskoblei;

import com.atlassian.vskoblei.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "login-form-username")
    private WebElement loginFormUsername;
    @FindBy(id = "login-form-password")
    private WebElement loginFormPassword;
    @FindBy(id = "login-form-submit")
    private WebElement logInFormSubmit;
    @FindBy(linkText = "Log In")
    private WebElement logInBtn;

    private WebDriver driver;

    //init Login page
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterLoginInfo(String username, String password) {
        Utilities.WaitForElementToBeClickable(driver, loginFormUsername);
        loginFormUsername.sendKeys(username);

        Utilities.WaitForElementToBeClickable(driver, loginFormPassword);
        loginFormPassword.sendKeys(password);
    }

    //click Login
    public DashBoardPage loginBtnClick() {
        logInFormSubmit.click();
        return PageFactory.initElements(driver, DashBoardPage.class);
    }

    public DashBoardPage login(String username, String password) {
        //Wait until login button is displayed then click it
        logInBtn.click();
        //Enter account credential
        enterLoginInfo(username, password);
        //Log In
        return loginBtnClick();
    }
}
