package com.andremoresco.asynctask.usecase.findbackup;

import com.andremoresco.asynctask.model.Backup;
import com.andremoresco.asynctask.exceptions.BackupNotFoundException;
import com.andremoresco.asynctask.repository.BackupRepository;
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
