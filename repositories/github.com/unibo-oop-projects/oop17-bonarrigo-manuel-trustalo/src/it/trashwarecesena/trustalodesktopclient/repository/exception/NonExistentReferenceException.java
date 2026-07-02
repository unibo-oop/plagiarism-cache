package it.trashwarecesena.trustalodesktopclient.repository.exception;

/**
 * A {@link RuntimeException} expressing the inability to retrieve from any persistence storage the information 
 * requested by the client, because they do not exist.
 * @author Manuel Bonarrigo
 *
 */
public final class NonExistentReferenceException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -7504991940137371127L;

    /**
     * Constructs a new NonExistentReferenceException with null as its detail message.
     */
    public NonExistentReferenceException() {
        super();
    }

    /**
     * Constructs a new NonExistentReferenceException with the specified detail message. The cause is not initialized, 
     * and may subsequently be initialized by a call to Throwable.initCause(java.lang.Throwable).
     * 
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage()
     * method.
     */
    public NonExistentReferenceException(final String message) {
        super(message);
    }

    /**
     * Constructs a new NonExistentReferenceException with the specified cause and a detail message of 
     * (cause==null ? null : cause.toString()) (which typically contains the class and detail message of cause).
     * 
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is
     * permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public NonExistentReferenceException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new NonExistentReferenceException with the specified detail message and cause.
     * <p>
     * Note that the detail message associated with cause is not automatically incorporated in this runtime 
     * exception's detail message.
     * 
     * @param message the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is
     *  permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public NonExistentReferenceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
