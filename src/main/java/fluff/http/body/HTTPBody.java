package fluff.http.body;

public class HTTPBody {
	
	private final byte[] bytes;
	
	private HTTPBody(byte[] bytes) {
		this.bytes = bytes;
	}
	
	public <V> V get(HTTPBodyParser<V> parser) {
		return parser.parse(bytes);
	}
	
	public static HTTPBody of() {
		return new HTTPBody(new byte[0]);
	}
	
	public static HTTPBody of(byte[] bytes) {
		return new HTTPBody(bytes);
	}
	
	public static HTTPBodyBuilder builder() {
		return new HTTPBodyBuilder();
	}
}
