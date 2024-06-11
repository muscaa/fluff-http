package fluff.http.head;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HTTPHeadBuilder {
	
	protected final Map<String, List<String>> headers = new HashMap<>();
	
	protected List<String> create(String header) {
		if (headers.containsKey(header)) return headers.get(header);
		
		List<String> list = new ArrayList<>();
		headers.put(header, list);
		return list;
	}
	
	public HTTPHeadBuilder add(String header, Object... values) {
		List<String> list = create(header);
		
		for (Object value : values) {
			list.add(String.valueOf(value));
		}
		return this;
	}
	
	public HTTPHeadBuilder add(HTTPHead head) {
		for (Map.Entry<String, List<String>> e : head.getHeaders().entrySet()) {
			List<String> list = create(e.getKey());
			
			list.addAll(e.getValue());
		}
		return this;
	}
	
	public <V> HTTPHeadBuilder add(HTTPHeader<V> header, V... values) {
		List<String> list = create(header.getName());
		
		for (V value : values) {
			list.add(header.getString(value));
		}
		return this;
	}
	
	public HTTPHeadBuilder remove(String header) {
		headers.remove(header);
		return this;
	}
	
	public HTTPHead build() {
		return HTTPHead.of(headers);
	}
}
