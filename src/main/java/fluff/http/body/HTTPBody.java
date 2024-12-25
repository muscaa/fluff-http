package fluff.http.body;

import java.io.IOException;
import java.io.InputStream;

import fluff.functions.gen.TFunc;
import fluff.http.HTTPException;

/**
 * Represents the body of an HTTP request or response.
 */
public class HTTPBody {
    
    private final TFunc<InputStream, IOException> inFunc;
    
    private HTTPBody(TFunc<InputStream, IOException> inFunc) {
        this.inFunc = inFunc;
    }
    
    @SuppressWarnings("resource")
	private <V> V get(HTTPBodyParser<V> parser, boolean close) throws HTTPException {
        try {
        	InputStream in = inFunc.invoke();
			V value = parser.deserialize(in);
			if (close) in.close();
			return value;
		} catch (IOException e) {
			throw new HTTPException(e);
		}
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
        return get(parser, true);
    }
	
    /**
     * Parses the body content using the specified parser.
     *
     * @param <V> the type of the parsed result
     * @param parser the parser to use for parsing the body content
     * @return the parsed result
     * @throws HTTPException if an error occurs while deserializing the body content
     */
	public <V> V getNoClose(HTTPBodyParser<V> parser) throws HTTPException {
        return get(parser, false);
    }
    
	/**
	 * Returns whether the body is empty.
	 *
	 * @return true if the body is empty, false otherwise
	 */
	public boolean isEmpty() {
        return inFunc == null;
	}
    
    /**
     * Creates an empty HTTP body.
     *
     * @return a new HTTPBody instance with an empty body
     */
	public static HTTPBody of() {
        return of(null);
    }
	
	
	/**
	 * Creates an HTTP body from the specified input stream function.
	 *
	 * @param inFunc the function to use for obtaining the input stream
	 * @return a new HTTPBody instance with the specified content
	 */
	public static HTTPBody of(TFunc<InputStream, IOException> inFunc) {
		return new HTTPBody(inFunc);
	}
    
	/**
	 * Creates an HTTP body from the specified value.
	 *
	 * @param <V> the type of the value
	 * @param parser the parser to use for serializing the value
	 * @param value the value to use as content
	 * @return a new HTTPBody instance with the specified content
	 */
	public static <V> HTTPBody of(HTTPBodyParser<V> parser, V value) {
		return new HTTPBody(() -> parser.serialize(value));
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
