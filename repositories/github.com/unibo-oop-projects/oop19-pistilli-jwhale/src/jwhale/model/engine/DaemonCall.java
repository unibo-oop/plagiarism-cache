package jwhale.model.engine;

import java.util.Map;
import java.util.Optional;

/**
 * Call implementation according to the Builder pattern.
 */
public final class DaemonCall implements Call {

    private final Method httpMethod;
    private final Optional<Map<String, String>> queryParams;
    private final Optional<String> requestBody;
    private final Optional<String> pathParam;
    private final Optional<String> lastParam;
    private final EndPoint endPoint;

    private DaemonCall(final Method httpMethod, final Optional<Map<String, String>> queryParams, 
            final Optional<String> requestBody, final Optional<String> pathParam, 
            final Optional<String> lastParam, final EndPoint endPoint) {
        this.httpMethod = httpMethod;
        this.queryParams = queryParams;
        this.requestBody = requestBody;
        this.pathParam = pathParam;
        this.lastParam = lastParam;
        this.endPoint = endPoint;
    };


    @Override
    public Method getMethod() {
        return this.httpMethod;
    }

    @Override
    public Optional<Map<String, String>> getQueryParams() {
        return this.queryParams;
    }

    @Override
    public Optional<String> getPathParam() {
        return this.pathParam;
    }

    @Override
    public Optional<String> getRequestBody() {
        return this.requestBody;
    }

    @Override
    public EndPoint getEndPoint() {
        return this.endPoint;
    }
    @Override
    public Optional<String> getLastParam() {
        return this.lastParam;
    }


    public static class Builder {
        private final Method httpMethod;
        private Optional<Map<String, String>> queryParams = Optional.empty();
        private Optional<String> requestBody = Optional.empty();
        private Optional<String> pathParam = Optional.empty();
        private Optional<String> lastParam = Optional.empty();
        private final EndPoint endPoint;
        /**
         * Public constructor, entry point for call configuration.
         * @param httpMethod - HTTP method to use.
         * @param endPoint - Request destination.
         */
        public Builder(final Method httpMethod, final EndPoint endPoint) {
            this.httpMethod = httpMethod;
            this.endPoint = endPoint;
        }
        /**
         * Set query parameters.
         * @param queryParams
         * @return itself according to Builder pattern
         */
        public Builder setQueryParams(final Optional<Map<String, String>> queryParams) {
            this.queryParams = queryParams;
            return this;
        }
        /**
         * Set request body.
         * @param requestBody - Request body as string
         * @return itself according to Builder pattern
         */
        public Builder setRequestBody(final Optional<String> requestBody) {
            this.requestBody = requestBody;
            return this;
        }
        /**
         * Set path parameters.
         * @param pathParam - Path parameters as string
         * @return itself according to Builder pattern
         */
        public Builder setPathParam(final Optional<String> pathParam) {
            this.pathParam = pathParam;
            return this;
        }
        /**
         * Set path parameters.
         * @param lastParam - Path parameters as string
         * @return itself according to Builder pattern
         */
        public Builder setLastParam(final Optional<String> lastParam) {
            this.lastParam = lastParam;
            return this;
        }
        /**
         * Build a DaemonCall object.
         * @return Call for connector.
         */
        public DaemonCall build() {
            return new DaemonCall(this.httpMethod, this.queryParams, this.requestBody, 
                    this.pathParam, this.lastParam, this.endPoint);

        }
    }
}
