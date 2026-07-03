package zengine.core;

import zengine.interfaces.GameFunctionsLogger;

final class ZengineLogger implements ZengineComponent, GameFunctionsLogger {
    private static ZengineLogger logger = new ZengineLogger();

    private boolean messagesEnabled = true;
    private boolean warningsEnabled = true;

    private ZengineLogger() {
    }

    public static ZengineLogger getLogger() {
        return logger;
    }

    @Override
    public ZengineComponent getComponent() {
        return logger;
    }

    @Override
    public void initialize() {
        restoreDefaultValues();
    }

    @Override
    public void reinitialize() {
        initialize();
    }

    @Override
    public void restoreDefaultValues() {
        messagesEnabled = true;
        warningsEnabled = true;
    }

    @Override
    public void link() {
        // nothing to do
    }

    @Override
    public void update() {
        // nothing to do
    }

    @Override
    public void loggerEnableMessages(final boolean yesOrNo) {
        messagesEnabled = yesOrNo;
    }

    @Override
    public void loggerEnableWarnings(final boolean yesOrNo) {
        warningsEnabled = yesOrNo;
    }

    @Override
    public void loggerMessage(final String message) {
        if (messagesEnabled) {
            System.out.println(/* "M: "+ */message);
        }
    }

    @Override
    public void loggerWarning(final String warning) {
        if (warningsEnabled) {
            System.err.println(/* "W: "+ */warning);
        }
    }

    @Override
    public void loggerError(final String error) {
        System.err.println("ERROR: " + error);
        Zengine.getEngine().gameTerminate(1);
    }
}
