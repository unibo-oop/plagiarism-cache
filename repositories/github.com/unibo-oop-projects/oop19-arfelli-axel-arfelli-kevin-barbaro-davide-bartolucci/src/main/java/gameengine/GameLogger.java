package gameengine;

public interface GameLogger {

    enum OutputLevel {
        LOG("Log"),
        ERROR("Error"),
        DEBUG("Debug");

        private String prefix;

        OutputLevel(final String prefix) {
            this.prefix = prefix;
        }
        public String format(final String line) {
            return String.format("|%5s|:%s", prefix, line);
        }
    }

    void logLine(String line, OutputLevel level);


}
