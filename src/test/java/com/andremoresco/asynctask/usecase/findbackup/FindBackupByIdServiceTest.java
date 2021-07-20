package com.andremoresco.asynctask.usecase.findbackup;

import com.andremoresco.asynctask.exceptions.BackupNotFoundException;
import com.andremoresco.asynctask.model.Backup;
import com.andremoresco.asynctask.repository.BackupRepository;
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
