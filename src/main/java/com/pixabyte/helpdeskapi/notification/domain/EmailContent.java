package com.pixabyte.helpdeskapi.notification.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class EmailContent {

    private String userFullName;
    private String message;


}
