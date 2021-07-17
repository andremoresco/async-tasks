package com.axcient.gsuitesafe.gmailbackup.useCase.createBackup;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.model.BackupStatus;
import com.axcient.gsuitesafe.gmailbackup.repository.IBackupRepository;
import com.axcient.gsuitesafe.gmailbackup.useCase.doBackup.DoBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        Backup backup = new Backup(new Date(), BackupStatus.IN_PROGRESS);
        this.backupRepository.save(backup);

        this.doBackupService.execute(backup.getBackupId().toString())
                .whenComplete((s, throwable) -> this.backupRepository.updateStatus(s, throwable == null ? BackupStatus.OK : BackupStatus.FAILED));

        return backup;
    }

}
