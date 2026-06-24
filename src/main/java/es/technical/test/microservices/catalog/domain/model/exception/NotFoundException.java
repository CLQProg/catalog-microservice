package es.technical.test.microservices.catalog.domain.model.exception;


public class NotFoundException extends GenericException {


    public NotFoundException(long code, String message) {
        super(code, message);
    }
}
