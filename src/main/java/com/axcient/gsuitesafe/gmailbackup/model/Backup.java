package com.axcient.gsuitesafe.gmailbackup.model;

import java.util.Date;
import java.util.UUID;

public class Backup {

    private UUID backupId;
    private Date date;
    private BackupStatus status;

    public Backup() {
    }

    public Backup(UUID backupId, Date date, BackupStatus status) {
        this.backupId = backupId;
        this.date = date;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Backup{" +
                "backupId=" + backupId +
                ", date=" + date +
                ", status=" + status +
                '}';
    }

    public UUID getBackupId() {
        return backupId;
    }

    public void setBackupId(UUID backupId) {
        this.backupId = backupId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BackupStatus getStatus() {
        return status;
    }

    public void setStatus(BackupStatus status) {
        this.status = status;
    }
}
