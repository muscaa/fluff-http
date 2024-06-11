package fluff.http;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.time.Duration;

import fluff.http.request.HTTPRequest;
import fluff.http.request.HTTPRequestMethod;

public class HTTP {
	
	public static final HttpClient.Builder DEFAULT_CLIENT_BUILDER = HttpClient.newBuilder()
			.followRedirects(Redirect.NORMAL)
			.connectTimeout(Duration.ofSeconds(15))
			.cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL))
			;
	
	private final HttpClient client;
	
	public HTTP(HttpClient client) {
		this.client = client;
	}
	
	public HTTP() {
		this(DEFAULT_CLIENT_BUILDER.build());
	}
	
	public HTTPRequest GET(String url) {
		return new HTTPRequest(this, HTTPRequestMethod.GET, URI.create(url));
	}
	
	public HTTPRequest POST(String url) {
		return new HTTPRequest(this, HTTPRequestMethod.POST, URI.create(url));
	}
	
	public HTTPRequest PUT(String url) {
		return new HTTPRequest(this, HTTPRequestMethod.PUT, URI.create(url));
	}
	
	public HTTPRequest DELETE(String url) {
		return new HTTPRequest(this, HTTPRequestMethod.DELETE, URI.create(url));
	}
	
	public HTTPRequest PATCH(String url) {
		return new HTTPRequest(this, HTTPRequestMethod.PATCH, URI.create(url));
	}
	
	public HttpClient getClient() {
		return client;
	}
}
