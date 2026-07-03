package org.gitgud.application.about;

import org.gitgud.application.node.Box;

/**
 * The about controller relative to the about box.
 */
public interface AboutBoxController extends Box {

    /**
     * Opens the default web browser to show the web link.
     *
     * @param link
     *            the link
     */
    void openWebLink(String link);
}
