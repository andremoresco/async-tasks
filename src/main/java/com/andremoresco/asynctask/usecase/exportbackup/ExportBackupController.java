package com.andremoresco.asynctask.usecase.exportbackup;

import com.andremoresco.asynctask.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class ExportBackupController {

    private final ExportBackupService exportBackupService;

    @Autowired
    public ExportBackupController(ExportBackupService exportBackupService) {
        this.exportBackupService = exportBackupService;
    }

    @GetMapping(value = "/exports/{backup_id}", produces = "application/zip")
    public ResponseEntity<StreamingResponseBody> handle(@PathVariable("backup_id") String backupId) throws Exception {

        var emailList = this.exportBackupService.execute(backupId);

        return processResponse(backupId, emailList);
    }

    @GetMapping(value = "/exports/{backup_id}/{label}", produces = "application/zip")
    public ResponseEntity<StreamingResponseBody> handleFiltering(@PathVariable("backup_id") String backupId,
                                                                 @PathVariable("label") String label) throws Exception {
        var list = this.exportBackupService.execute(backupId, label);

        return processResponse(backupId, list);
    }

    private ResponseEntity<StreamingResponseBody> processResponse(String backupId, List<Email> list) {
        if (list == null || list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "attachment; filename=\"Backup-" + backupId + ".zip\"")
                .body(out -> {
                    var zipOutputStream = new ZipOutputStream(out);

                    for (var emailData : list) {
                        var emailBytes = emailData.toString().getBytes();
                        var entry = new ZipEntry(emailData.getId() + ".txt");
                        entry.setSize(emailBytes.length);
                        zipOutputStream.putNextEntry(entry);
                        zipOutputStream.write(emailBytes);
                        zipOutputStream.closeEntry();
                    }

                    zipOutputStream.close();
                });
    }

}
