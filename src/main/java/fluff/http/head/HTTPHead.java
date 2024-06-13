package fluff.http.head;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fluff.http.HTTPException;

/**
 * Represents the headers of an HTTP request or response.
 */
public class HTTPHead {
    
    private final Map<String, List<String>> headers = new HashMap<>();
    
    private HTTPHead(Map<String, List<String>> headers) {
        this.headers.putAll(headers);
    }
    
    /**
     * Retrieves all values for a given header.
     *
     * @param header the name of the header
     * @return a list of all values associated with the header, or null if the header is not present
     */
    public List<String> getAll(String header) {
        return headers.get(header);
    }
    
    /**
     * Retrieves the value of a header at a specific index.
     *
     * @param header the name of the header
     * @param index the index of the value to retrieve
     * @return the value at the specified index, or null if the index is out of bounds
     */
    public String get(String header, int index) {
        List<String> list = headers.get(header);
        return list != null && list.size() > index ? list.get(index) : null;
    }
    
    /**
     * Retrieves the first value of a header.
     *
     * @param header the name of the header
     * @return the first value of the header, or null if the header is not present
     */
    public String get(String header) {
        return get(header, 0);
    }
    
    /**
     * Retrieves all values for a given header, parsed by the provided HTTPHeader.
     *
     * @param <V> the type of the value
     * @param header the HTTPHeader object used to parse the header values
     * @return a list of all parsed values associated with the header
     * @throws HTTPException if the values cannot be parsed
     */
    public <V> List<V> getAll(HTTPHeader<V> header) throws HTTPException {
        List<V> list = new ArrayList<>();
        for (String v : headers.get(header.getName())) {
            list.add(header.getValue(v));
        }
        return list;
    }
    
    /**
     * Retrieves the value of a header at a specific index, parsed by the provided HTTPHeader.
     *
     * @param <V> the type of the value
     * @param header the HTTPHeader object used to parse the header values
     * @param index the index of the value to retrieve
     * @return the parsed value at the specified index, or null if the index is out of bounds
     * @throws HTTPException if the value cannot be parsed
     */
    public <V> V get(HTTPHeader<V> header, int index) throws HTTPException {
        return header.getValue(get(header.getName(), index));
    }
    
    /**
     * Retrieves the first value of a header, parsed by the provided HTTPHeader.
     *
     * @param <V> the type of the value
     * @param header the HTTPHeader object used to parse the header values
     * @return the first parsed value of the header, or null if the header is not present
     * @throws HTTPException if the value cannot be parsed
     */
    public <V> V get(HTTPHeader<V> header) throws HTTPException {
        return get(header, 0);
    }
    
    /**
     * Retrieves all headers as a map.
     *
     * @return a map of all headers and their values
     */
    public Map<String, List<String>> getHeaders() {
        return headers;
    }
    
    /**
     * Creates an empty HTTPHead object.
     *
     * @return an empty HTTPHead object
     */
    public static HTTPHead of() {
        return new HTTPHead(Map.of());
    }
    
    /**
     * Creates an HTTPHead object with the specified headers.
     *
     * @param headers a map of headers and their values
     * @return an HTTPHead object with the specified headers
     */
    public static HTTPHead of(Map<String, List<String>> headers) {
        return new HTTPHead(headers);
    }
    
    /**
     * Creates a new HTTPHeadBuilder.
     *
     * @return a new HTTPHeadBuilder
     */
    public static HTTPHeadBuilder builder() {
        return new HTTPHeadBuilder();
    }
}
