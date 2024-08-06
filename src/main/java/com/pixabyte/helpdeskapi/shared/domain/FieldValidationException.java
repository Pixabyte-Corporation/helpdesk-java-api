package com.pixabyte.helpdeskapi.shared.domain;

import lombok.Getter;

@Getter
public class FieldValidationException extends RuntimeException {

    private final String fieldName;
    private final String value;
    private final String messageDetails;

    public FieldValidationException(String message, String fieldName, String value) {
        super(message);
        this.fieldName = fieldName;
        this.value = value;
        messageDetails = String.format("Field validation fails for field %s with value: %s",
                fieldName,
                value != null? value: "null");
    }


}
