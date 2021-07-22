package com.andremoresco.asynctask.usecase.dobackup;

import com.andremoresco.asynctask.providers.EmailProvider;
import com.andremoresco.asynctask.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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

            this.emailProvider.getEmails(listEmail -> this.emailRepository.save(backupId, listEmail));

            return CompletableFuture.completedFuture(backupId);

        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);

        }
    }

}
