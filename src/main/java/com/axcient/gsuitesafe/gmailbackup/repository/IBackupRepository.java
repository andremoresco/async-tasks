package com.axcient.gsuitesafe.gmailbackup.repository;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;

public interface IBackupRepository {

    void save(Backup backup);

}
