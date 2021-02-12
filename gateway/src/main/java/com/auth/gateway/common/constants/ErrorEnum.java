package com.auth.gateway.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    /* 로그인 오류 메세지 정의 */
    LOG_NOT_FOUND(HttpStatus.NOT_FOUND, "LOG_000", "해당 로그인을 처리할 수 없습니다."),
    /* 로그인 오류 메세지 정의 끝*/

    /* 기타 오류 메세지 정의 */
    ETC(HttpStatus.INTERNAL_SERVER_ERROR, "ETC_000", "알 수 없는 오류입니다.");
    /* 기타 오류 메세지 정의 끝*/

    private final ErrorResponse errorResponse;

    public String getMessage() {
        return this.errorResponse.getMessage();
    }

    public String getErrCode() {
        return this.errorResponse.getErrCode();
    }

    public HttpStatus getHttpStatus() {
        return this.errorResponse.getHttpStatus();
    }

    ErrorEnum(HttpStatus httpStatus, String errCode, String message) {
        this.errorResponse = new ErrorResponse(httpStatus, errCode, message);
    }

    @Getter
    public static class ErrorResponse {
        private final HttpStatus httpStatus;
        private final String errCode;
        private final String message;

        public ErrorResponse(HttpStatus httpStatus, String errCode, String message) {
            this.httpStatus = httpStatus;
            this.errCode = errCode;
            this.message = message;
        }
    }
}
