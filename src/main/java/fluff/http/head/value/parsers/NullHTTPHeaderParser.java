package fluff.http.head.value.parsers;

import fluff.http.HTTPException;
import fluff.http.head.value.HTTPHeaderParser;

/**
 * A parser that always throws an exception indicating an undefined header parser.
 *
 * @param <V> the type of value this parser would parse
 */
public class NullHTTPHeaderParser<V> implements HTTPHeaderParser<V> {
    
    @Override
    public V parse(String value) throws HTTPException {
        throw new HTTPException("Null header parser!");
    }
}
