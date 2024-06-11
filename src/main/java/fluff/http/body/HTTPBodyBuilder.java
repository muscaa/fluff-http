package fluff.http.body;

import java.io.ByteArrayOutputStream;

import fluff.json.JSON;

public class HTTPBodyBuilder {
	
	protected final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	
	public HTTPBodyBuilder append(byte[] value, int off, int len) {
		bytes.write(value, off, len);
		return this;
	}
	
	public HTTPBodyBuilder append(byte[] value) {
		return append(value, 0, value.length);
	}
	
	public HTTPBodyBuilder append(String value) {
		return append(value.getBytes());
	}
	
	public HTTPBodyBuilder append(JSON value) {
		return append(value.toString());
	}
	
	public HTTPBody build() {
		return HTTPBody.of(bytes.toByteArray());
	}
}
