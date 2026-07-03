package it.lttply.controller;

import it.lttply.model.domain.MediaContainer;

/**
 * Functional interface which permits to manage the initialization phase of the
 * reproduction of the {@link MediaContainer} chosen.
 */
public interface SingleMediaController extends Controller {
    /**
     * Sets the environment in order to reproduce the {@link MediaContainer}.
     */
    void play();
}
