package com.axcient.gsuitesafe.gmailbackup.repository.implementation;

import com.axcient.gsuitesafe.gmailbackup.model.EmailData;
import com.axcient.gsuitesafe.gmailbackup.repository.EmailRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmailBackupStorageRepository implements EmailRepository {

    private Map<String, List<EmailData>> BACKUPS = new HashMap<>();

    @Override
    public void save(String backupId, List<EmailData> listEmails) {
        this.BACKUPS.put(backupId, listEmails);
    }

    @Override
    public List<EmailData> list(String backupId) {
        return this.BACKUPS.get(backupId);
    }
}
