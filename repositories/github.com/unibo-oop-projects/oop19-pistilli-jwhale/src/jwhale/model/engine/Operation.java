package jwhale.model.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
/**
 * Represents a configurable operation.
 *
 */
public class Operation implements Executable {
    private final Method method;
    private final EndPoint endPoint;
    private Optional<Map<String, String>> queryParams = Optional.empty();
    private Optional<String> pathParam = Optional.empty();
    private Optional<String> requestBody = Optional.empty();
    private Optional<String> lastParam = Optional.empty();
    /**
     * Public constructor.
     * @param method - HTTP method for operation.
     * @param endPoint - End point for operation.
     */
    public Operation(final Method method, final EndPoint endPoint) {
        this.method = method;
        this.endPoint = endPoint;
    }
    /**
     * Get HTTP Method.
     * @return HTTP method
     */
    public final Method getMethod() {
        return method;
    }
    /**
     * Get operation end point.
     * @return Operation end point
     */
    public final EndPoint getEndPoint() {
        return endPoint;
    }
    /**
     * Get query parameters if present.
     * @return query parameters
     */
    public final Optional<Map<String, String>> getQueryParams() {
        return queryParams;
    }
    /**
     * Get path parameter, if present.
     * @return path parameters
     */
    public final Optional<String> getPathParam() {
        return pathParam;
    }
    /**
     * Get request body, if present.
     * @return request body
     */
    public final Optional<String> getRequestBody() {
        return requestBody;
    }
    /**
     * Get last parameter, if present.
     * @return last parameter
     */
    public final Optional<String> getLastParam() {
        return lastParam;
    }
    /** 
     * Sets query parameter. If key has already a value, it update it.
     * @param var is the parameters name.
     * @param value is the parameters value.
     */
    public final void setQueryParams(final String var, final String value) {
        queryParams.ifPresentOrElse(e -> e.merge(var, value, (x, y) -> value), 
                () -> {
                    queryParams = Optional.of(new HashMap<>());
                    queryParams.get().put(var, value);
                });
    }
    /**
     * Sets path parameter.
     * @param param as string.
     */
    public final void setPathParam(final String param) {
        this.pathParam = Optional.of(param);
    }
    /**
     * Sets request body.
     * @param body string for request body.
     */
    public final void setBodyRequest(final String body) {
        this.requestBody = Optional.of(body);
    }
    /**
     * Sets last URI parameter.
     * @param last - last URI parameter.
     */
    public final void setLastParam(final String last) {
        this.lastParam = Optional.of(last);
    }
    @Override
    public final Call buildCall() {
        return new DaemonCall.Builder(method, endPoint)
                .setQueryParams(queryParams)
                .setPathParam(pathParam)
                .setLastParam(lastParam)
                .setRequestBody(requestBody)
                .build();
    }
}
