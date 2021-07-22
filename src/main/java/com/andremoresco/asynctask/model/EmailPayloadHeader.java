package com.andremoresco.asynctask.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class EmailPayloadHeader {

    private String name;

    private String value;

 }
