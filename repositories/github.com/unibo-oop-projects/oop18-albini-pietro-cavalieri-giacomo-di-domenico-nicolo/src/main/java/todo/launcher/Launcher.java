package todo.launcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import todo.launcher.platforms.Platform;
import todo.launcher.platforms.PlatformRegistry;

/**
 * The launcher is a wrapper program that detects the system running the program
 * and starts the game with the correct flags specific for that platform.
 */
public final class Launcher {
    private static final String TO_LAUNCH = "todo.App";
    private static final String DEBUG_FILE_NAME = ".todo-debug";

    private Launcher() {
    }

    public static void main(final String[] args) {
        int exitValue;
        try {
            final Process process = buildProcess().start();
            while (true) {
                try {
                    exitValue = process.waitFor();
                    break;
                } catch (final InterruptedException e) {
                    continue;
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
            exitValue = 1;
        }
        System.exit(exitValue);
    }

    private static ProcessBuilder buildProcess() {
        final Platform platform = PlatformRegistry.getCurrent();
        final List<String> args = new ArrayList<>();
        args.add(platform.getJvmExecutable());
        args.add("-cp");
        args.add(System.getProperty("java.class.path"));
        platform.getCommandLineFlags().forEach(args::add);
        args.add(TO_LAUNCH);
        final ProcessBuilder builder = new ProcessBuilder(args);
        final Map<String, String> env = builder.environment();
        developmentFlag(env, LauncherOptions.VAR_DEBUG, DEBUG_FILE_NAME, "debug mode");
        return builder.inheritIO();
    }

    private static void developmentFlag(final Map<String, String> env, final String var, final String file,
            final String name) {
        final boolean enabled;
        if (!env.containsKey(var) && new File(file).exists()) {
            env.put(var, "1");
            enabled = true;
        } else {
            enabled = env.containsKey(var) && env.get(var).equals("1");
        }
        if (enabled) {
            final ConfirmDialog alert = new ConfirmDialog("You're trying to start the game with " + name + " enabled.\n"
                    + "Please remember this mode is intended to be used during development only.\n\n"
                    + "Do you want to continue?", ConfirmDialog.Style.WARNING);
            if (!alert.getAnswer()) {
                System.exit(0);
            }
        }
    }
}
