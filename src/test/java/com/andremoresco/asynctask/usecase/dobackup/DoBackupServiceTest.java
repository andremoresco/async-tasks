package com.andremoresco.asynctask.usecase.dobackup;

import com.andremoresco.asynctask.model.Backup;
import com.andremoresco.asynctask.providers.EmailProvider;
import com.andremoresco.asynctask.repository.EmailRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DoBackupServiceTest {

    @Mock
    private EmailProvider emailProvider;

    @Mock
    private EmailRepository emailRepository;

    @InjectMocks
    private DoBackupService doBackupService;

//    @Test
//    public void testDoBackupSuccess() throws Exception {
//        Backup backup = new Backup();
//
//        when(this.emailProvider.getEmails()).thenReturn(Collections.emptyList());
//        CompletableFuture<String> execute = this.doBackupService.execute(backup.getBackupId().toString());
//
//        verify(this.emailProvider, times(1)).getEmails();
//        verify(this.emailRepository, times(1)).save(backup.getBackupId().toString(), Collections.emptyList());
//
//        Assert.assertTrue(execute.isDone());
//        Assert.assertFalse(execute.isCompletedExceptionally());
//        Assert.assertFalse(execute.isCancelled());
//    }
//
//    @Test
//    public void testDoBackupErrorEmailProvider() throws Exception {
//        Backup backup = new Backup();
//
//        Mockito.when(this.emailProvider.getEmails()).thenThrow(new Exception("Error to connect to provider"));
//
//        CompletableFuture<String> execute = this.doBackupService.execute(backup.getBackupId().toString());
//
//        verify(this.emailProvider, times(1)).getEmails();
//        verify(this.emailRepository, times(0)).save(backup.getBackupId().toString(), Collections.emptyList());
//
//        Assert.assertTrue(execute.isDone());
//        Assert.assertTrue(execute.isCompletedExceptionally());
//    }
//
//    @Test
//    public void errorEmailRepositorySave() throws Exception {
//        Backup backup = new Backup();
//
//        doThrow(new Exception()).when(this.emailRepository).save(anyString(), anyList());
//
//        CompletableFuture<String> execute = this.doBackupService.execute(backup.getBackupId().toString());
//
//        verify(this.emailProvider, times(1)).getEmails();
//        verify(this.emailRepository, times(1)).save(backup.getBackupId().toString(), Collections.emptyList());
//
//        Assert.assertTrue(execute.isDone());
//        Assert.assertTrue(execute.isCompletedExceptionally());
//
//    }


}
