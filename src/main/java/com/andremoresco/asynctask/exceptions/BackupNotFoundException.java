package com.andremoresco.asynctask.exceptions;

public class BackupNotFoundException extends Exception {

    private String code;
    private String message;

    public BackupNotFoundException() {
        super("Backup not found!");
        this.code = "BACKUP_NOT_FOUND";
        this.message = "Backup not found!";
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
