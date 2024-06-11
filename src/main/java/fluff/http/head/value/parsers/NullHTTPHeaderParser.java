package fluff.http.head.value.parsers;

import fluff.http.HTTPException;
import fluff.http.head.value.HTTPHeaderParser;

public class NullHTTPHeaderParser<V> implements HTTPHeaderParser<V> {
	
	@Override
	public V parse(String value) throws HTTPException {
		throw new HTTPException("Undefined header parser!");
	}
}
