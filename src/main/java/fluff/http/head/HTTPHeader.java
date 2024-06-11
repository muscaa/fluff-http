package fluff.http.head;

import fluff.http.HTTPException;
import fluff.http.head.value.HTTPHeaderParser;
import fluff.http.head.value.HTTPHeaderValue;
import fluff.http.head.value.parsers.MappedHTTPHeaderParser;
import fluff.http.head.value.parsers.NullHTTPHeaderParser;
import fluff.http.head.value.values.MIMETypes;

public class HTTPHeader<V> {
	
	public static final HTTPHeader<String> USER_AGENT = of("User-Agent", HTTPHeaderParser.STRING);
	public static final HTTPHeader<String> AUTHORIZATION = of("Authorization", HTTPHeaderParser.STRING);
	public static final HTTPHeader<MIMETypes> ACCEPT = of("Accept", MIMETypes.values());
	public static final HTTPHeader<MIMETypes> CONTENT_TYPE = of("Content-Type", MIMETypes.values());
	
	private final String name;
	private final HTTPHeaderParser<V> parser;
	
	private HTTPHeader(String name, HTTPHeaderParser<V> parser) {
		this.name = name;
		this.parser = parser;
	}
	
	public String getName() {
		return name;
	}
	
	public String getString(V value) {
		return value instanceof HTTPHeaderValue v ? v.getHTTPHeaderValue() : String.valueOf(value);
	}
	
	public V getValue(String value) throws HTTPException {
		return parser.parse(value);
	}
	
	public static <V extends HTTPHeaderValue> HTTPHeader<V> of(String name, V... values) {
		return of(name, values.length == 0 ? new NullHTTPHeaderParser<>() : new MappedHTTPHeaderParser<>(values));
	}
	
	public static <V> HTTPHeader<V> of(String name, HTTPHeaderParser<V> parser) {
		return new HTTPHeader<>(name, parser);
	}
}
