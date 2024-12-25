package fluff.http.body;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import fluff.http.HTTPException;

/**
 * Builder class for constructing an {@link HTTPBody} instance.
 */
public class HTTPBodyBuilder {
    
    protected final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    
    /**
     * Appends a portion of a byte array to the HTTP body.
     *
     * @param value the byte array to append
     * @param off the start offset in the byte array
     * @param len the number of bytes to append
     * @return this builder instance
     */
    public HTTPBodyBuilder append(byte[] value, int off, int len) {
        bytes.write(value, off, len);
        return this;
    }
    
    /**
	 * Appends the serialized value to the HTTP body.
	 *
	 * @param <V> the type of the value
	 * @param parser the parser to use for serializing the value
	 * @param value the value to append
	 * @return this builder instance
	 * @throws HTTPException if an error occurs while serializing the value
	 */
	public <V> HTTPBodyBuilder append(HTTPBodyParser<V> parser, V value) throws HTTPException {
		try {
			InputStream in = parser.serialize(value);
			byte[] buffer = new byte[8192];
			int read;
			while ((read = in.read(buffer)) != -1) {
				append(buffer, 0, read);
			}
			in.close();
			return this;
		} catch (IOException e) {
			throw new HTTPException(e);
		}
	}
    
    /**
     * Builds the HTTP body with the appended data.
     *
     * @return a new {@link HTTPBody} instance containing the appended data
     */
    public HTTPBody build() {
        return HTTPBody.of(HTTPBodyParser.BYTES, bytes.toByteArray());
    }
}
