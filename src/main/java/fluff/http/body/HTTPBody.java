package fluff.http.body;

import java.io.IOException;
import java.io.InputStream;

import fluff.http.HTTPException;

/**
 * Represents the body of an HTTP request or response.
 */
public class HTTPBody {
    
    private final InputStream in;
    
    private HTTPBody(InputStream in) {
        this.in = in;
    }
    
    /**
     * Parses the body content using the specified parser.
     *
     * @param <V> the type of the parsed result
     * @param parser the parser to use for parsing the body content
     * @return the parsed result
     * @throws HTTPException if an error occurs while deserializing the body content
     */
    public <V> V get(HTTPBodyParser<V> parser) throws HTTPException {
        try {
			return parser.deserialize(in);
		} catch (IOException e) {
			throw new HTTPException(e);
		}
    }
    
    /**
     * Creates an empty HTTP body.
     *
     * @return a new HTTPBody instance with an empty body
     * @throws HTTPException if an error occurs while creating the body
     */
    @SuppressWarnings("resource")
	public static HTTPBody of() throws HTTPException {
        return of(HTTPBodyParser.INPUT_STREAM, InputStream.nullInputStream());
    }
    
	/**
	 * Creates an HTTP body from the specified value.
	 *
	 * @param <V> the type of the value
	 * @param parser the parser to use for serializing the value
	 * @param value the value to use as content
	 * @return a new HTTPBody instance with the specified content
	 * @throws HTTPException if an error occurs while serializing the value
	 */
	@SuppressWarnings("resource")
	public static <V> HTTPBody of(HTTPBodyParser<V> parser, V value) throws HTTPException {
		try {
			return new HTTPBody(parser.serialize(value));
		} catch (IOException e) {
			throw new HTTPException(e);
		}
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
