package com.thoughtworks.FizzBuzzWhizz;

/**
 * 特殊数录入不合法
 * User: zjzhai
 * Date: 4/29/14
 */
public class SpecialNumberIllegalException extends RuntimeException {
    public SpecialNumberIllegalException() {
    }

    public SpecialNumberIllegalException(String message) {
        super(message);
    }

    public SpecialNumberIllegalException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpecialNumberIllegalException(Throwable cause) {
        super(cause);
    }

    public SpecialNumberIllegalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
