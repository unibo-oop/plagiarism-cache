package jwhale.model.connector;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.StringJoiner;
import jwhale.model.engine.Call;
import jwhale.model.engine.EndPoint;
/**
 * Connector for Docker daemons listening on TCP socket.
 *
 */
//Invoker
public class NetworkConnector implements Connector {
    /**
     * URL of Docker daemon TCP socket.
     */
    private final String url;
    /**
     * Port of Docker daemon TCP socket.
     */
    private final String port;
    /**
     * HTTP Client.
     */
    private final HttpClient httpClient;
    /**
     * Connection status.
     */
    private boolean available;
    /**
     * HTTP status code for successful connection.
     */
    private static final int OK_STATUS = 200;
    /**
     * Empty body as string.
     */
    private static final String EMPTY_BODY = "";
    /**
     * Timeout duration for HTTP client.
     */
    private static final long TIME = 5;
    private HttpResponse<String> response;
    /**
     * NetworkConnector constructor.
     * 
     * @param url - daemon TCP socket address
     * @param port - daemon TCP socket port
     */
    public NetworkConnector(final String url, final String port) {
        this.url = url;
        this.port = port;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(TIME))
                .build();
        this.available = false;
    }

    @Override
    public final void init() throws ConnectionException, DaemonResponseException {
        final HttpRequest request = HttpRequest.newBuilder()
                .setHeader("User-Agent", "JWhale")
                .uri(URI.create(addressBuild() + EndPoint.VERSION.getURI()))
                .build();
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            available = (response.statusCode() == OK_STATUS) ? true : false;
            if (!available) {
                throw new DaemonResponseException(String.valueOf(response.statusCode()));
            }
        } catch (IOException | InterruptedException e) {
            throw new ConnectionException();
        }
    }
    @Override
    public final void sendRequest(final Call req) throws ConnectionException {
        final HttpRequest request = HttpRequest.newBuilder()
                .setHeader("User-Agent", "JWhale")
                .method(req.getMethod().name(), 
                        HttpRequest.BodyPublishers.ofString(this.getBody(req)))
                .uri(URI.create(getURI(req)))
                .setHeader("Content-Type", "application/json")
                .build();
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ConnectionException();
        }
    }
    @Override
    public final HttpResponse<String> getResponse() {
        return this.response;
    }
    @Override
    public final boolean isAvailable() {
        return this.available;
    }
    /*
     * Used in request creation process for URL format.
     */
    private String addressBuild() {
        return this.url + ":" + this.port;
    }
    /*
     * Used in request creation process for body format
     */
    private String getBody(final Call req) {
        if (req.getRequestBody().isEmpty()) {
            return EMPTY_BODY;
        } else {
            return req.getRequestBody().get();
        }
    }
    /*
     * Used to append parameters with proper separator and path parameter.
     */
    private String getURI(final Call req) {
        final StringBuilder str = new StringBuilder();
        //Joiner for query parameters.
        final StringJoiner joiner = new StringJoiner("&");
        //Base string : address + port.
        str.append(addressBuild());
        //Append end point
        str.append(req.getEndPoint().getURI());
        //Append optional parameters (path, query, last).
        req.getPathParam().ifPresent(x -> str.append(x));
        req.getLastParam().ifPresent(x -> str.append(x + "?"));
        req.getQueryParams().ifPresent(x -> {
            req.getQueryParams().get().entrySet().stream().forEach(e -> {
                joiner.add(e.getKey() + "=" + e.getValue());
            });
            //Append query parameters, opportunely separated.
            str.append(joiner.toString());
        });
        return str.toString();
    }
}


