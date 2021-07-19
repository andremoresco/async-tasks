package com.axcient.gsuitesafe.gmailbackup.usecase.findbackup;

import com.axcient.gsuitesafe.gmailbackup.exceptions.BackupNotFoundException;
import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.repository.BackupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindBackupByIdService {

    private final BackupRepository backupRepository;

    @Autowired
    public FindBackupByIdService(BackupRepository backupRepository) {
        this.backupRepository = backupRepository;
    }

    public Backup execute(String id) throws BackupNotFoundException {
        return this.backupRepository.findById(id)
                .orElseThrow(BackupNotFoundException::new);
    }
}
