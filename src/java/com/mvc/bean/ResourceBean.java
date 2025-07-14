package com.mvc.bean;

public class ResourceBean {
    private int resourceID;
    private String resourceHeader;
    private String resourceDescription;
    private String resourceLink;

    // Getters and Setters
    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getResourceHeader() {
        return resourceHeader;
    }

    public void setResourceHeader(String resourceHeader) {
        this.resourceHeader = resourceHeader;
    }

    public String getResourceDescription() {
        return resourceDescription;
    }

    public void setResourceDescription(String resourceDescription) {
        this.resourceDescription = resourceDescription;
    }

    public String getResourceLink() {
        return resourceLink;
    }

    public void setResourceLink(String resourceLink) {
        this.resourceLink = resourceLink;
    }
}
