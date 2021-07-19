package com.axcient.gsuitesafe.gmailbackup.usecase.findbackup;

import com.axcient.gsuitesafe.gmailbackup.exceptions.BackupNotFoundException;
import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import com.axcient.gsuitesafe.gmailbackup.repository.BackupRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindBackupByIdServiceTest {

    @Mock
    private BackupRepository backupRepository;

    @InjectMocks
    private FindBackupByIdService findBackupByIdService;

    @Test
    public void findBackupByIdSuccess() throws BackupNotFoundException {
        when(this.backupRepository.findById(anyString())).thenReturn(Optional.of(new Backup()));

        Backup backupFound = this.findBackupByIdService.execute(anyString());

        Assert.assertNotNull(backupFound);

    }

    @Test(expected = BackupNotFoundException.class)
    public void findBackupByIdNotFound() throws BackupNotFoundException {
        when(this.backupRepository.findById(anyString())).thenReturn(Optional.empty());

        this.findBackupByIdService.execute(anyString());

    }

    @Test(expected = BackupNotFoundException.class)
    public void findBackupByIdWithIdParamNull() throws BackupNotFoundException {

        this.findBackupByIdService.execute(null);

    }

}
