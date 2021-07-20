package com.andremoresco.asynctask.usecase.exportbackup;

import com.andremoresco.asynctask.model.BackupStatus;
import com.andremoresco.asynctask.model.EmailData;
import com.andremoresco.asynctask.usecase.findbackup.FindBackupByIdService;
import com.andremoresco.asynctask.exceptions.BackupNotReadyToExportException;
import com.andremoresco.asynctask.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ExportBackupService {

    private final FindBackupByIdService findBackupByIdService;
    private final EmailRepository emailRepository;

    @Autowired
    public ExportBackupService(FindBackupByIdService findBackupByIdService, EmailRepository emailRepository) {
        this.findBackupByIdService = findBackupByIdService;
        this.emailRepository = emailRepository;
    }

    public List<EmailData> execute(String backupId) throws Exception {
        var backup = this.findBackupByIdService.execute(backupId);

        if (backup.getStatus() != BackupStatus.OK) {
            throw new BackupNotReadyToExportException();
        }

        return this.emailRepository.list(backupId);
    }

    public List<EmailData> execute(String backupId, String label) throws Exception {
        var emailList = this.execute(backupId);
        return emailList.stream()
                .filter(emailData -> Objects.nonNull(emailData.getLabels()) && emailData.getLabels().contains(label))
                .collect(Collectors.toList());
    }

}
