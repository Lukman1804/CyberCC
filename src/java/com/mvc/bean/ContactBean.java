package com.mvc.bean;

public class ContactBean {
    private int contactID;
    private String contactHeader;
    private String contactDescription;
    private String contactExtra;

    // Getters and Setters
    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getContactHeader() {
        return contactHeader;
    }

    public void setContactHeader(String contactHeader) {
        this.contactHeader = contactHeader;
    }

    public String getContactDescription() {
        return contactDescription;
    }

    public void setContactDescription(String contactDescription) {
        this.contactDescription = contactDescription;
    }

    public String getContactExtra() {
        return contactExtra;
    }

    public void setContactExtra(String contactExtra) {
        this.contactExtra = contactExtra;
    }
}