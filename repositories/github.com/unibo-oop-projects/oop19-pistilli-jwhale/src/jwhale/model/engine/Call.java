package jwhale.model.engine;

import java.util.Map;
import java.util.Optional;

/**
 * Represents the contract for objects to send to Docker daemon.
 */
public interface Call {
    /**
     * Get request's HTTP method.
     * 
     * @return method as method enum type.
     */
    Method getMethod();
    /**
     * Get request's query parameters.
     * 
     * @return Bundle of query parameters.
     */
    Optional<Map<String, String>> getQueryParams();
    /**
     * Get request's path parameters.
     * 
     * @return all path parameters.
     */
    Optional<String> getPathParam();
    /**
     * Get request body.
     * 
     * @return request body.
     */
    Optional<String> getRequestBody();
    /**
     * Get last parameter.
     * 
     * @return last parameter.
     */
    Optional<String> getLastParam();
    /**
     * Get request's end point.
     * @return string representation of end point name.
     */
    EndPoint getEndPoint();
}
