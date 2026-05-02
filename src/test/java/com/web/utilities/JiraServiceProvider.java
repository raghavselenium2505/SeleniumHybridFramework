/*
 * // JiraServiceProvider.java
 * 
 * package com.web.utilities;
 * 
 * import com.gps.base.TestBase;
 * 
 * import net.rcarz.jiraclient.BasicCredentials; import
 * net.rcarz.jiraclient.Field; import net.rcarz.jiraclient.Issue; import
 * net.rcarz.jiraclient.JiraClient; import net.rcarz.jiraclient.JiraException;
 * 
 * public class JiraServiceProvider extends TestBase {
 * 
 * private JiraClient jira; private String project; private String jiraUrl;
 * 
 * public static String userName; public static String passWord;
 * 
 * public JiraServiceProvider() {
 * 
 * try {
 * 
 * if ("Y".equalsIgnoreCase( JiraProp.getProperty("jiraEnabled"))) {
 * 
 * jiraUrl = JiraProp.getProperty("jiraUrl");
 * 
 * userName = JiraProp.getProperty("userName");
 * 
 * passWord = JiraProp.getProperty("passWord");
 * 
 * BasicCredentials creds = new BasicCredentials( userName, passWord);
 * 
 * jira = new JiraClient( jiraUrl, creds);
 * 
 * project = JiraProp.getProperty( "projectCode");
 * 
 * logger.info( "Connected Jira Project : " + project);
 * 
 * } else {
 * 
 * logger.info( "Jira Integration Disabled"); }
 * 
 * } catch (Exception e) {
 * 
 * logger.error( "Jira Initialization Failed", e); } }
 * 
 * public void createJiraIssue( String issueType, String summary, String
 * description, String reporterName) throws JiraException {
 * 
 * if (!"Y".equalsIgnoreCase( JiraProp.getProperty( "jiraEnabled"))) {
 * 
 * logger.info("Jira Disabled"); return; }
 * 
 * try {
 * 
 * Issue.SearchResult sr = jira.searchIssues( "project=" + project +
 * " AND summary ~ \"" + summary + "\"");
 * 
 * if (sr.total != 0) {
 * 
 * logger.info( "Duplicate bug already exists"); return; }
 * 
 * Issue newIssue = jira.createIssue( project, issueType) .field( Field.SUMMARY,
 * summary) .field( Field.DESCRIPTION, description) .execute();
 * 
 * logger.info( "Bug Created : " + newIssue.getKey());
 * 
 * logger.info( "Bug URL : " + jiraUrl + "/browse/" + newIssue.getKey());
 * 
 * } catch (Exception e) {
 * 
 * logger.error( "Jira Creation Failed", e); } } }
 */