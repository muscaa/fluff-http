package fluff.http.head;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HTTPHead {
	
	private final Map<String, List<String>> headers = new HashMap<>();
	
	private HTTPHead(Map<String, List<String>> headers) {
		this.headers.putAll(headers);
	}
	
	public List<String> getAll(String header) {
		return headers.get(header);
	}
	
	public String get(String header, int index) {
		List<String> list = headers.get(header);
		return list.size() > index ? list.get(index) : null;
	}
	
	public String get(String header) {
		return get(header, 0);
	}
	
	public <V> List<V> getAll(HTTPHeader<V> header) {
		List<V> list = new ArrayList<>();
		for (String v : headers.get(header.getName())) {
			list.add(header.getValue(v));
		}
		return list;
	}
	
	public <V> V get(HTTPHeader<V> header, int index) {
		return header.getValue(get(header.getName(), index));
	}
	
	public <V> V get(HTTPHeader<V> header) {
		return get(header, 0);
	}
	
	public Map<String, List<String>> getHeaders() {
		return headers;
	}
	
	public static HTTPHead of() {
		return new HTTPHead(Map.of());
	}
	
	public static HTTPHead of(Map<String, List<String>> headers) {
		return new HTTPHead(headers);
	}
	
	public static HTTPHeadBuilder builder() {
		return new HTTPHeadBuilder();
	}
}
