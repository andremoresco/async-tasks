package com.axcient.gsuitesafe.gmailbackup.useCase.createBackup;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.model.BackupStatus;
import com.axcient.gsuitesafe.gmailbackup.repository.BackupRepository;
import com.axcient.gsuitesafe.gmailbackup.useCase.doBackup.DoBackupService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.CompletableFuture;

@RunWith(MockitoJUnitRunner.class)
public class CreateBackupServiceTest {

    @Mock
    private BackupRepository backupRepository;

    @Mock
    private DoBackupService doBackupService;

    @InjectMocks
    private CreateBackupService createBackupService;

    @Test
    public void testCreateBackupSuccess() throws Exception {
        Mockito.when(this.doBackupService.execute(Mockito.anyString()))
                .thenReturn(CompletableFuture.completedFuture(Mockito.anyString()));

        Backup backupCreated = createBackupService.execute();
        Assert.assertNotNull(backupCreated);
        Assert.assertNotNull(backupCreated.getBackupId());
    }

    @Test
    public void testCreateBackupReturnedStatusInProgress() throws Exception {
        Mockito.when(this.doBackupService.execute(Mockito.anyString()))
                .thenReturn(CompletableFuture.completedFuture(Mockito.anyString()));

        Backup backupCreated = createBackupService.execute();
        Assert.assertNotNull(backupCreated);
        Assert.assertEquals(BackupStatus.IN_PROGRESS, backupCreated.getStatus());
    }

}
