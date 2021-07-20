package com.andremoresco.asynctask.usecase.listbackups;

import com.andremoresco.asynctask.model.Backup;
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
    public ResponseEntity<Object> handle() throws Exception {
        List<Backup> backups = this.listBackupsService.execute();
        return ResponseEntity.ok(backups);
    }

}
