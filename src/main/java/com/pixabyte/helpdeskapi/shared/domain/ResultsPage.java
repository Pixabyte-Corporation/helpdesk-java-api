package com.pixabyte.helpdeskapi.shared.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder(toBuilder = true)
public class ResultsPage<T> {
    List<T> results;
    Integer pageNumber;
    Integer pageSize;
    Long totalElements;
}
