package com.pixabyte.helpdeskapi.shared.domain;

public class ParagraphMother {
    public static String random() {
        return MotherCreator.random().lorem().paragraph();
    }
}
