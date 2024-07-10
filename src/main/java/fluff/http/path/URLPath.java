package fluff.http.path;

import java.util.LinkedList;
import java.util.List;

/**
 * A class representing a URL path with methods to manipulate and derive paths.
 */
public class URLPath {
    
    private final String protocol;
    private final List<String> components;
    private final String path;
    
    /**
     * Constructs a new URLPath.
     *
     * @param protocol the protocol of the URL (e.g., http, https)
     * @param components the components of the URL path
     * @param directory flag indicating if the path is a directory
     */
    private URLPath(String protocol, List<String> components, boolean directory) {
        this.protocol = protocol;
        this.components = components;
        this.path = new StringBuilder()
                .append(protocol)
                .append("://")
                .append(String.join("/", components))
                .append(directory || components.size() == 1 ? "/" : "")
                .toString();
    }
    
    /**
     * Derives a new URLPath by appending the given path to the current path.
     *
     * @param path the path to append
     * @return the derived URLPath
     */
    public URLPath derive(String path) {
        List<String> childComponents = getComponents(path);
        childComponents.addAll(0, components);
        
        return new URLPath(protocol, childComponents, path.endsWith("/"));
    }
    
    /**
     * Returns the parent URLPath of the current path.
     *
     * @return the parent URLPath, or null if this path has no parent
     */
    public URLPath parent() {
        int size = components.size();
        if (size == 1) return null;
        
        List<String> parentComponents = new LinkedList<>(components);
        parentComponents.remove(size - 1);
        
        return new URLPath(protocol, parentComponents, true);
    }
    
    /**
     * Returns the full URL path as a string.
     *
     * @return the full URL path
     */
    public String getPath() {
        return path;
    }
    
    /**
     * Returns the string representation of the URL path.
     *
     * @return the string representation of the URL path
     */
    @Override
    public String toString() {
        return getPath();
    }
    
    /**
     * Creates a URLPath from a string representation of a URL.
     *
     * @param path the string representation of the URL
     * @return the created URLPath
     * @throws RuntimeException if the URL is invalid
     */
    public static URLPath of(String path) {
        String[] split = path.split("://");
        if (split.length != 2) throw new RuntimeException("Invalid URL path!");
        
        return new URLPath(split[0], getComponents(split[1]), path.endsWith("/"));
    }
    
    /**
     * Splits the given path into components.
     *
     * @param path the path to split
     * @return a list of components
     */
    private static List<String> getComponents(String path) {
        List<String> list = new LinkedList<>();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            
            if (c != '/') {
                sb.append(c);
                continue;
            }
            
            if (sb.length() > 0) {
                list.add(sb.toString());
                sb.setLength(0);
            }
        }
        
        if (sb.length() > 0) {
            list.add(sb.toString());
            sb.setLength(0);
        }
        
        return list;
    }
}
