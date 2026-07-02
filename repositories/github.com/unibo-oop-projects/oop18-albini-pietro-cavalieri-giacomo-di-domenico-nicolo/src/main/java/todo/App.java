package todo;

import java.io.IOException;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public final class App {
    private App() {
    }

    public static void main(final String[] args) throws IOException {
        createApplication();
    }

    private static Lwjgl3Application createApplication() throws IOException {
        return new Lwjgl3Application(new TodoGame(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        final Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("//TODO");
        configuration.setResizable(false);
        configuration.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        configuration.setWindowIcon("assets/icon.png");
        return configuration;
    }
}
