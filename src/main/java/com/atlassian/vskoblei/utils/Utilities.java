package com.atlassian.vskoblei.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Utilities {
    public static WebElement WaitForElementToBeClickable(WebDriver driver, WebElement we) {
        WebDriverWait wait = new WebDriverWait(driver, 300, 500);
        return wait.until(ExpectedConditions.elementToBeClickable(we));
    }

    public static WebElement WaitForElementToDisplay(WebDriver driver, WebElement we) {
        WebDriverWait wait = new WebDriverWait(driver, 300, 500);
        return wait.until(ExpectedConditions.visibilityOf(we));
    }

    //Check if issue is created
    public static boolean isIssueCreated(WebDriver driver, String searchQuery) {
        boolean isCreated = false;
        WebElement issueList = Utilities.WaitForElementToBeClickable(driver, driver.findElement(By.className("simple-issue-list")));
        List<WebElement> issuesList = issueList.findElements(By.tagName("li"));
        for (WebElement issue : issuesList) {
            if (issue.getAttribute("title").equalsIgnoreCase(searchQuery)) {
                isCreated = true;
                break;
            }
        }
        return isCreated;
    }

    //Select the issue
    public static void selectIssue(WebDriver driver, String searchQuery) {

        WebElement issueList = Utilities.WaitForElementToDisplay(driver, driver.findElement(By.className("issue-list")));
        List<WebElement> issuesList = issueList.findElements(By.tagName("li"));

        for (int i = 0; i < issuesList.size(); i++) {
            if (issuesList.get(i).getAttribute("title").equalsIgnoreCase(searchQuery)) {
                WebElement issue = issueList.findElement(By.xpath("//ol[@class='issue-list']/li[" + (i + 1) + "]/a"));
                issue.click();
            }
        }
    }
}
