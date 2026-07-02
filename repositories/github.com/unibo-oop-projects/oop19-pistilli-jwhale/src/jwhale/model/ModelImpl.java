package jwhale.model;

import java.util.LinkedList;
import java.util.List;
import jwhale.model.connector.ConnectionException;
import jwhale.model.connector.DaemonResponseException;
/**
 * Model implementation.
 */
public class ModelImpl implements Model {
    private final List<Environment> envs = new LinkedList<>();

    @Override
    public final void createEnv(final String url, final String port, final String name) 
            throws ConnectionException, DaemonResponseException {
        envs.add(new Environment(url, port, name));
    }

    @Override
    public final void removeEnv(final String envName) {
        envs.remove(getEnv(envName));
    }

    @Override
    public final Environment getEnv(final String envName) {
        return envs.stream()
                .filter(e -> e.getEnvName().equals(envName))
                .findFirst()
                .orElse(null);
    }
}
