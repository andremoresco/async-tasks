package com.axcient.gsuitesafe.gmailbackup.useCase.doBackup;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DoBackupService {

    @Async
    public CompletableFuture<String> execute(String backupId) {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(backupId);
    }

}
