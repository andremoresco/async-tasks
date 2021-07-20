package com.andremoresco.asynctask.usecase.listbackups;

import com.andremoresco.asynctask.model.Backup;
import com.andremoresco.asynctask.repository.BackupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListBackupsService {

    private final BackupRepository backupRepository;

    @Autowired
    public ListBackupsService(BackupRepository backupRepository) {
        this.backupRepository = backupRepository;
    }

    public List<Backup> execute() throws Exception {
        return this.backupRepository.find() ;
    }
}
