package com.axcient.gsuitesafe.gmailbackup.usecase.dobackup;

import com.axcient.gsuitesafe.gmailbackup.model.EmailData;
import com.axcient.gsuitesafe.gmailbackup.providers.EmailProvider;
import com.axcient.gsuitesafe.gmailbackup.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DoBackupService {

    private final EmailProvider emailProvider;
    private final EmailRepository emailRepository;

    @Autowired
    public DoBackupService(EmailProvider emailProvider, EmailRepository emailRepository) {
        this.emailProvider = emailProvider;
        this.emailRepository = emailRepository;
    }

    @Async
    public CompletableFuture<String> execute(String backupId) {
        try {
            List<EmailData> emails = this.emailProvider.getEmails();

            this.emailRepository.save(backupId, emails);

            return CompletableFuture.completedFuture(backupId);

        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);

        }
    }

}
