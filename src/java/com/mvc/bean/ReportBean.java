package com.mvc.bean;

import java.sql.Date;

public class ReportBean {
    private int reportID;
    private String reportTitle;
    private String reportDescription;
    private Date reportDate;
    private String reportApproval;
    private int clientID;
    private int adminID;
    private String clientName;
    private String adminName;

    // Getters & Setters
    public int getReportID() { return reportID; }
    public void setReportID(int reportID) { this.reportID = reportID; }

    public String getReportTitle() { return reportTitle; }
    public void setReportTitle(String reportTitle) { this.reportTitle = reportTitle; }

    public String getReportDescription() { return reportDescription; }
    public void setReportDescription(String reportDescription) { this.reportDescription = reportDescription; }

    public Date getReportDate() { return reportDate; }
    public void setReportDate(Date reportDate) { this.reportDate = reportDate; }

    public String getReportApproval() { return reportApproval; }
    public void setReportApproval(String reportApproval) { this.reportApproval = reportApproval; }

    public int getClientID() { return clientID; }
    public void setClientID(int clientID) { this.clientID = clientID; }

    public int getAdminID() { return adminID; }
    public void setAdminID(int adminID) { this.adminID = adminID; }
    
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }

    public String getAdminName() { return adminName; }
    public void setAdminName(String adminName) { this.adminName = adminName; }
}
