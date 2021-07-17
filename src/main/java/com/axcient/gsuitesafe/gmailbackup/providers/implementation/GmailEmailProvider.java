package com.axcient.gsuitesafe.gmailbackup.providers.implementation;

import com.axcient.gsuitesafe.gmailbackup.model.EmailData;
import com.axcient.gsuitesafe.gmailbackup.providers.EmailProvider;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

//    private List<EmailData> arrayEmails() {
//
//
//        List<EmailData> list = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            list.add(newEmail("important,inbox"));
//        }
//        return Arrays.asList(
//                newEmail("important,inbox")
////                newEmail("important,inbox"),
////                newEmail("important,inbox"),
////                newEmail("important,inbox"),
////                newEmail("important,inbox"),
////                newEmail("important,inbox"),
////                newEmail("important,inbox"),
////                newEmail("important,inbox"),
////                newEmail("draft"),
////                newEmail("draft"),
////                newEmail("draft"),
////                newEmail("draft"),
////                newEmail("draft"),
////                newEmail("draft"),
////                newEmail("draft"),
////                newEmail("sent"),
////                newEmail("sent"),
////                newEmail("sent"),
////                newEmail("sent"),
////                newEmail("sent"),
////                newEmail("starred"),
////                newEmail("starred"),
////                newEmail("starred"),
////                newEmail("starred"),
////                newEmail("starred"),
////                newEmail("starred"),
////                newEmail("starred"),
////                newEmail("starred"),
////                newEmail("starred"),
////                newEmail("starred")
//        );
//    }

    private EmailData newEmail(String labels, String subject) {
        return new EmailData("amoresco1@gmail.com", "xxx@gmail.com", new Date(), new Date(), new Date(), labels, subject, "kaksjdkaja");
    }
}
