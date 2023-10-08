package selly.authenticate.login.service.exception;

import lombok.Getter;

@Getter
public class SellyLoginException extends RuntimeException {
    private String message;
    private String errorCode;
    private static String ERROR_CODE_ACCT_NF = "SELLY_LG_ERROR_1";

    public SellyLoginException(String message) {
        this.message = message;
        this.errorCode = ERROR_CODE_ACCT_NF;
    }
}
