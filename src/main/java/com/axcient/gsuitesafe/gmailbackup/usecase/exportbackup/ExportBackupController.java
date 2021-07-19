package com.axcient.gsuitesafe.gmailbackup.usecase.exportbackup;

import com.axcient.gsuitesafe.gmailbackup.model.EmailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

@RestController
public class ExportBackupController {

    private final ExportBackupService exportBackupService;

    @Autowired
    public ExportBackupController(ExportBackupService exportBackupService) {
        this.exportBackupService = exportBackupService;
    }

    @GetMapping(value = "/exports/{backup_id}", produces = "application/zip")
    public ResponseEntity<StreamingResponseBody> handle(@PathVariable("backup_id") String backupId) throws Exception {
        List<EmailData> list = this.exportBackupService.execute(backupId);

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=\"Backup-" + backupId + ".zip\"")
                .body(out -> CreateBackupZipOutputStream.execute(out, list));
    }

    @GetMapping(value = "/exports/{backup_id}/{label}", produces = "application/zip")
    public ResponseEntity<StreamingResponseBody> handleFiltering(@PathVariable("backup_id") String backupId,
                                                                 @PathVariable("label") String label) throws Exception {
        List<EmailData> list = this.exportBackupService.execute(backupId, label);

        if (list == null || list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=\"Backup-" + backupId + ".zip\"")
                .body(out -> CreateBackupZipOutputStream.execute(out, list));
    }

}
