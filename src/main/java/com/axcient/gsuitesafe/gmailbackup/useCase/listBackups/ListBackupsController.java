package com.axcient.gsuitesafe.gmailbackup.useCase.listBackups;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ListBackupsController {

    private final ListBackupsService listBackupsService;

    @Autowired
    public ListBackupsController(ListBackupsService listBackupsService) {
        this.listBackupsService = listBackupsService;
    }

    @GetMapping("/backups")
    public ResponseEntity<Object> handle() {
        List<Backup> backups = this.listBackupsService.execute();
        return ResponseEntity.ok(backups);
    }

}
