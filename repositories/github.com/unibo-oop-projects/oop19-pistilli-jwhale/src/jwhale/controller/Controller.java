package jwhale.controller;

import java.io.IOException;
import java.util.List;
import jwhale.model.connector.ConnectionException;
import jwhale.model.connector.DaemonResponseException;
/**
 * System controller. Deals with the management of all the controllers related 
 * to the various environments.
 *
 */
public interface Controller  {
    /**
     * Get environment list as EnvPreView, useful for view purposes.
     * @return
     *          Environments list.
     */
    List<EnvPreView> getEnvsList();
    /**
     * Get specific environment controller.
     * @param envName
     *           Environment name.
     * @return
     *          environment controller.
     */
    EnvController getEnvController(String envName);
    /**
     * Load environment from remote source and pass it to the model.
     * @param url
     *          source address.
     * @param port
     *          TCP port on which the Docker daemon listens.
     * @param envName
     *          environment name.
     * @throws ConnectionException
     *          source is unavailable.
     * @throws DaemonResponseException
     *          the daemon does not respond as expected
     * @throws IOException
     *          unable to update the file that tracks the environments
     */
    void loadEnv(String url, String port, String envName) 
            throws ConnectionException, DaemonResponseException, IOException;
    /**
     * Remove env from model and local file.
     * @param envName 
     *          environment to remove.
     * @throws IOException
     *          unable to update the file that tracks the environments
     */
    void removeEnv(String envName) throws IOException;
    /**
     * Keep track of current environment managed. Useful for get environment 
     * controller.
     * @return
     *          current environment name.
     */
    String getCurrentEnvName();
    /**
     * Set current environment managed.
     * @param name
     *          environment to manage.
     */
    void setCurrentEnvName(String name);
}
