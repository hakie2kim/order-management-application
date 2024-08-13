package kr.co.ordermanagement.domain.exception;

public class CannotCancelStateException extends RuntimeException {

    public CannotCancelStateException(String message) {
        super(message);
    }

}
