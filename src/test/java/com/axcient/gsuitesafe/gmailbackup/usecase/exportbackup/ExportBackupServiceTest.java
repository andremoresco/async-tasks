package com.axcient.gsuitesafe.gmailbackup.usecase.exportbackup;

import com.axcient.gsuitesafe.gmailbackup.exceptions.BackupNotFoundException;
import com.axcient.gsuitesafe.gmailbackup.exceptions.BackupNotReadyToExportException;
import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.model.BackupStatus;
import com.axcient.gsuitesafe.gmailbackup.model.EmailData;
import com.axcient.gsuitesafe.gmailbackup.repository.EmailRepository;
import com.axcient.gsuitesafe.gmailbackup.usecase.findbackup.FindBackupByIdService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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
                        new EmailData("from", "to", new Date(), new Date(), new Date(), "important", "Subject", "aa"),
                        new EmailData("from", "to", new Date(), new Date(), new Date(), "draft", "Subject", "aa")
                ));

        List<EmailData> draft = this.exportBackupService.execute(anyString(), "draft");

        Assert.assertEquals(1, draft.size());
        Assert.assertEquals("draft", draft.get(0).getLabels());
    }

}
