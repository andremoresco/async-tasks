package com.axcient.gsuitesafe.gmailbackup.providers.implementation;

import com.axcient.gsuitesafe.gmailbackup.model.EmailData;
import com.axcient.gsuitesafe.gmailbackup.providers.EmailProvider;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class created to simulate a connection with Gmail provider
 * and get the emails to backup.
 */

@Repository
public class GmailEmailProvider implements EmailProvider {

    @Override
    public List<EmailData> getEmails() {
        List<EmailData> emails = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            emails.add(newEmail("important,inbox", "testEmail" + i));
        }
        return emails;
    }

    private EmailData newEmail(String labels, String subject) {
        return new EmailData("amoresco1@gmail.com", "xxx@gmail.com", new Date(), new Date(), new Date(), labels, subject, "kaksjdkaja");
    }
}
