package com.axcient.gsuitesafe.gmailbackup.repository;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.model.BackupStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackupRepository implements IBackupRepository {

    private List<Backup> BACKUP_LIST = new ArrayList<>();

    @Override
    public void save(Backup backup) {
        this.BACKUP_LIST.add(backup);
    }

    @Override
    public void updateStatus(String id, BackupStatus backupStatus) {
        this.BACKUP_LIST.stream()
                .filter(backup -> backup.getBackupId().toString().equals(id))
                .findFirst()
                .ifPresent(backup -> backup.setStatus(backupStatus));
    }

    @Override
    public List<Backup> find() {
        return this.BACKUP_LIST;
    }

}
