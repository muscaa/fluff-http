package fluff.http;

/**
 * Represents an exception that can occur during HTTP operations.
 */
public class HTTPException extends RuntimeException {
    
    private static final long serialVersionUID = -7294604455183897422L;
    
    /**
     * Constructs a new HTTPException with {@code null} as its detail message.
     */
    public HTTPException() {
        super();
    }
    
    /**
     * Constructs a new HTTPException with the specified detail message.
     *
     * @param message the detail message
     */
    public HTTPException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new HTTPException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public HTTPException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new HTTPException with the specified cause.
     *
     * @param cause the cause
     */
    public HTTPException(Throwable cause) {
        super(cause);
    }
}
