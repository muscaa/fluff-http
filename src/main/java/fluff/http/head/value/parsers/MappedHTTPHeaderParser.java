package fluff.http.head.value.parsers;

import java.util.HashMap;
import java.util.Map;

import fluff.http.HTTPException;
import fluff.http.head.value.HTTPHeaderValue;
import fluff.http.head.value.HTTPHeaderParser;

public class MappedHTTPHeaderParser<V extends HTTPHeaderValue> implements HTTPHeaderParser<V> {
	
	private final Map<String, V> map = new HashMap<>();
	
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
