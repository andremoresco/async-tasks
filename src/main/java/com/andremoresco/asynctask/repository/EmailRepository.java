package com.andremoresco.asynctask.repository;

import com.andremoresco.asynctask.model.Email;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository {

    void save(String backupId, List<Email> listEmails) throws Exception;

    List<Email> list(String backupId);

}
