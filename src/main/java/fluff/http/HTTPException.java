package fluff.http;

public class HTTPException extends RuntimeException {
	
	private static final long serialVersionUID = -7294604455183897422L;
	
	public HTTPException() {
        super();
    }
	
    public HTTPException(String message) {
        super(message);
    }
    
    public HTTPException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public HTTPException(Throwable cause) {
        super(cause);
    }
}
