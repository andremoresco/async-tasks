package com.axcient.gsuitesafe.gmailbackup.useCase.listBackups;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.repository.IBackupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListBackupsService {

    private final IBackupRepository backupRepository;

    @Autowired
    public ListBackupsService(IBackupRepository backupRepository) {
        this.backupRepository = backupRepository;
    }

    public List<Backup> execute() {
        return this.backupRepository.find();
    }
}
