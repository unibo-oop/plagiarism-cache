package it.unibo.aknightstale.utils;

import it.unibo.aknightstale.App;
import net.harawata.appdirs.AppDirsFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

class AppPathsTest {
    @Test
    @DisplayName("Get the path of a file in appdata folder")
    void filePath() {
        // If the app is run in headless mode, the appdir path is the build folder.
        final var appdata = Boolean.getBoolean("headless")
                ? Paths.get("build").toAbsolutePath().toString()
                : AppDirsFactory.getInstance().getUserDataDir(App.APP_NAME, App.APP_VERSION, "unibo", true);

        final var path = appdata + System.getProperty("file.separator") + "test.txt";

        Assertions.assertThat(AppPaths.getFilePath("test.txt")).hasToString(path);
        Assertions.assertThat(AppPaths.getFilePath(Path.of("test.txt"))).hasToString(path);
    }
}
