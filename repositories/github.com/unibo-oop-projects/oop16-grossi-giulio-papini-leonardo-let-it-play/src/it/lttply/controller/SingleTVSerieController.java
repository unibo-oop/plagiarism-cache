package it.lttply.controller;

/**
 * Manager for a single tv series instance. Not implemented, every method will
 * throw {@link UnsupportedOperationException}.
 */
public class SingleTVSerieController implements SingleMediaController {

    @Override
    public void refresh(final RefreshType type) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void play() {
        throw new UnsupportedOperationException();
    }

}
