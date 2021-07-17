package com.axcient.gsuitesafe.gmailbackup.repository.implementation;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.model.BackupStatus;
import com.axcient.gsuitesafe.gmailbackup.repository.BackupRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BackupStorageRepository implements BackupRepository {

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

    @Override
    public Optional<Backup> findById(String id) {
        return this.BACKUP_LIST.stream()
                .filter(backup -> backup.getBackupId().toString().equals(id))
                .findFirst();
    }

}
