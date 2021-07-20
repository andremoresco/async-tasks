package com.andremoresco.asynctask.repository;

import com.andremoresco.asynctask.model.EmailData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository {

    void save(String backupId, List<EmailData> listEmails) throws Exception;

    List<EmailData> list(String backupId);

}
