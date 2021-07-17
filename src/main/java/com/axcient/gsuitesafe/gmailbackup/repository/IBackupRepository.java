package com.axcient.gsuitesafe.gmailbackup.repository;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.model.BackupStatus;

import java.util.List;

public interface IBackupRepository {

    void save(Backup backup);
    void updateStatus(String id, BackupStatus backupStatus);
    List<Backup> find();

}
