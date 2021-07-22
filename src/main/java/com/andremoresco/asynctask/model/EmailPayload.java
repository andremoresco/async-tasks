package com.andremoresco.asynctask.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class EmailPayload {

    private EmailPayloadBody body;

    private String filename;

    private List<EmailPayloadHeader> headers;

    private String mimeType;

    private String partId;

    private List<EmailPayload> parts;
    
}
