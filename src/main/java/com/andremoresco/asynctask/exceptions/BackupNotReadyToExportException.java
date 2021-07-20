package com.andremoresco.asynctask.exceptions;

public class BackupNotReadyToExportException extends Exception {

    private final String code;
    private final String message;

    public BackupNotReadyToExportException() {
        super("Backup not ready to export. Check the status before!");
        this.code = "BACKUP_NOT_READY_TO_EXPORT";
        this.message = "Backup not ready to export!";
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
