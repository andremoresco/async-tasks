package com.axcient.gsuitesafe.gmailbackup.useCase.doBackup;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DoBackupService {

    @Async
    public CompletableFuture<String> execute(String backupId) throws InterruptedException {
        Thread.sleep(10000L);
        return CompletableFuture.completedFuture(backupId);
    }

}
