package com.axcient.gsuitesafe.gmailbackup.usecase.exportbackup;

import com.axcient.gsuitesafe.gmailbackup.model.EmailData;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateBackupZipOutputStream {

    public static void execute(OutputStream outputStream, List<EmailData> listEmails) throws IOException {
        var zipOutputStream = new ZipOutputStream(outputStream);

        for (var emailData : listEmails) {
            var emailBytes = emailData.toString().getBytes();
            var entry = new ZipEntry(emailData.getSubject() + ".txt");
            entry.setSize(emailBytes.length);
            zipOutputStream.putNextEntry(entry);
            zipOutputStream.write(emailBytes);
            zipOutputStream.closeEntry();
        }

        zipOutputStream.close();
    }
}
