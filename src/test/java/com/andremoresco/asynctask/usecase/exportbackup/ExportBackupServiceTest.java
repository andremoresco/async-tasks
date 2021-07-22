package com.andremoresco.asynctask.usecase.exportbackup;

import com.andremoresco.asynctask.exceptions.BackupNotFoundException;
import com.andremoresco.asynctask.exceptions.BackupNotReadyToExportException;
import com.andremoresco.asynctask.model.Backup;
import com.andremoresco.asynctask.model.BackupStatus;
import com.andremoresco.asynctask.model.Email;
import com.andremoresco.asynctask.repository.EmailRepository;
import com.andremoresco.asynctask.usecase.findbackup.FindBackupByIdService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExportBackupServiceTest {

    @Mock
    private FindBackupByIdService findBackupByIdService;

    @Mock
    private EmailRepository emailRepository;

    @InjectMocks
    private ExportBackupService exportBackupService;

    @Test
    public void backupFoundAndStatusOk() throws Exception {

        var backup = new Backup();
        backup.setStatus(BackupStatus.OK);

        when(this.findBackupByIdService.execute(anyString())).thenReturn(backup);
        when(this.emailRepository.list(anyString())).thenReturn(Collections.emptyList());

        this.exportBackupService.execute(backup.getBackupId().toString());

        verify(this.findBackupByIdService, times(1)).execute(anyString());
        verify(this.emailRepository, times(1)).list(anyString());

    }

    @Test(expected = BackupNotReadyToExportException.class)
    public void backupFoundAndStatusInProgress() throws Exception {
        var backup = new Backup();

        when(this.findBackupByIdService.execute(anyString())).thenReturn(backup);

        this.exportBackupService.execute(backup.getBackupId().toString());

    }

    @Test(expected = BackupNotReadyToExportException.class)
    public void backupFoundAndStatusFailed() throws Exception {
        var backup = new Backup();
        backup.setStatus(BackupStatus.FAILED);
        when(this.findBackupByIdService.execute(anyString())).thenReturn(backup);

        this.exportBackupService.execute(backup.getBackupId().toString());

    }

    @Test(expected = BackupNotFoundException.class)
    public void backupNotFound() throws Exception {
        when(this.findBackupByIdService.execute(anyString())).thenThrow(new BackupNotFoundException());

        this.exportBackupService.execute(anyString());

    }

    @Test
    public void backupFoundAndStatusOkAndFilterListByLabel() throws Exception {
        Backup backup = new Backup();
        backup.setStatus(BackupStatus.OK);
        when(this.findBackupByIdService.execute(anyString()))
                .thenReturn(backup);

        when(this.emailRepository.list(anyString()))
                .thenReturn(Arrays.asList(
                        Email.builder().id("aaa").labelIds(Collections.singletonList("draft")).build(),
                        Email.builder().id("bbb").labelIds(Collections.singletonList("inbox")).build()
                ));

        List<Email> draft = this.exportBackupService.execute(anyString(), "draft");

        Assert.assertEquals(1, draft.size());
        Assert.assertEquals(Collections.singletonList("draft"), draft.get(0).getLabelIds());
    }

}
