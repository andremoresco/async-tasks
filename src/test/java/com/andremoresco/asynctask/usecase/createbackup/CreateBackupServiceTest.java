package com.andremoresco.asynctask.usecase.createbackup;

import com.andremoresco.asynctask.model.Backup;
import com.andremoresco.asynctask.model.BackupStatus;
import com.andremoresco.asynctask.repository.BackupRepository;
import com.andremoresco.asynctask.usecase.dobackup.DoBackupService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;

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
        Mockito.when(this.doBackupService.execute(anyString()))
                .thenReturn(CompletableFuture.completedFuture(anyString()));

        Backup backupCreated = createBackupService.execute();

        Mockito.verify(backupRepository, times(1)).save(any());
        Mockito.verify(doBackupService, times(1)).execute(any());

        Assert.assertNotNull(backupCreated);
        Assert.assertNotNull(backupCreated.getBackupId());
    }

    @Test
    public void testCreateBackupReturnedStatusInProgress() throws Exception {
        Mockito.when(this.doBackupService.execute(anyString()))
                .thenReturn(CompletableFuture.completedFuture(anyString()));

        Backup backupCreated = createBackupService.execute();
        Assert.assertNotNull(backupCreated);
        Assert.assertEquals(BackupStatus.IN_PROGRESS, backupCreated.getStatus());
    }

    @Test
    public void updateStatusBackupAfterBackupFinishedWithOkStatus() throws Exception {
        Mockito.when(this.doBackupService.execute(anyString()))
                .thenReturn(CompletableFuture.completedFuture(anyString()));

        createBackupService.execute();

        Mockito.verify(this.backupRepository, times(1))
                .updateStatus(anyString(), eq(BackupStatus.OK));
    }

    @Test
    public void updateStatusBackupAfterBackupFinishedWithFailedStatus() throws Exception {
        CompletableFuture<String> future = new CompletableFuture<>();
        future.completeExceptionally(new Exception("Error"));

        Mockito.when(this.doBackupService.execute(anyString()))
                .thenReturn(future);

        createBackupService.execute();

        Mockito.verify(this.backupRepository, times(1))
                .updateStatus(anyString(), eq(BackupStatus.FAILED));
    }

}
