package com.andremoresco.asynctask.providers;

import com.andremoresco.asynctask.model.EmailData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailProvider {

    List<EmailData> getEmails() throws Exception;

}
