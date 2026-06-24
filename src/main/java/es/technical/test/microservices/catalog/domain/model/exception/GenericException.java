package es.technical.test.microservices.catalog.domain.model.exception;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {


    private final long errorCode;
    private final String message;

    /**
     * Generic Exception constructor.
     *
     * @param message   message.
     * @param errorCode errorCode.
     */
    public GenericException(long errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
