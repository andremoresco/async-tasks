package com.axcient.gsuitesafe.gmailbackup.repository;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.model.BackupStatus;

public interface IBackupRepository {

    void save(Backup backup);
    void updateStatus(String id, BackupStatus backupStatus);

}
