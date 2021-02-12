package com.auth.userserver.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    /* 사용자 오류 메세지 정의 */
    USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "USER_000", "해당 아이디가 이미 존재합니다"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_001", "해당 아이디가 존재하지 않습니다."),
    USER_ALREADY_CONFIRMED(HttpStatus.BAD_REQUEST, "USER_001", "해당 아이디가 이미 이메일 인증을 받았습니다."),
    /* 사용자 오류 메세지 정의 끝*/

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
