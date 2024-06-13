package fluff.http.response;

import java.net.URI;

import fluff.http.HTTP;
import fluff.http.body.HTTPBody;
import fluff.http.head.HTTPHead;
import fluff.http.request.HTTPRequest;

/**
 * Represents an HTTP response received after sending an HTTP request.
 */
public class HTTPResponse {
    
    private final HTTP http;
    private final HTTPRequest request;
    private final URI uri;
    private final HTTPResponseStatus status;
    private final HTTPHead head;
    private final HTTPBody body;
    
    /**
     * Constructs an HTTPResponse instance with the specified details.
     *
     * @param http the HTTP client instance used for the request
     * @param request the HTTP request that generated this response
     * @param uri the URI of the request
     * @param status the HTTP response status
     * @param head the HTTP headers received in the response
     * @param body the HTTP body received in the response
     */
    public HTTPResponse(HTTP http, HTTPRequest request, URI uri, HTTPResponseStatus status, HTTPHead head, HTTPBody body) {
        this.http = http;
        this.request = request;
        this.uri = uri;
        this.status = status;
        this.head = head;
        this.body = body;
    }
    
    /**
     * Returns the HTTP client instance used for the request.
     *
     * @return the HTTP client instance
     */
    public HTTP getHTTP() {
        return http;
    }
    
    /**
     * Returns the HTTP request that generated this response.
     *
     * @return the HTTP request
     */
    public HTTPRequest getRequest() {
        return request;
    }
    
    /**
     * Returns the URI of the request.
     *
     * @return the URI
     */
    public URI getURI() {
        return uri;
    }
    
    /**
     * Returns the HTTP response status.
     *
     * @return the HTTP response status
     */
    public HTTPResponseStatus getStatus() {
        return status;
    }
    
    /**
     * Returns the HTTP headers received in the response.
     *
     * @return the HTTP headers
     */
    public HTTPHead getHead() {
        return head;
    }
    
    /**
     * Returns the HTTP body received in the response.
     *
     * @return the HTTP body
     */
    public HTTPBody getBody() {
        return body;
    }
}
