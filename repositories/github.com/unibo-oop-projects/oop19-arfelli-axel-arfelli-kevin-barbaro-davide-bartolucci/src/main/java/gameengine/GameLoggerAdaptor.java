package gameengine;

import org.tinylog.Logger;

public class GameLoggerAdaptor implements GameLogger {

    @Override
    public final void logLine(final String line, final OutputLevel level) {
        switch (level) {
            case DEBUG:
                Logger.debug(line);
                break;
            case ERROR:
                Logger.error(line);
                break;
            case LOG:
                Logger.info(line);
                break;
            default:
                break;
        }
    }

}