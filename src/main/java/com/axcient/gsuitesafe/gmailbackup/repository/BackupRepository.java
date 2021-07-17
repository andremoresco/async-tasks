package com.axcient.gsuitesafe.gmailbackup.repository;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.model.BackupStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BackupRepository {

    void save(Backup backup);

    void updateStatus(String id, BackupStatus backupStatus);

    List<Backup> find();

    Optional<Backup> findById(String id);

}
