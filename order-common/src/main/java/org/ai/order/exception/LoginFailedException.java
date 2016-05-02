package org.ai.order.exception;

/**
 * Created by hua.ai on 2016/4/20.
 */
public class LoginFailedException extends Exception {
    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }
}
