package com.axcient.gsuitesafe.gmailbackup.repository;

import com.axcient.gsuitesafe.gmailbackup.model.EmailData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository {

    void save(String backupId, List<EmailData> listEmails) throws Exception;

    List<EmailData> list(String backupId);

}
