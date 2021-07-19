package com.axcient.gsuitesafe.gmailbackup.useCase.listBackups;

import com.axcient.gsuitesafe.gmailbackup.model.Backup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ListBackupsController.class)
public class ListBackupsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListBackupsService listBackupsService;

    @Test
    public void requestListBackupsSuccess() throws Exception {
        when(this.listBackupsService.execute()).thenReturn(Collections.singletonList(new Backup()));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/backups"))
                .andExpect(status().isOk());

    }

    @Test
    public void requestListBackupsWithNoBackupList() throws Exception {
        when(this.listBackupsService.execute()).thenReturn(Collections.emptyList());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/backups"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

    }

    @Test
    public void requestListBackupsThrowingError() throws Exception {
        when(this.listBackupsService.execute()).thenThrow(new Exception("test"));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/backups"))
                .andExpect(status().isInternalServerError())
                .andDo(print())
                .andExpect(content().json("{\"message\": \"test\", \"error_code\": \"INTERNAL_SERVER_ERROR\"}"));

    }
}
