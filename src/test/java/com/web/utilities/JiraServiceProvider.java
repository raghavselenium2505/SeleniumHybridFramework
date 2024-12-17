// JiraServiceProvider.java

package com.web.utilities;
import com.base.web.TestBase;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.Issue.FluentCreate;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class JiraServiceProvider extends TestBase {
     private JiraClient Jira;
     private String project;
     private String JiraUrl;
     public static String userName;
     public static String passWord;
   
    
     
     
     public JiraServiceProvider() {
    	 if(JiraProp.getProperty("jiraEnabled").equals("Y"))
         {
        	 
        this. JiraUrl=JiraProp.getProperty("jiraUrl");
         userName=JiraProp.getProperty("userName");
         passWord=JiraProp.getProperty("passWord");
         BasicCredentials creds = new BasicCredentials(userName, passWord);
         Jira = new JiraClient(JiraUrl, creds);

         this.project = JiraProp.getProperty("projectCode");
     logger.info("ProjectName logged"+JiraProp.getProperty("projectCode"));
         }
    	 else 
    	 {
    		 logger.info("Jira Bug is not raised since jira Enabled is "+JiraProp.getProperty("jiraEnabled"));
    	 }
     }


    public void createJiraIssue(String issueType, String summary, String description, String reporterName) throws JiraException {


if(JiraProp.getProperty("jiraEnabled").equals("Y"))
{
	 

    FluentCreate fleuntCreate = Jira.createIssue(project, issueType);


        try {

            //Avoid Creating Duplicate Issue

          Issue.SearchResult sr = Jira.searchIssues("summary ~ \""+summary+"\"");

			
			  if(sr.total!=0) {
			  
//			  System.out.println("Same Issue Already Exists on Jira");
	logger.info("same issue already exists on jira");
				  fleuntCreate.field(Field.STATUS,"BACKLOG");
			  return;
			  }
			  
            //Create issue if not exists

			  //  FluentCreate fleuntCreate = Jira.createIssue(project, issueType);
            

           fleuntCreate.field(Field.SUMMARY, summary);
           logger.info("Summary"+summary);

            fleuntCreate.field(Field.DESCRIPTION, description);
            logger.info("Description"+description);

            Issue newIssue = fleuntCreate.execute();

         logger.info("New issue created in Jira with ID: " + newIssue);
         logger.info("New issue URL is :"+JiraUrl+"/browse/"+newIssue);
         

            System.out.println("*******************************************");

        }

    catch (JiraException e) {

            e.printStackTrace();

        }

    }
else
{
	logger.info("No jira is created");
}

}
  
}