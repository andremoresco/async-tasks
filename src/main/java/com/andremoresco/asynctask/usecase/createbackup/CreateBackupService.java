package com.andremoresco.asynctask.usecase.createbackup;

import com.andremoresco.asynctask.model.Backup;
import com.andremoresco.asynctask.model.BackupStatus;
import com.andremoresco.asynctask.usecase.dobackup.DoBackupService;
import com.andremoresco.asynctask.repository.BackupRepository;
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

    private void doBackup(Backup backup) throws Exception {
        this.doBackupService.execute(backup.getBackupId().toString())
                .whenCompleteAsync((s, throwable) -> this.backupRepository.updateStatus(backup.getBackupId().toString(), throwable == null ? BackupStatus.OK : BackupStatus.FAILED));
    }

}
