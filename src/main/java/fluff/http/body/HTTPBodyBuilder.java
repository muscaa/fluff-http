package fluff.http.body;

import java.io.ByteArrayOutputStream;

import fluff.json.JSON;

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
     * Appends a byte array to the HTTP body.
     *
     * @param value the byte array to append
     * @return this builder instance
     */
    public HTTPBodyBuilder append(byte[] value) {
        return append(value, 0, value.length);
    }
    
    /**
     * Appends a string to the HTTP body.
     *
     * @param value the string to append
     * @return this builder instance
     */
    public HTTPBodyBuilder append(String value) {
        return append(value.getBytes());
    }
    
    /**
     * Appends a JSON object to the HTTP body.
     *
     * @param value the JSON object to append
     * @return this builder instance
     */
    public HTTPBodyBuilder append(JSON value) {
        return append(value.toString());
    }
    
    /**
     * Builds the HTTP body with the appended data.
     *
     * @return a new {@link HTTPBody} instance containing the appended data
     */
    public HTTPBody build() {
        return HTTPBody.of(bytes.toByteArray());
    }
}
