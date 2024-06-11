package fluff.http.request;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import fluff.http.HTTP;
import fluff.http.HTTPException;
import fluff.http.body.HTTPBody;
import fluff.http.body.HTTPBodyParser;
import fluff.http.head.HTTPHead;
import fluff.http.response.HTTPResponse;
import fluff.http.response.HTTPResponseStatus;

public class HTTPRequest {
	
	private final HTTP http;
	private final HTTPRequestMethod method;
	private final URI uri;
	
	protected HTTPHead head = HTTPHead.of();
	protected HTTPBody body = HTTPBody.of();
	
	public HTTPRequest(HTTP http, HTTPRequestMethod method, URI uri) {
		this.http = http;
		this.method = method;
		this.uri = uri;
	}
	
	public HttpRequest create() {
		HttpRequest.Builder request = HttpRequest.newBuilder()
					.uri(uri)
					;
		
		for (Map.Entry<String, List<String>> e : head.getHeaders().entrySet()) {
			for (String v : e.getValue()) {
				request.header(e.getKey(), v);
			}
		}
		
		byte[] bytes = body.get(HTTPBodyParser.BYTES);
		BodyPublisher bodyPublisher = bytes.length == 0 ? HttpRequest.BodyPublishers.noBody()
				: HttpRequest.BodyPublishers.ofByteArray(bytes);
		request.method(method.name(), bodyPublisher);
		
		return request.build();
	}
	
	public HTTPResponse send() throws HTTPException {
		try {
			HttpResponse<byte[]> response = http.getClient().send(create(), HttpResponse.BodyHandlers.ofByteArray());
			
			return new HTTPResponse(
					http,
					this,
					response.uri(),
					HTTPResponseStatus.byCode(response.statusCode()),
					HTTPHead.of(response.headers().map()),
					HTTPBody.of(response.body())
					);
		} catch (IOException | InterruptedException e) {
			throw new HTTPException(e);
		}
	}
	
	public HTTP getHTTP() {
		return http;
	}
	
	public HTTPRequestMethod getMethod() {
		return method;
	}
	
	public URI getURI() {
		return uri;
	}
	
	public HTTPHead getHead() {
		return head;
	}
	
	public HTTPRequest setHead(HTTPHead head) {
		this.head = head;
		return this;
	}
	
	public HTTPBody getBody() {
		return body;
	}
	
	public HTTPRequest setBody(HTTPBody body) {
		this.body = body;
		return this;
	}
}
