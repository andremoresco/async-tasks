package com.axcient.gsuitesafe.gmailbackup.useCase.createBackup;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.model.BackupStatus;
import com.axcient.gsuitesafe.gmailbackup.repository.BackupRepository;
import com.axcient.gsuitesafe.gmailbackup.useCase.doBackup.DoBackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateBackupService {

    private final BackupRepository backupRepository;

    private final DoBackupService doBackupService;

    @Autowired
    public CreateBackupService(BackupRepository backupRepository, DoBackupService doBackupService) {
        this.backupRepository = backupRepository;
        this.doBackupService = doBackupService;
    }

    public Backup execute() throws Exception {
        Backup backup = new Backup();
        this.backupRepository.save(backup);

        doBackup(backup);

        return backup;
    }

    private void doBackup(Backup backup) {
        this.doBackupService.execute(backup.getBackupId().toString())
                .whenComplete((s, throwable) -> this.backupRepository.updateStatus(s, throwable == null ? BackupStatus.OK : BackupStatus.FAILED));
    }

}
