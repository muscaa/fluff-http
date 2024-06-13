package fluff.http.body;

import fluff.http.HTTPException;

/**
 * Represents the body of an HTTP request or response.
 */
public class HTTPBody {
    
    private final byte[] bytes;
    
    private HTTPBody(byte[] bytes) {
        this.bytes = bytes;
    }
    
    /**
     * Parses the body content using the specified parser.
     *
     * @param <V> the type of the parsed result
     * @param parser the parser to use for parsing the body content
     * @return the parsed result
     * @throws HTTPException if the bytes cannot be parsed
     */
    public <V> V get(HTTPBodyParser<V> parser) throws HTTPException {
        return parser.parse(bytes);
    }
    
    /**
     * Creates an empty HTTP body.
     *
     * @return a new HTTPBody instance with an empty byte array
     */
    public static HTTPBody of() {
        return new HTTPBody(new byte[0]);
    }
    
    /**
     * Creates an HTTP body with the specified byte array.
     *
     * @param bytes the byte array to use as the body content
     * @return a new HTTPBody instance with the specified byte array
     */
    public static HTTPBody of(byte[] bytes) {
        return new HTTPBody(bytes);
    }
    
    /**
     * Creates a new HTTPBodyBuilder instance.
     *
     * @return a new HTTPBodyBuilder instance
     */
    public static HTTPBodyBuilder builder() {
        return new HTTPBodyBuilder();
    }
}
