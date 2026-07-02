package it.unibo.aknightstale.utils;

import it.unibo.aknightstale.App;
import net.harawata.appdirs.AppDirsFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class AppPaths {
    private AppPaths() {
        // Utility class.
    }

    public static Path getFilePath(final String... pathsToAppend) {
        var appDir = AppDirsFactory.getInstance().getUserDataDir(App.APP_NAME, App.APP_VERSION, "unibo", true);

        if (Boolean.getBoolean("headless")) {
            appDir = Paths.get("build").toAbsolutePath().toString();
        }

        return Paths.get(appDir, pathsToAppend);
    }

    public static Path getFilePath(final Path pathToAppend) {
        return getFilePath().resolve(pathToAppend);
    }
}
