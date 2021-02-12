package com.auth.userserver.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Max;

@Setter
@Getter
public class PageLimitRequest {
    private int page;

    @Max(value = 100, message = "요청할 수 있는 size의 최대크기는 100입니다.")
    private int size;

    private Sort.Direction sortDirection;

    private String sortColumn;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page -1, size, sortDirection, sortColumn);
    }
}
