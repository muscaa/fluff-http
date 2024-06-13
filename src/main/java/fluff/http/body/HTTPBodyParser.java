package fluff.http.body;

import fluff.http.HTTPException;
import fluff.json.JSON;
import fluff.json.JSONArray;
import fluff.json.JSONObject;

/**
 * Interface representing a parser for HTTP body content.
 *
 * @param <V> the type of the parsed result
 */
public interface HTTPBodyParser<V> {
	
    /**
     * Parser that returns the body content as a byte array.
     */
    HTTPBodyParser<byte[]> BYTES = bytes -> bytes;
    
    /**
     * Parser that returns the body content as a string.
     */
    HTTPBodyParser<String> STRING = bytes -> new String(bytes);
    
    /**
     * Parser that returns the body content as a {@link JSONObject}.
     */
    HTTPBodyParser<JSONObject> JSON_OBJECT = bytes -> JSON.object(STRING.parse(bytes));
    
    /**
     * Parser that returns the body content as a {@link JSONArray}.
     */
    HTTPBodyParser<JSONArray> JSON_ARRAY = bytes -> JSON.array(STRING.parse(bytes));
    
    /**
     * Parses the given byte array into an instance of type {@code V}.
     *
     * @param bytes the byte array to parse
     * @return the parsed result
     * @throws HTTPException if an error occurs during parsing
     */
    V parse(byte[] bytes) throws HTTPException;
}
