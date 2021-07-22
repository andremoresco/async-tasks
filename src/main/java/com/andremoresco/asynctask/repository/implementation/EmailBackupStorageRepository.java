package com.andremoresco.asynctask.repository.implementation;

import com.andremoresco.asynctask.model.Email;
import com.andremoresco.asynctask.repository.EmailRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class created to simulate a repository
 */
@Repository
public class EmailBackupStorageRepository implements EmailRepository {

    private Map<String, List<Email>> BACKUPS = new HashMap<>();

    @Override
    public void save(String backupId, List<Email> listEmails) {
        this.BACKUPS.put(backupId, listEmails);
    }

    @Override
    public List<Email> list(String backupId) {
        return this.BACKUPS.get(backupId);
    }
}
