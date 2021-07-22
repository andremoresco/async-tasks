package com.andremoresco.asynctask.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class EmailPayloadBody {


    private String attachmentId;

    private String data;

    private Integer size;

}
