package com.atlassian.vskoblei;

import com.atlassian.vskoblei.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchIssuePage extends DashBoardPage {
    @FindBy(id = "searcher-query")
    WebElement searchQuery;

    public SearchIssuePage(WebDriver driver) {
        super(driver);
    }

    //Click Edit Button
    public void updateBtnClick() {
        List<WebElement> editBtnList = driver.findElements(By.id("edit-issue"));
        for (WebElement editBtn : editBtnList) {
            if (editBtn.isDisplayed()) {
                editBtn.click();
                break;
            }
        }
    }

    //Enter Seacrh Query
    public void enterSeachQuery(String searchString) throws InterruptedException {
        Utilities.WaitForElementToBeClickable(driver, searchQuery);
        searchQuery.sendKeys(searchString);
        searchQuery.sendKeys(Keys.RETURN);
    }
}
