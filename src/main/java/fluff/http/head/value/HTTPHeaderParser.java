package fluff.http.head.value;

import fluff.http.HTTPException;

/**
 * Interface for parsing HTTP header values.
 *
 * @param <V> the type of the parsed value
 */
public interface HTTPHeaderParser<V> {
    
    /**
     * Parses the header value as a string.
     */
    HTTPHeaderParser<String> STRING = value -> value;
    
    /**
     * Parses the header value as an integer.
     */
    HTTPHeaderParser<Integer> INT = value -> Integer.parseInt(value);
    
    /**
     * Parses the header value as a float.
     */
    HTTPHeaderParser<Float> FLOAT = value -> Float.parseFloat(value);
    
    /**
     * Parses the header value as a long.
     */
    HTTPHeaderParser<Long> LONG = value -> Long.parseLong(value);
    
    /**
     * Parses the header value as a double.
     */
    HTTPHeaderParser<Double> DOUBLE = value -> Double.parseDouble(value);
    
    /**
     * Parses the given header value.
     *
     * @param value the header value to parse
     * @return the parsed value
     * @throws HTTPException if the header value cannot be parsed
     */
    V parse(String value) throws HTTPException;
}
