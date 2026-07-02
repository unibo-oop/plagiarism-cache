package todo.launcher;

import java.util.Map;

/**
 * LauncherOptions is a singleton that exposes the configuration the launcher
 * passed to the game. It's supposed to be used by the game.
 */
public class LauncherOptions {
    protected static final String VAR_DEBUG = "TODO_DEBUG";
    private final boolean debugModeEnabled;

    private LauncherOptions() {
        final Map<String, String> env = System.getenv();
        this.debugModeEnabled = getEnv(env, VAR_DEBUG);
    }

    private boolean getEnv(final Map<String, String> env, final String variable) {
        return env.containsKey(variable) && env.get(variable).equals("1");
    }

    /**
     * Debug mode can be enabled by creating a .todo-debug file in the directory
     * where the game is launched, or by setting the TODO_DEBUG environment variable
     * to 1.
     *
     * @return true if debug mode is enabled
     */
    public boolean isDebugModeEnabled() {
        return this.debugModeEnabled;
    }

    /**
     * Get the instance of this singleton.
     *
     * @return the global instance of LauncherOptions
     */
    public static LauncherOptions getInstance() {
        return SingletonHolder.OPTIONS;
    }

    private static class SingletonHolder {
        private static final LauncherOptions OPTIONS = new LauncherOptions();
    }
}
