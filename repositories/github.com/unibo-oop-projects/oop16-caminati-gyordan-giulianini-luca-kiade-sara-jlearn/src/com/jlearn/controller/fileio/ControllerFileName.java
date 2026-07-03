package com.jlearn.controller.fileio;

import java.io.File;

import com.jlearn.view.utilities.enums.ExerciseType;

/**
 * Represent all {@link File} name and relative extension used from JLearn App.
 */
public enum ControllerFileName {
    /**
     * All name {@link File} used.
     */
    USER_F("User"), UNIT_NAME_F("UnitName"), STATS_F("Stats"), SAVE_IMG_NAME("profile"), DEFAULT_IMG_NAME("Default_User"), EXERCISE_NAME("Es"), LOADING_GIF_NAME("Preloader2"),

    /**
     * All name extension and format used.
     */
    SERIALIZED_FILE_EXTENSION(".ser"), THEORY_FILE_EXT(".html"), UNIT_NAME_F_EXTENSION(".txt"), IMAGE_EXTENSION(".png"), AUD_EXT(".mp3"), EXERCISE_EXT(".csv"), IMAGE_FORMAT("PNG"), GIF_FORMAT(".gif");
    private String path;

    /**
     *
     * @param value
     *            The string to be checked.
     * @return true if is part of {@link ControllerFolderPaths} Enum.
     */
    public static boolean isValue(final String value) {

        for (final ExerciseType type : ExerciseType.values()) {
            if (type.name().equals(value)) {
                return true;
            }
        }

        return false;
    }

    ControllerFileName(final String path) {
        this.path = path;

    }

    /**
     * @return the {@link ControllerFolderPaths} path.
     */
    public String getName() {
        return this.path;
    }
}
