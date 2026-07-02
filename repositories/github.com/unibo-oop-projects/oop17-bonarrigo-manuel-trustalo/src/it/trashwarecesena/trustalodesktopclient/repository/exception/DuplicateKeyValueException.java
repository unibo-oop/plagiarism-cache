package it.trashwarecesena.trustalodesktopclient.repository.exception;

/**
 * A {@link RuntimeException} expressing the illegal state of creating an already existent key value in a persistence 
 * storage where one and only one of a kind is expected to be present.
 * @author Manuel Bonarrigo
 *
 */
public final class DuplicateKeyValueException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -7335161303481949861L;

    /**
     * Constructs a new DuplicateKeyValueException with null as its detail message.
     */
    public DuplicateKeyValueException() {
        super();
    }

    /**
     * Constructs a new DuplicateKeyValueException with the specified detail message. The cause is not initialized, and 
     * may subsequently be initialized by a call to Throwable.initCause(java.lang.Throwable).
     * 
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage()
     * method.
     */
    public DuplicateKeyValueException(final String message) {
        super(message);
    }

    /**
     * Constructs a new DuplicateKeyValueException with the specified cause and a detail message of 
     * (cause==null ? null : cause.toString()) (which typically contains the class and detail message of cause).
     * 
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is
     * permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public DuplicateKeyValueException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new DuplicateKeyValueException with the specified detail message and cause.<p>
     * Note that the detail message associated with cause is not automatically incorporated in this runtime 
     * exception's detail message.
     * 
     * @param message the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is
     *  permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public DuplicateKeyValueException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
