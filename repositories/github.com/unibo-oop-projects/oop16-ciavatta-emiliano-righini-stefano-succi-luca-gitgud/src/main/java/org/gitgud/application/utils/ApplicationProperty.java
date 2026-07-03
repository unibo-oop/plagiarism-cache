package org.gitgud.application.utils;

/**
 * Contains all application properties.
 */
public interface ApplicationProperty {

    /**
     * @return the current merge mode
     */
    String getMergeMode();

    /**
     * @return the remote color manager
     */
    ColorManager getRemoteColorManager();

    /**
     * @param mergeMode
     *            the merge mode to set
     */
    void setMergeMode(String mergeMode);

}
