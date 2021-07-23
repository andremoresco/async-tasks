package com.andremoresco.asynctask.providers;

import com.andremoresco.asynctask.model.Email;
import com.andremoresco.asynctask.usecase.dobackup.SyncEmailCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailProvider {

    List<Email> getEmails() throws Exception;

}
