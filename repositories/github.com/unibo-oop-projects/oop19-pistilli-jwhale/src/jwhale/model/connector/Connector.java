package jwhale.model.connector;

import java.net.http.HttpResponse;

import jwhale.model.engine.Call;

/**
 * Connector interface. Represents the contract for functional 
 * entities that communicate with the API daemon.
 */
public interface Connector {
    /**
     * Check daemon availability.
     * @throws ConnectionException
     * @throws DaemonResponseException
     */
    void init() throws ConnectionException, DaemonResponseException;
    /**
     * Send a request to Docker daemon.
     * 
     * @param req - request to send.
     * @throws ConnectionException 
     */
    void sendRequest(Call req) throws ConnectionException;
    /**
     * Get response from Docker daemon.
     * @return body as string.
     */
    HttpResponse<String> getResponse();
    /**
     * Get daemon availability.
     * 
     * @return connection status, "true" if it's connected "else" otherwise.
     */
    boolean isAvailable();

}
