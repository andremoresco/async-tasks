package com.andremoresco.asynctask.usecase.listbackups;

import com.andremoresco.asynctask.repository.BackupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ListBackupsServiceTest {

    @Mock
    private BackupRepository backupRepository;

    @InjectMocks
    private ListBackupsService listBackupsService;

    @Test
    public void listBackupsSuccess() throws Exception {
        when(this.backupRepository.find()).thenReturn(Collections.emptyList());

        this.listBackupsService.execute();

        verify(this.backupRepository, times(1)).find();
    }
}
