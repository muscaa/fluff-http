package fluff.http.head.value.parsers;

import java.util.HashMap;
import java.util.Map;

import fluff.http.HTTPException;
import fluff.http.head.value.HTTPHeaderValue;
import fluff.http.head.value.HTTPHeaderParser;

/**
 * A parser that maps HTTP header values to a specific type of {@link HTTPHeaderValue}.
 *
 * @param <V> the type of {@link HTTPHeaderValue} this parser handles
 */
public class MappedHTTPHeaderParser<V extends HTTPHeaderValue> implements HTTPHeaderParser<V> {
	
	private final Map<String, V> map = new HashMap<>();
	
	/**
	 * Constructs a new MappedHTTPHeaderParser with the specified array of values.
	 *
	 * @param values the array of values to be mapped
	 */
	public MappedHTTPHeaderParser(V[] values) {
		for (V v : values) {
			map.put(v.getHTTPHeaderValue(), v);
		}
	}
	
	@Override
	public V parse(String value) throws HTTPException {
		return map.get(value);
	}
}
