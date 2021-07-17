package com.axcient.gsuitesafe.gmailbackup.useCase.createBackup;

public class RequestBackupDTO {

    private String backupId;

    public RequestBackupDTO() {
    }

    public RequestBackupDTO(String backupId) {
        this.backupId = backupId;
    }

    public String getBackupId() {
        return backupId;
    }

    public void setBackupId(String backupId) {
        this.backupId = backupId;
    }
}
