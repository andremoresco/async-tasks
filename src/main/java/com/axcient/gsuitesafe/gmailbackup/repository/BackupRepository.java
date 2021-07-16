package com.axcient.gsuitesafe.gmailbackup.repository;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
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
}
