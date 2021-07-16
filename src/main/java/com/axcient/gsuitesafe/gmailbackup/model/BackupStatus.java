package com.axcient.gsuitesafe.gmailbackup.model;

public enum BackupStatus {
    IN_PROGRESS("In Progress"),
    OK("Ok"),
    FAILED("Failed");

    private String description;

    BackupStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
