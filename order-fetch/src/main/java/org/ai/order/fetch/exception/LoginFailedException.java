package org.ai.order.fetch.exception;

/**
 * Created by hua.ai on 2016/4/9.
 */
public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
