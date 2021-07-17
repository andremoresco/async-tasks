package com.axcient.gsuitesafe.gmailbackup.useCase.doBackup;

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

        List<EmailData> emails = this.emailProvider.getEmails();

        this.emailRepository.save(backupId, emails);

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(backupId);
    }

}
