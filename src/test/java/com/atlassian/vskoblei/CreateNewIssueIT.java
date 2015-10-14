package com.atlassian.vskoblei;

import com.atlassian.vskoblei.model.Issue;
import com.atlassian.vskoblei.model.Issue.Field;
import com.atlassian.vskoblei.utils.Utilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.fail;

public class CreateNewIssueIT {

    private static final String USERNAME = "vasskob";
    private static final String PASSWORD = "17801780";

    public static WebDriver driver;

    @Before
    public void before() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        final String URL = "localhost:8080";
        driver.get(URL);
    }

    @After
    public void after() {
        driver.close();
        driver.quit();
    }

    @Test
    public void createIssueTest() throws InterruptedException {
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        CreateIssuePage createIssuePage = new CreateIssuePage(driver);
        SearchIssuePage pageSearchIssue = new SearchIssuePage(driver);
        UpdateIssuePage pageUpdateIssue = new UpdateIssuePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        Issue issue = new Issue("FeedHenry", "Test fails with AssertionError", "Bug", "Major", "Run test");
        loginPage.login(USERNAME, PASSWORD);
        // Create Issue
        dashBoardPage.createBtnClick();
        //Enter issue information
        createIssuePage.createIssue(issue);
        Thread.sleep(3000); // sleep couple seconds
        // Search for issue and check if issue was created
        dashBoardPage.searchForIssueClick();
        pageSearchIssue.enterSeachQuery(issue.summary);
        //Check if issue is created
        if (!Utilities.isIssueCreated(driver, issue.summary)) {
            fail("Issue was not created. ");
        }
    }

    @Test
    public void updateIssueTest() throws InterruptedException {
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        CreateIssuePage createIssuePage = new CreateIssuePage(driver);
        SearchIssuePage pageSearchIssue = new SearchIssuePage(driver);
        UpdateIssuePage pageUpdateIssue = new UpdateIssuePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        Issue issue = new Issue("FeedHenry", "Test fails with InterruptionError", "Bug", "Major", "Run test");
        loginPage.login(USERNAME, PASSWORD);
        // Create Issue
        dashBoardPage.createBtnClick();
        createIssuePage.createIssue(issue);
        Thread.sleep(3000); // sleep couple seconds
        // Search for issue and check if issue was created
        dashBoardPage.searchForIssueClick();
        pageSearchIssue.enterSeachQuery(issue.summary);
        Utilities.selectIssue(driver, issue.summary);
        // Select Edit button to begin modify issue
        pageSearchIssue.updateBtnClick();
        // Modify description
        issue.description = "Run test again";
        //Edit description
        pageUpdateIssue.updateIssue(issue);
        // Wait for modifying issue process to finish
        Thread.sleep(3000);
        // Click Edit button to check description
        pageSearchIssue.updateBtnClick();
        // Check if description is actually changed
        if (!pageUpdateIssue.isDataUpdated(Field.DESCRIPTION, issue.description)) {
            fail("Data are not updated");
        }
    }
}
