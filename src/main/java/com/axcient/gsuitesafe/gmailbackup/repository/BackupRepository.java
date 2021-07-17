package com.axcient.gsuitesafe.gmailbackup.repository;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.model.BackupStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackupRepository implements IBackupRepository {

    private List<Backup> backupsList = new ArrayList<>();

    @Override
    public void save(Backup backup) {
        this.backupsList.add(backup);
        this.backupsList.forEach(System.out::println);
    }

    @Override
    public void updateStatus(String id, BackupStatus backupStatus) {
        System.out.println("Updating status to: " + backupStatus.toString());
        this.backupsList.stream()
                .filter(backup -> backup.getBackupId().toString().equals(id))
                .findFirst()
                .ifPresent(backup -> backup.setStatus(backupStatus));
    }

}
