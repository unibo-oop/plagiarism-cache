package com.jlearn.controller.fileio;

import com.jlearn.view.utilities.enums.ExerciseType;

/**
 * Represent all paths used from JLearn App.
 */
public enum ControllerFolderPaths {

    /**
     * Standard directory of JLearn.
     */
    STD_NAME_DIR_UT("Jlearn"),
    /**
     * Standard directory of User inside JLearn standard directory.
     */
    DEF_USER_DIR_NAME("User"),
    /**
     * Separator Used in jar.
     */
    DEF_JAR_DIR_SEP("/"),

    /**
     * Separator and home for portable App.
     */
    HOME(System.getProperty("user.home")), PATH_SEP(System.getProperty("file.separator")),

    /**
     * The paths inside jar of JLearn.
     */
    STD_DIR_ES_AUD("/file/audio/"), STD_DIR_THEORY("/pdf/"), STD_DIR_AUD_FX("/audioFX/"), STD_DIR_ES("/file/"), STD_DIR_DEF_IMG("/images/");
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

    ControllerFolderPaths(final String path) {
        this.path = path;
    }

    /**
     * @return the {@link ControllerFolderPaths} path.
     */
    public String getPath() {
        return this.path;
    }

}
