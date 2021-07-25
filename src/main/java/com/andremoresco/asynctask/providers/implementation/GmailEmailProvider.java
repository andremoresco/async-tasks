package com.andremoresco.asynctask.providers.implementation;

import com.andremoresco.asynctask.exceptions.BackupFailureException;
import com.andremoresco.asynctask.model.Email;
import com.andremoresco.asynctask.providers.EmailProvider;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GmailEmailProvider implements EmailProvider {

    private final String AUTHENTICATED_USER = "me";
    private final GmailOauthAuthentication gmailOauthAuthentication;
    private List<Email> LIST_EMAILS;

    @Value("${gmail.messages.format:raw}")
    private String format;

    @Autowired
    public GmailEmailProvider(GmailOauthAuthentication gmailOauthAuthentication) {
        this.gmailOauthAuthentication = gmailOauthAuthentication;
    }

    @Override
    public List<Email> getEmails() throws Exception {

        Gmail service = this.gmailOauthAuthentication.execute();
        this.LIST_EMAILS = new ArrayList<>();

        this.syncEmails(service, null);

        return LIST_EMAILS;
    }

    private void syncEmails(Gmail service, String pageToken) throws IOException {
        ListMessagesResponse messageResponse = this.listMessages(service, pageToken);

        this.executeBatchRequestToGetEmails(service, messageResponse.getMessages());

        if (Objects.nonNull(messageResponse.getNextPageToken())) {
            this.syncEmails(service, messageResponse.getNextPageToken());
        }
    }

    private ListMessagesResponse listMessages(Gmail service, String pageToken) throws IOException {
        return service.users().messages().list(AUTHENTICATED_USER).setPageToken(pageToken).execute();
    }

    private void executeBatchRequestToGetEmails(Gmail service, List<Message> messages) throws IOException {
        if (messages == null || messages.isEmpty()) {
            return;
        }

        BatchRequest batchRequest = service.batch();

        for (Message message : messages) {
            service.users().messages().get(AUTHENTICATED_USER, message.getId()).setFormat(this.format).queue(batchRequest, getJsonBatchCallback());
        }
        batchRequest.execute();
    }

    private JsonBatchCallback<Message> getJsonBatchCallback() {
        return new JsonBatchCallback<>() {

            @Override
            public void onSuccess(Message message, HttpHeaders httpHeaders) {
                ModelMapper modelMapper = new ModelMapper();
                Email email = modelMapper.map(message, Email.class);
                LIST_EMAILS.add(email);
            }

            @SneakyThrows
            @Override
            public void onFailure(GoogleJsonError googleJsonError, HttpHeaders httpHeaders) {
                throw new BackupFailureException(googleJsonError.getMessage());

            }
        };
    }
}
