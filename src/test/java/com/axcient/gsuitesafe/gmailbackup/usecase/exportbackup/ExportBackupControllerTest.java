package com.axcient.gsuitesafe.gmailbackup.usecase.exportbackup;

import com.axcient.gsuitesafe.gmailbackup.model.EmailData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ExportBackupController.class)
public class ExportBackupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExportBackupService exportBackupService;

    @Test
    public void handleWithBackupIdPath() throws Exception {

        when(this.exportBackupService.execute(anyString()))
                .thenReturn(Collections.singletonList(new EmailData()));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/exports/id"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Disposition"));

        verify(this.exportBackupService, times(1)).execute(anyString());
    }

    @Test
    public void handleWithBackupIdAndLabelPath() throws Exception {

        when(this.exportBackupService.execute(anyString(), anyString()))
                .thenReturn(Collections.singletonList(new EmailData()));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/exports/id/label"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Content-Disposition"));

        verify(this.exportBackupService, times(1)).execute(anyString(), anyString());
    }

    @Test
    public void handleWithBackupIdPathNoContentServiceReturningEmptyEmailList() throws Exception {

        when(this.exportBackupService.execute(anyString()))
                .thenReturn(Collections.emptyList());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/exports/id"))
                .andExpect(status().isNoContent());

        verify(this.exportBackupService, times(1)).execute(anyString());
    }

    @Test
    public void handleWithBackupIdAndLabelPathNoContentServiceReturningEmptyEmailList() throws Exception {

        when(this.exportBackupService.execute(anyString(), anyString()))
                .thenReturn(Collections.emptyList());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/exports/id/label"))
                .andExpect(status().isNoContent());

        verify(this.exportBackupService, times(1)).execute(anyString(), anyString());
    }

    @Test
    public void handleWithBackupIdPathNoContentServiceReturningNullEmailList() throws Exception {

        when(this.exportBackupService.execute(anyString()))
                .thenReturn(null);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/exports/id"))
                .andExpect(status().isNoContent());

        verify(this.exportBackupService, times(1)).execute(anyString());
    }

    @Test
    public void handleWithBackupIdAndLabelPathNoContentServiceReturningNullEmailList() throws Exception {

        when(this.exportBackupService.execute(anyString(), anyString()))
                .thenReturn(null);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/exports/id/label"))
                .andExpect(status().isNoContent());

        verify(this.exportBackupService, times(1)).execute(anyString(), anyString());
    }

}
