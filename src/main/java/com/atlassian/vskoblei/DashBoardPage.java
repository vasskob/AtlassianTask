package com.atlassian.vskoblei;

import com.atlassian.vskoblei.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DashBoardPage {

    protected WebDriver driver;
    @FindBy(id = "create_link")
    WebElement createBtn;
    @FindBy(id = "find_link")
    WebElement issueMenu;
    @FindBy(id = "issues_new_search_link_lnk")
    WebElement searchForIssue;
    @FindBy(id = "header-details-user-fullname")
    WebElement selectProfile;
    @FindBy(using = "log_out")
    WebElement btnLogout;


    public DashBoardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CreateIssuePage createBtnClick() {
        Utilities.WaitForElementToBeClickable(driver, createBtn);
        createBtn.click();
        return PageFactory.initElements(driver, CreateIssuePage.class);
    }

    public SearchIssuePage searchForIssueClick() {
        Utilities.WaitForElementToBeClickable(driver, this.issueMenu).click();
        Utilities.WaitForElementToBeClickable(driver, this.searchForIssue).click();
        return PageFactory.initElements(driver, SearchIssuePage.class);
    }

    public void logout() {
        Utilities.WaitForElementToBeClickable(driver, this.selectProfile).click();
        btnLogout.click();
    }
}
