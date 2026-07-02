package it.trashwarecesena.trustalodesktopclient.repository.exception;

/**
 * A {@link RuntimeException} expressing that the requested action is impossible
 * to perform since the some kind of reference of the entity to be manipulated
 * is being hold by some other participant, therefore it can not be changed or
 * deleted.
 * 
 * @author Manuel Bonarrigo
 *
 */
public class BoundedReferenceException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 7962767450317395438L;

    /**
     * Constructs a new BoundedReferenceException with null as its detail message.
     */
    public BoundedReferenceException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructs a new BoundedReferenceException with the specified detail message. The cause is not initialized, and 
     * may subsequently be initialized by a call to Throwable.initCause(java.lang.Throwable).
     * 
     * @param message the detail message. The detail message is saved for later retrieval by the Throwable.getMessage()
     * method.
     */
    public BoundedReferenceException(final String message) {
        super(message);
    }

    /**
     * Constructs a new BoundedReferenceException with the specified cause and a detail message of 
     * (cause==null ? null : cause.toString()) (which typically contains the class and detail message of cause).
     * 
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is
     * permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public BoundedReferenceException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new BoundedReferenceException with the specified detail message and cause.<p>
     * Note that the detail message associated with cause is not automatically incorporated in this runtime 
     * exception's detail message.
     * 
     * @param message the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is
     *  permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public BoundedReferenceException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
