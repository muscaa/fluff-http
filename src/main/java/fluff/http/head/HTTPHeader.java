package fluff.http.head;

import fluff.http.HTTPException;
import fluff.http.head.value.HTTPHeaderParser;
import fluff.http.head.value.HTTPHeaderValue;
import fluff.http.head.value.parsers.MappedHTTPHeaderParser;
import fluff.http.head.value.parsers.NullHTTPHeaderParser;
import fluff.http.head.value.values.MIMETypes;

/**
 * Represents an HTTP header with a specific name and value parser.
 *
 * @param <V> the type of the value that this header holds
 */
public class HTTPHeader<V> {
    
    /**
     * The User-Agent header.
     */
    public static final HTTPHeader<String> USER_AGENT = of("User-Agent", HTTPHeaderParser.STRING);
    
    /**
     * The Authorization header.
     */
    public static final HTTPHeader<String> AUTHORIZATION = of("Authorization", HTTPHeaderParser.STRING);
    
    /**
     * The Accept header.
     */
    public static final HTTPHeader<MIMETypes> ACCEPT = of("Accept", MIMETypes.values());
    
    /**
     * The Content-Type header.
     */
    public static final HTTPHeader<MIMETypes> CONTENT_TYPE = of("Content-Type", MIMETypes.values());
    
    private final String name;
    private final HTTPHeaderParser<V> parser;
    
    private HTTPHeader(String name, HTTPHeaderParser<V> parser) {
        this.name = name;
        this.parser = parser;
    }
    
    /**
     * Returns the name of the header.
     *
     * @return the name of the header
     */
    public String getName() {
        return name;
    }
    
    /**
     * Converts the given value to a string representation suitable for HTTP headers.
     *
     * @param value the value to convert
     * @return the string representation of the value
     */
    public String getString(V value) {
        return value instanceof HTTPHeaderValue v ? v.getHTTPHeaderValue() : String.valueOf(value);
    }
    
    /**
     * Parses the given string value into the header's value type.
     *
     * @param value the string value to parse
     * @return the parsed value
     * @throws HTTPException if the value cannot be parsed
     */
    public V getValue(String value) throws HTTPException {
        return parser.parse(value);
    }
    
    /**
     * Creates a new HTTPHeader with the given name and a mapped parser for the specified values.
     *
     * @param <V> the type of the header value
     * @param name the name of the header
     * @param values the possible values for the header
     * @return a new HTTPHeader instance
     */
    public static <V extends HTTPHeaderValue> HTTPHeader<V> of(String name, V... values) {
        return of(name, values.length == 0 ? new NullHTTPHeaderParser<>() : new MappedHTTPHeaderParser<>(values));
    }
    
    /**
     * Creates a new HTTPHeader with the given name and parser.
     *
     * @param <V> the type of the header value
     * @param name the name of the header
     * @param parser the parser for the header value
     * @return a new HTTPHeader instance
     */
    public static <V> HTTPHeader<V> of(String name, HTTPHeaderParser<V> parser) {
        return new HTTPHeader<>(name, parser);
    }
}
