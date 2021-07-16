package com.axcient.gsuitesafe.gmailbackup.useCase;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.model.BackupStatus;
import com.axcient.gsuitesafe.gmailbackup.repository.IBackupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CreateBackupService {

    private final IBackupRepository IBackupRepository;

    @Autowired
    public CreateBackupService(IBackupRepository IBackupRepository) {
        this.IBackupRepository = IBackupRepository;
    }

    public Backup execute() {
        UUID backupId = UUID.randomUUID();
        Backup backup = new Backup(backupId, new Date(), BackupStatus.IN_PROGRESS);
        this.IBackupRepository.save(backup);
        return backup;
    }

}
