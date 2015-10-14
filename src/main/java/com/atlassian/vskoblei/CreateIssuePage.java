package com.atlassian.vskoblei;

import com.atlassian.vskoblei.model.Issue;
import com.atlassian.vskoblei.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateIssuePage {

    @FindBy(id = "summary")
    protected WebElement summary;
    @FindBy(id = "issuetype-field")
    protected WebElement issueTypeField;
    @FindBy(id = "priority-field")
    protected WebElement priority;
    @FindBy(id = "description")
    protected WebElement description;
    @FindBy(id = "create-issue-submit")
    protected WebElement createBtn;

    protected WebDriver driver;

    //Init Elements on Page
    public CreateIssuePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Select an issue Type
    public CreateIssuePage withIssueType(String issueType) {
        Utilities.WaitForElementToBeClickable(driver, issueTypeField);
        this.issueTypeField.click();
        WebElement issueTypeSuggestions = driver.findElement(By.id("issuetype-suggestions"));
        List<WebElement> issueTypeList = issueTypeSuggestions.findElements(By.tagName("a"));
        for (WebElement issueTypeItem : issueTypeList) {
            if (issueTypeItem.getAttribute("title").equalsIgnoreCase(issueType)) {
                issueTypeItem.click();
                break;
            }
        }
        return this;
    }

    //Select priority
    public CreateIssuePage withPriority(String priority) {
        Utilities.WaitForElementToBeClickable(driver, this.priority);
        this.priority.click();
        WebElement prioritySuggestions = driver.findElement(By.id("priority-suggestions"));
        List<WebElement> issueTypeList = prioritySuggestions.findElements(By.tagName("a"));
        for (WebElement issueTypeItem : issueTypeList) {
            if (issueTypeItem.getAttribute("title").equalsIgnoreCase(priority)) {
                issueTypeItem.click();
                break;
            }
        }
        return this;
    }

    public CreateIssuePage withSummary(String summary) {
        Utilities.WaitForElementToBeClickable(driver, this.summary);
        if (!this.summary.getText().equals(summary)) {
            this.summary.clear();
            this.summary.sendKeys(summary);
        }
        return this;
    }

    public CreateIssuePage withDescription(String description) {
        Utilities.WaitForElementToBeClickable(driver, this.description);
        if (!this.description.getText().equals(description)) {
            this.description.clear();
            this.description.sendKeys(description);
        }
        return this;
    }

    //Click Create button
    public DashBoardPage createBtnClick() {
        createBtn.click();
        return PageFactory.initElements(driver, DashBoardPage.class);
    }

    public DashBoardPage createIssue(Issue issue) throws InterruptedException {
        withSummary(issue.summary);
        withIssueType(issue.issueType);
        withPriority(issue.priority);
        withDescription(issue.description);
        return createBtnClick();
    }
}
