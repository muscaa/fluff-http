package fluff.http;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.time.Duration;

import fluff.http.path.URLPath;
import fluff.http.request.HTTPRequest;
import fluff.http.request.HTTPRequestMethod;

/**
 * Provides an HTTP client for making HTTP requests.
 */
public class HTTP {
    
    /**
     * The default HttpClient.Builder configuration.
     */
    public static final HttpClient.Builder DEFAULT_CLIENT_BUILDER = HttpClient.newBuilder()
            .followRedirects(Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(15))
            .cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
    
    private final HttpClient client;
    
    /**
     * Constructs an HTTP instance with the specified HttpClient.
     *
     * @param client the HttpClient to use
     */
    public HTTP(HttpClient client) {
        this.client = client;
    }
    
    /**
     * Constructs an HTTP instance with the default HttpClient configuration.
     */
    public HTTP() {
        this(DEFAULT_CLIENT_BUILDER.build());
    }
    
    /**
     * Creates a new GET request for the specified URL.
     *
     * @param url the URL to send the GET request to
     * @return a new HTTPRequest instance for the GET request
     */
    public HTTPRequest GET(String url) {
        return new HTTPRequest(this, HTTPRequestMethod.GET, URI.create(url));
    }
    
    /**
     * Creates a new GET request for the specified URLPath.
     *
     * @param path the URLPath to send the GET request to
     * @return a new HTTPRequest instance for the GET request
     */
    public HTTPRequest GET(URLPath path) {
        return GET(path.getPath());
    }
    
    /**
     * Creates a new POST request for the specified URL.
     *
     * @param url the URL to send the POST request to
     * @return a new HTTPRequest instance for the POST request
     */
    public HTTPRequest POST(String url) {
        return new HTTPRequest(this, HTTPRequestMethod.POST, URI.create(url));
    }
    
    /**
     * Creates a new POST request for the specified URLPath.
     *
     * @param path the URLPath to send the POST request to
     * @return a new HTTPRequest instance for the POST request
     */
    public HTTPRequest POST(URLPath path) {
        return POST(path.getPath());
    }
    
    /**
     * Creates a new PUT request for the specified URL.
     *
     * @param url the URL to send the PUT request to
     * @return a new HTTPRequest instance for the PUT request
     */
    public HTTPRequest PUT(String url) {
        return new HTTPRequest(this, HTTPRequestMethod.PUT, URI.create(url));
    }
    
    /**
     * Creates a new PUT request for the specified URLPath.
     *
     * @param path the URLPath to send the PUT request to
     * @return a new HTTPRequest instance for the PUT request
     */
    public HTTPRequest PUT(URLPath path) {
        return PUT(path.getPath());
    }
    
    /**
     * Creates a new DELETE request for the specified URL.
     *
     * @param url the URL to send the DELETE request to
     * @return a new HTTPRequest instance for the DELETE request
     */
    public HTTPRequest DELETE(String url) {
        return new HTTPRequest(this, HTTPRequestMethod.DELETE, URI.create(url));
    }
    
    /**
     * Creates a new DELETE request for the specified URLPath.
     *
     * @param path the URLPath to send the DELETE request to
     * @return a new HTTPRequest instance for the DELETE request
     */
    public HTTPRequest DELETE(URLPath path) {
        return DELETE(path.getPath());
    }
    
    /**
     * Creates a new PATCH request for the specified URL.
     *
     * @param url the URL to send the PATCH request to
     * @return a new HTTPRequest instance for the PATCH request
     */
    public HTTPRequest PATCH(String url) {
        return new HTTPRequest(this, HTTPRequestMethod.PATCH, URI.create(url));
    }
    
    /**
     * Creates a new PATCH request for the specified URLPath.
     *
     * @param path the URLPath to send the PATCH request to
     * @return a new HTTPRequest instance for the PATCH request
     */
    public HTTPRequest PATCH(URLPath path) {
        return PATCH(path.getPath());
    }
    
    /**
     * Returns the HttpClient used by this HTTP instance.
     *
     * @return the HttpClient
     */
    public HttpClient getClient() {
        return client;
    }
}
