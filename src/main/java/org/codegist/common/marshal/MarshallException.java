package org.codegist.common.marshal;

public class MarshallException extends RuntimeException {
    public MarshallException() {
    }

    public MarshallException(String message) {
        super(message);
    }

    public MarshallException(String message, Throwable cause) {
        super(message, cause);
    }

    public MarshallException(Throwable cause) {
        super(cause);
    }
}
