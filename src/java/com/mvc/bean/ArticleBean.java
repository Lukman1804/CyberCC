package com.mvc.bean;

import java.sql.Date;

public class ArticleBean {
    private int articleID;
    private String articleTitle;
    private String articleDescription;
    private Date articleDate;
    private String articleApproval;
    private int clientID;
    private int adminID;
    private String clientName;
    private String adminName;

    // Getters and Setters
    public int getArticleID() { return articleID; }
    public void setArticleID(int articleID) { this.articleID = articleID; }
    
    public String getArticleTitle() { return articleTitle; }
    public void setArticleTitle(String articleTitle) { this.articleTitle = articleTitle; }

    public String getArticleDescription() { return articleDescription; }
    public void setArticleDescription(String articleDescription) { this.articleDescription = articleDescription; }

    public Date getArticleDate() { return articleDate; }
    public void setArticleDate(Date articleDate) { this.articleDate = articleDate; }

    public String getArticleApproval() { return articleApproval; }
    public void setArticleApproval(String articleApproval) { this.articleApproval = articleApproval; }

    public int getClientID() { return clientID; }
    public void setClientID(int clientID) { this.clientID = clientID; }

    public int getAdminID() { return adminID; }
    public void setAdminID(int adminID) { this.adminID = adminID; }
    
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getAdminName() { return adminName; }
    public void setAdminName(String adminName) { this.adminName = adminName; }
}