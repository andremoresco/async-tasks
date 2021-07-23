package com.andremoresco.asynctask.providers.implementation;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

@Component
public class GmailOauthAuthentication {

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private static final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_LABELS, GmailScopes.GMAIL_READONLY);

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${gmail.authentication.token_directory:token}")
    private String tokenDirectory;

    @Value("${gmail.authentication.user.id}")
    private String userId;

    @Value("${gmail.authentication.client.id}")
    private String clientId;

    @Value("${gmail.authentication.client.secret}")
    private String clientSecret;

    @Value("${gmail.authentication.access_type:offline}")
    private String accessType;

    @Value("${gmail.authentication.server.port:8888}")
    private Integer serverPort;

    public Gmail execute() throws GeneralSecurityException, IOException {

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(applicationName)
                .build();

    }

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientId, clientSecret, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(tokenDirectory)))
                .setAccessType(accessType)
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(serverPort).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize(userId);
    }

}
