package todo.view.drawables.level.ui.playback;

import todo.utils.Disposable;

/**
 * This interface models the playback controls of the level UI. These controls
 * are: play/stop, step, undo/redo, copy/paste
 */
public interface PlaybackControls extends Disposable {
    /**
     * Refresh the buttons state.
     */
    void refresh();
}
