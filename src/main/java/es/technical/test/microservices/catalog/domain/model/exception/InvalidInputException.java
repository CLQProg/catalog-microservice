package es.technical.test.microservices.catalog.domain.model.exception;

/**
 * Bad request exception.
 */
public class InvalidInputException extends GenericException {


    /**
     * Bad request exception constructor.
     *
     * @param message message.
     * @param code    code.
     */
    public InvalidInputException(long code, String message) {
        super(code, message);
    }
}
