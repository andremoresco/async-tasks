package com.andremoresco.asynctask.usecase.createbackup;

import com.andremoresco.asynctask.model.Backup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CreateBackupController.class)
public class CreateBackupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateBackupService createBackupService;

    @Test
    public void createBackupSuccess() throws Exception {
        Backup backup = new Backup();
        Mockito.when(this.createBackupService.execute()).thenReturn(backup);

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/backups")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{\"backupId\": \"" + backup.getBackupId() + "\"}"));
    }

    @Test
    public void createBackupException() throws Exception {
        Mockito.when(this.createBackupService.execute()).thenThrow(new Exception());

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/backups")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

}
