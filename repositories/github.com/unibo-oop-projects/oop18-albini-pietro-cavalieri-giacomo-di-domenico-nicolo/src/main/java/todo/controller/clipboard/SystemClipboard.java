package todo.controller.clipboard;

import java.util.Optional;

import com.badlogic.gdx.Gdx;

/**
 * This class is a {@link ClipboardProvider} that allows to interact with the
 * system clipboard allowing to copy and paste strings to/from the current
 * application.
 */
public final class SystemClipboard implements ClipboardProvider {
    private static SystemClipboard instance;

    private SystemClipboard() {
    }

    public static SystemClipboard getInstance() {
        if (instance == null) {
            instance = new SystemClipboard();
        }
        return instance;
    }

    @Override
    public Optional<String> get() {
        return Optional.ofNullable(Gdx.app.getClipboard().getContents());
    }

    @Override
    public void set(final String string) {
        Gdx.app.getClipboard().setContents(string);
    }
}
