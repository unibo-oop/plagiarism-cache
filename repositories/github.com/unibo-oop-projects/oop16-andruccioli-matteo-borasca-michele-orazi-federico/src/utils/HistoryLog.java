package utils;

import java.util.Optional;

import view.View;

/**
 * A logger used to update view console, when a player makes something.
 */
public final class HistoryLog {

    private Optional<View> view;
    private static final HistoryLog SINGLETON = new HistoryLog();

    private HistoryLog() { }

    /**
     * Gets the Log object.
     * @return
     *        the Log object.
     */
    public static HistoryLog getLog() {
        return SINGLETON;
    }

    /**
     * Registers a new view. If a new view is recorded while another view already exits, 
     * the second view will overwrite the first.
     * @param view
     *            represents the view to be registered.
     */
    public void registerView(final View view) {
        this.view = Optional.of(view);
    }

    /**
     * Sends a message to registered view.
     * @param msg
     *           the message to be sent.
     */
    public void send(final String msg) {
        this.view.get().updateLog(msg);
    }

}
