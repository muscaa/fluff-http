package fluff.http.response;

import java.net.URI;

import fluff.http.HTTP;
import fluff.http.body.HTTPBody;
import fluff.http.head.HTTPHead;
import fluff.http.request.HTTPRequest;

public class HTTPResponse {
	
	private final HTTP http;
	private final HTTPRequest request;
	private final URI uri;
	private final HTTPResponseStatus status;
	private final HTTPHead head;
	private final HTTPBody body;
	
	public HTTPResponse(HTTP http, HTTPRequest request, URI uri, HTTPResponseStatus status, HTTPHead head, HTTPBody body) {
		this.http = http;
		this.request = request;
		this.uri = uri;
		this.status = status;
		this.head = head;
		this.body = body;
	}
	
	public HTTP getHTTP() {
		return http;
	}
	
	public HTTPRequest getRequest() {
		return request;
	}
	
	public URI getURI() {
		return uri;
	}
	
	public HTTPResponseStatus getStatus() {
		return status;
	}
	
	public HTTPHead getHead() {
		return head;
	}
	
	public HTTPBody getBody() {
		return body;
	}
}
