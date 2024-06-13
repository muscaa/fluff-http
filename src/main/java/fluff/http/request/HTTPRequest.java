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

/**
 * Represents an HTTP request.
 */
public class HTTPRequest {
    
    private final HTTP http;
    private final HTTPRequestMethod method;
    private final URI uri;
    
    protected HTTPHead head = HTTPHead.of();
    protected HTTPBody body = HTTPBody.of();
    
    /**
     * Constructs a new HTTPRequest with the specified HTTP instance, method, and URI.
     *
     * @param http the HTTP instance
     * @param method the HTTP request method
     * @param uri the URI of the request
     */
    public HTTPRequest(HTTP http, HTTPRequestMethod method, URI uri) {
        this.http = http;
        this.method = method;
        this.uri = uri;
    }
    
    /**
     * Creates the HttpRequest object.
     *
     * @return the built HttpRequest object
     */
    protected HttpRequest create() {
        HttpRequest.Builder request = HttpRequest.newBuilder()
                    .uri(uri);
        
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
    
    /**
     * Sends the HTTP request and returns the response.
     *
     * @return the HTTP response
     * @throws HTTPException if an error occurs while sending the request
     */
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
    
    /**
     * Returns the HTTP instance associated with this request.
     *
     * @return the HTTP instance
     */
    public HTTP getHTTP() {
        return http;
    }
    
    /**
     * Returns the HTTP request method.
     *
     * @return the HTTP request method
     */
    public HTTPRequestMethod getMethod() {
        return method;
    }
    
    /**
     * Returns the URI of the request.
     *
     * @return the URI of the request
     */
    public URI getURI() {
        return uri;
    }
    
    /**
     * Returns the HTTP headers of the request.
     *
     * @return the HTTP headers
     */
    public HTTPHead getHead() {
        return head;
    }
    
    /**
     * Sets the HTTP headers of the request.
     *
     * @param head the HTTP headers
     * @return this HTTPRequest instance
     */
    public HTTPRequest setHead(HTTPHead head) {
        this.head = head;
        return this;
    }
    
    /**
     * Returns the HTTP body of the request.
     *
     * @return the HTTP body
     */
    public HTTPBody getBody() {
        return body;
    }
    
    /**
     * Sets the HTTP body of the request.
     *
     * @param body the HTTP body
     * @return this HTTPRequest instance
     */
    public HTTPRequest setBody(HTTPBody body) {
        this.body = body;
        return this;
    }
}
