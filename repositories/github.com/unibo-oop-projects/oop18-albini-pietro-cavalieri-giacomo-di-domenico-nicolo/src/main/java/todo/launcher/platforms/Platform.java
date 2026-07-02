package todo.launcher.platforms;

import java.util.List;

/**
 * This interface represents a platform supported by the launcher.
 */
public interface Platform {
    /**
     * @return the path of the JVM used to start the launcher.
     */
    String getJvmExecutable();

    /**
     * @return if this platform is the one running the launcher.
     */
    boolean isCurrent();

    /**
     * @return the command line flags specific to this platform.
     */
    List<String> getCommandLineFlags();
}
