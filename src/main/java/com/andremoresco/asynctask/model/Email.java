package com.andremoresco.asynctask.model;

import lombok.*;

import java.math.BigInteger;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Email {

    private BigInteger historyId;

    private String id;

    private Long internalDate;

    private List<String> labelIds;

    private String raw;

    private Integer sizeEstimate;

    private String snippet;

    private String threadId;

    private EmailPayload payload;

}
