package jwhale.controller;
/**
 * Helper class to wrap environments representation. It'useful for fetch
 * container information.
 */
public class EnvPreView {
    private final String url;
    private final String port;
    private final String name;
    /**
     * Simple public constructor.
     * @param url
     *          environment address.
     * @param port
     *          daemon TCP port.
     * @param name
     *          environment name.
     */
    public EnvPreView(final String url, final String port, final String name) {
        this.url = url;
        this.port = port;
        this.name = name;
    }
    /**
     * Get environment address.
     * @return
     *          url string.
     */
    public final String getUrl() {
        return url;
    }
    /**
     * Get environment port.
     * @return
     *          environment port.
     */
    public final String getPort() {
        return port;
    }
    /**
     * Get environment name.
     * @return
     *          environment name.
     */
    public final String getName() {
        return name; 
    }

}
