package com.axcient.gsuitesafe.gmailbackup.useCase.createBackup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateBackupController {

    private final CreateBackupService createBackupService;

    @Autowired
    public CreateBackupController(CreateBackupService createBackupService) {
        this.createBackupService = createBackupService;
    }

    @PostMapping("/backups")
    public ResponseEntity<Object> handle() {
        return ResponseEntity.ok(new RequestBackupDTO(this.createBackupService.execute().getBackupId().toString()));
    }

}
