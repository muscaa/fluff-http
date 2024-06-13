package fluff.http.head;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A builder class for constructing {@link HTTPHead} instances.
 */
public class HTTPHeadBuilder {
    
    protected final Map<String, List<String>> headers = new HashMap<>();
    
    /**
     * Retrieves the list of values for a given header, or creates a new list if none exists.
     *
     * @param header the name of the header
     * @return the list of values associated with the header
     */
    protected List<String> getOrCreate(String header) {
        if (headers.containsKey(header)) return headers.get(header);
        
        List<String> list = new ArrayList<>();
        headers.put(header, list);
        return list;
    }
    
    /**
     * Adds values to the specified header.
     *
     * @param header the name of the header
     * @param values the values to add to the header
     * @return the current HTTPHeadBuilder instance
     */
    public HTTPHeadBuilder add(String header, Object... values) {
        List<String> list = getOrCreate(header);
        
        for (Object value : values) {
            list.add(String.valueOf(value));
        }
        return this;
    }
    
    /**
     * Adds all headers and their values from the specified HTTPHead instance.
     *
     * @param head the HTTPHead instance to add headers from
     * @return the current HTTPHeadBuilder instance
     */
    public HTTPHeadBuilder add(HTTPHead head) {
        for (Map.Entry<String, List<String>> e : head.getHeaders().entrySet()) {
            List<String> list = getOrCreate(e.getKey());
            
            list.addAll(e.getValue());
        }
        return this;
    }
    
    /**
     * Adds values to the specified HTTPHeader.
     *
     * @param <V> the type of the values
     * @param header the HTTPHeader instance
     * @param values the values to add to the header
     * @return the current HTTPHeadBuilder instance
     */
    public <V> HTTPHeadBuilder add(HTTPHeader<V> header, V... values) {
        List<String> list = getOrCreate(header.getName());
        
        for (V value : values) {
            list.add(header.getString(value));
        }
        return this;
    }
    
    /**
     * Removes the specified header.
     *
     * @param header the name of the header to remove
     * @return the current HTTPHeadBuilder instance
     */
    public HTTPHeadBuilder remove(String header) {
        headers.remove(header);
        return this;
    }
    
    /**
     * Builds an HTTPHead instance with the current headers.
     *
     * @return a new HTTPHead instance
     */
    public HTTPHead build() {
        return HTTPHead.of(headers);
    }
}
