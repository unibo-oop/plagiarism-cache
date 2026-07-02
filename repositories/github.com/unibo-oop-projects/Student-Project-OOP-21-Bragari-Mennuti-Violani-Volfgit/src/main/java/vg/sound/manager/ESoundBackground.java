package vg.sound.manager;

import vg.utils.path.PathSound;

public enum ESoundBackground {
    START(PathSound.BACKGROUND_START),
    GAME(""),
    END("");

    private final String path;

    ESoundBackground(final String path) {
        this.path = path;
    }
    public String getPath() {
        return this.path;
    }
}
