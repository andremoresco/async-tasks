package com.axcient.gsuitesafe.gmailbackup.usecase.createbackup;

public class RequestBackupDTO {

    private String backupId;

    public RequestBackupDTO(String backupId) {
        this.backupId = backupId;
    }

    public String getBackupId() {
        return backupId;
    }

}
