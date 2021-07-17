package com.axcient.gsuitesafe.gmailbackup.providers;

import com.axcient.gsuitesafe.gmailbackup.model.EmailData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailProvider {

    List<EmailData> getEmails();

}
