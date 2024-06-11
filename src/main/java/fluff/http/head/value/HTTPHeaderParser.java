package fluff.http.head.value;

import fluff.http.HTTPException;

public interface HTTPHeaderParser<V> {
	
	HTTPHeaderParser<String> STRING = value -> value;
	HTTPHeaderParser<Integer> INT = value -> Integer.parseInt(value);
	HTTPHeaderParser<Float> FLOAT = value -> Float.parseFloat(value);
	HTTPHeaderParser<Long> LONG = value -> Long.parseLong(value);
	HTTPHeaderParser<Double> DOUBLE = value -> Double.parseDouble(value);
	
	V parse(String value) throws HTTPException;
}
