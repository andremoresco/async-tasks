package com.axcient.gsuitesafe.gmailbackup.useCase.createBackup;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.model.BackupStatus;
import com.axcient.gsuitesafe.gmailbackup.repository.IBackupRepository;
import com.axcient.gsuitesafe.gmailbackup.useCase.doBackup.DoBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class CreateBackupService {

    private final IBackupRepository backupRepository;

    private final DoBackupService doBackupService;

    @Autowired
    public CreateBackupService(IBackupRepository backupRepository, DoBackupService doBackupService) {
        this.backupRepository = backupRepository;
        this.doBackupService = doBackupService;
    }

    public Backup execute() {
        UUID backupId = UUID.randomUUID();
        Backup backup = new Backup(backupId, new Date(), BackupStatus.IN_PROGRESS);
        this.backupRepository.save(backup);

        try {
            CompletableFuture<String> execute = this.doBackupService.execute(backupId.toString());
            execute.whenCompleteAsync((s, throwable) -> this.backupRepository.updateStatus(s, throwable == null ? BackupStatus.OK : BackupStatus.FAILED));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return backup;
    }

}
