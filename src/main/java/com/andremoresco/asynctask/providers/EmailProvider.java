package com.andremoresco.asynctask.providers;

import com.andremoresco.asynctask.usecase.dobackup.SyncEmailCallback;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailProvider {

    void getEmails(SyncEmailCallback syncEmailCallback) throws Exception;

}
