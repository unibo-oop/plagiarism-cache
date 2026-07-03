package it.lttply.controller;

/**
 * Provides a standard representation of what kind of refresh must be made.
 */
public enum RefreshType {
    /**
     * Minimal refresh, provides a simple function of loading without any check
     * of integrity.
     */
    MINIMAL,

    /**
     * Shallow refresh, provides only the necessary refresh. It is different
     * from the minimal because the shallow refresh provides a simple and fast
     * check of integrity.
     */
    SHALLOW,

    /**
     * Deeeeeepest refresh, provides a total-destruction operation which thinks
     * that "new is better": guess what is mean, so use it with caution.
     */
    DEEP
}
