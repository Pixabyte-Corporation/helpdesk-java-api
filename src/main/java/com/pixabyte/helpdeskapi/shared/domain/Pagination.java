package com.pixabyte.helpdeskapi.shared.domain;

public record Pagination(
        Integer pageSize,
        Integer pageNumber
) {
}
