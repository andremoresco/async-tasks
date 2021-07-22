package com.andremoresco.asynctask.usecase.dobackup;

import com.andremoresco.asynctask.model.Email;

import java.util.List;

@FunctionalInterface
public interface SyncEmailCallback {

    void success(List<Email> listEmail) throws Exception;

}
