package com.andremoresco.asynctask.providers.implementations;

import com.andremoresco.asynctask.exceptions.BackupFailureException;
import com.andremoresco.asynctask.model.Email;
import com.andremoresco.asynctask.providers.implementation.GmailEmailProvider;
import com.andremoresco.asynctask.providers.implementation.GmailOauthAuthentication;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class GmailEmailProviderTest {

    @Mock
    private GmailOauthAuthentication gmailOauthAuthentication;

    private Gmail gmail;
    private Gmail.Users.Messages messages;

    @InjectMocks
    private GmailEmailProvider gmailEmailProvider;

    @Captor
    private ArgumentCaptor<JsonBatchCallback<Message>> messageCaptor;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(gmailEmailProvider, "format", "raw");

        this.gmail = mock(Gmail.class);
        this.messages = mockMessages(mockUsers(this.gmail));

    }

    @Test
    public void syncEmail() throws Exception {

        when(this.gmailOauthAuthentication.execute()).thenReturn(gmail);

        Gmail.Users.Messages.List list = mockList();

        Message responseMessage = new Message();
        responseMessage.setHistoryId(BigInteger.TWO);
        responseMessage.setId("1234");

        ListMessagesResponse listExecuteResponse = new ListMessagesResponse();
        listExecuteResponse.setMessages(Collections.singletonList(responseMessage));
        listExecuteResponse.setNextPageToken(null);

        when(list.execute())
                .thenReturn(listExecuteResponse);

        BatchRequest batchRequestMock = mockBatchRequest();

        Gmail.Users.Messages.Get get = mockGet();

        this.gmailEmailProvider.getEmails();

        verify(get, times(1)).queue(any(), any());
        verify(list, times(1)).execute();
        verify(batchRequestMock, times(1)).execute();

    }

    @Test
    public void syncEmailWithMoreThanOnePage() throws Exception {

        when(this.gmailOauthAuthentication.execute()).thenReturn(gmail);

        Gmail.Users.Messages.List list = mockList();


        Message responseMessage = new Message();
        responseMessage.setHistoryId(BigInteger.TWO);
        responseMessage.setId("1234");

        ListMessagesResponse listExecuteResponse = new ListMessagesResponse();
        listExecuteResponse.setMessages(Collections.singletonList(responseMessage));
        listExecuteResponse.setNextPageToken("nextPageToken");

        Message secondResponseMessage = new Message();
        secondResponseMessage.setHistoryId(BigInteger.TEN);
        secondResponseMessage.setId("12342");

        ListMessagesResponse listExecuteSecondResponse = new ListMessagesResponse();
        listExecuteSecondResponse.setMessages(Collections.singletonList(secondResponseMessage));
        listExecuteSecondResponse.setNextPageToken(null);


        when(list.execute())
                .thenReturn(listExecuteResponse, listExecuteSecondResponse);

        BatchRequest batchRequestMock = mockBatchRequest();

        Gmail.Users.Messages.Get get = mockGet();

        this.gmailEmailProvider.getEmails();

        verify(list, times(2)).execute();
        verify(get, times(2)).queue(any(), any());
        verify(batchRequestMock, times(2)).execute();

    }

    @Test
    public void listGmailEmpty() throws Exception {

        when(this.gmailOauthAuthentication.execute()).thenReturn(gmail);

        Gmail.Users.Messages.List list = mockList();

        ListMessagesResponse listExecuteResponse = new ListMessagesResponse();
        listExecuteResponse.setMessages(Collections.emptyList());
        listExecuteResponse.setNextPageToken(null);

        when(list.execute())
                .thenReturn(listExecuteResponse);

        BatchRequest batchRequestMock = mockBatchRequest();

        Gmail.Users.Messages.Get get = mockGet();

        this.gmailEmailProvider.getEmails();

        verify(list, times(1)).execute();
        verify(get, times(0)).queue(any(), any());
        verify(batchRequestMock, times(0)).execute();

    }

    @Test
    public void getEmailSuccessCallback() throws Exception {

        when(this.gmailOauthAuthentication.execute()).thenReturn(gmail);

        Gmail.Users.Messages.List list = mockList();

        Message responseMessage = new Message();
        responseMessage.setHistoryId(BigInteger.TWO);
        responseMessage.setId("1234");

        ListMessagesResponse listExecuteResponse = new ListMessagesResponse();
        listExecuteResponse.setMessages(Collections.singletonList(responseMessage));
        listExecuteResponse.setNextPageToken(null);

        when(list.execute())
                .thenReturn(listExecuteResponse);

        BatchRequest batchRequestMock = mockBatchRequest();

        Gmail.Users.Messages.Get get = mockGet();

        List<Email> emails = this.gmailEmailProvider.getEmails();

        verify(list, times(1)).execute();
        verify(batchRequestMock, times(1)).execute();
        verify(get, times(1)).queue(any(), messageCaptor.capture());

        JsonBatchCallback<Message> value = messageCaptor.getValue();
        value.onSuccess(responseMessage, mock(HttpHeaders.class));

        Assert.assertEquals(1, emails.size());

    }

    @Test(expected = BackupFailureException.class)
    public void getEmailFailureCallback() throws Exception {

        when(this.gmailOauthAuthentication.execute()).thenReturn(gmail);

        Gmail.Users.Messages.List list = mockList();

        Message responseMessage = new Message();
        responseMessage.setHistoryId(BigInteger.TWO);
        responseMessage.setId("1234");

        ListMessagesResponse listExecuteResponse = new ListMessagesResponse();
        listExecuteResponse.setMessages(Collections.singletonList(responseMessage));
        listExecuteResponse.setNextPageToken(null);

        when(list.execute())
                .thenReturn(listExecuteResponse);

        BatchRequest batchRequestMock = mockBatchRequest();

        Gmail.Users.Messages.Get get = mockGet();

        this.gmailEmailProvider.getEmails();

        verify(list, times(1)).execute();
        verify(batchRequestMock, times(1)).execute();
        verify(get, times(1)).queue(any(), messageCaptor.capture());

        JsonBatchCallback<Message> value = messageCaptor.getValue();
        value.onFailure(mock(GoogleJsonError.class), mock(HttpHeaders.class));

    }

    private Gmail.Users.Messages.Get mockGet() throws IOException {
        Gmail.Users.Messages.Get get = mock(Gmail.Users.Messages.Get.class);
        when(messages.get(eq("me"), anyString()))
                .thenReturn(get);
        when(get.setFormat(anyString()))
                .thenReturn(get);
        return get;
    }

    private BatchRequest mockBatchRequest() {
        BatchRequest batchRequestMock = mock(BatchRequest.class);
        when(gmail.batch())
                .thenReturn(batchRequestMock);
        return batchRequestMock;
    }

    private Gmail.Users.Messages.List mockList() throws IOException {
        Gmail.Users.Messages.List list = mock(Gmail.Users.Messages.List.class);
        when(messages.list("me")).thenReturn(list);
        when(list.setPageToken(any())).thenReturn(list);
        return list;
    }

    private Gmail.Users.Messages mockMessages(Gmail.Users users) {
        Gmail.Users.Messages messages = mock(Gmail.Users.Messages.class);
        doReturn(messages).when(users).messages();
        return messages;
    }

    private Gmail.Users mockUsers(Gmail gmail) {
        Gmail.Users users = mock(Gmail.Users.class);
        doReturn(users).when(gmail).users();
        return users;
    }
}
