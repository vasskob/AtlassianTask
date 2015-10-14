package com.atlassian.vskoblei;

import com.atlassian.vskoblei.model.Issue;
import com.atlassian.vskoblei.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UpdateIssuePage extends CreateIssuePage {

    @FindBy(id = "edit-issue-submit")
    WebElement updateIssueBtn;

    public UpdateIssuePage(WebDriver driver) {
        super(driver);
    }

    //Select an issue Type
    public UpdateIssuePage withIssueType(String issueType) {
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
    public UpdateIssuePage withPriority(String priority) {
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

    public UpdateIssuePage withSummary(String summary) {
        Utilities.WaitForElementToBeClickable(driver, this.summary);
        if (!this.summary.getText().equals(summary)) {
            this.summary.clear();
            this.summary.sendKeys(summary);
        }
        return this;
    }

    public UpdateIssuePage withDescription(String description) {
        Utilities.WaitForElementToBeClickable(driver, this.description);
        if (!this.description.getText().equals(description)) {
            this.description.clear();
            this.description.sendKeys(description);
        }
        return this;
    }

    public SearchIssuePage updateIssue(Issue issue) {
        withSummary(issue.summary);
        withIssueType(issue.issueType);
        withPriority(issue.priority);
        withDescription(issue.description);
        return updateBtnClick();
    }

    public boolean isDataUpdated(Issue.Field field, String checkString) {
        boolean result = false;
        switch (field) {
            case SUMMARY:
                Utilities.WaitForElementToBeClickable(driver, summary);
                result = summary.getText().equals(checkString);
                break;
            case ISSUE_TYPE:
                Utilities.WaitForElementToBeClickable(driver, issueTypeField);
                result = issueTypeField.getText().equals(checkString);
                break;
            case PRIORITY:
                Utilities.WaitForElementToBeClickable(driver, priority);
                result = priority.getText().equals(checkString);
                break;
            case DESCRIPTION:
                Utilities.WaitForElementToBeClickable(driver, description);
                result = description.getText().equals(checkString);
                break;
            default:
                result = false;
        }
        return result;
    }

    private SearchIssuePage updateBtnClick() {
        Utilities.WaitForElementToBeClickable(driver, updateIssueBtn);
        updateIssueBtn.click();
        return new SearchIssuePage(driver);
    }
}
