package com.andremoresco.asynctask.exceptions;

public class BackupFailureException extends Exception {

    private final String code;
    private final String message;

    public BackupFailureException() {
        super("Backup Fail.");
        this.code = "ERROR_BACKUP_PROCESS";
        this.message = "Error on backup process.";
    }

    public BackupFailureException(String message) {
        super("Backup Fail.");
        this.code = "ERROR_BACKUP_PROCESS";
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
