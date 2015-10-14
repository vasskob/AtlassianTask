package com.atlassian.vskoblei.model;

public class Issue {

    public String project;
    public String summary;
    public String issueType;
    public String priority;
    public String description;
    public Issue(String project, String summary, String issueType, String priority, String description) {
        this.project = project;
        this.summary = summary;
        this.issueType = issueType;
        this.priority = priority;
        this.description = description;
    }

    public enum Field {
        PROJECT("project"),
        SUMMARY("summary"),
        ISSUE_TYPE("issuetype"),
        PRIORITY("priority"),
        DESCRIPTION("description");

        String field;

        Field(String field) {
            this.field = field;
        }
    }
}
