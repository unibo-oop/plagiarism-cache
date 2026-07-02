package controller.gamecontroller;

import java.util.logging.*;
import utilities.Utilities;
import view.LogView;

class ViewHandler extends Handler {

    private final LogView view;

    ViewHandler(final LogView view) {
        super();
        Utilities.requireNonNull(view);
        this.view = view;
    }

    @Override
    public void publish(final LogRecord record) {
        Utilities.requireNonNull(record);
        view.write(record.getParameters()[0] + ": " + record.getMessage());
    }

    @Override
    public void flush() {
        view.dispose();
    }

    @Override
    public void close() throws SecurityException {
        view.write("Game ended");
    }

}
