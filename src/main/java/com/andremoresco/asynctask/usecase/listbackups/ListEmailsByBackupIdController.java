package com.andremoresco.asynctask.usecase.listbackups;

import com.andremoresco.asynctask.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListEmailsByBackupIdController {

    @Autowired
    private EmailRepository emailRepository;

    @GetMapping("/emails/{backup_id}")
    public ResponseEntity<Object> get(@PathVariable("backup_id") String backupId) {
        return ResponseEntity.ok(this.emailRepository.list(backupId));
    }

}
