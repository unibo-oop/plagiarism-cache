package clashclass.battle.timer;



import java.util.Optional;

/**
 * Represent the Timer Implementation.
 */
public class TimerImpl implements Timer {

    private static final long TIME_UNIT = 1000; // 1 second in milliseconds
    private static final int TIME_LIMIT = 180;  // Time limit in seconds (3 minutes)
    private volatile boolean isRunning;
    private Thread timerThread;
    private long startTime;

    /**
     * Constructs the timer.
     */
    public TimerImpl() {
        this.isRunning = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void start() {
        if (isRunning) {
            return;
        }
        switchIsRunning();
        startTime = System.currentTimeMillis();
        timerThread = new Thread(this::runTimer);
        timerThread.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public synchronized void stop() {
        if (!isRunning) {
            return;
        }
        switchIsRunning();
        if (Optional.ofNullable(timerThread).isPresent() && timerThread.isAlive()) {
            timerThread.interrupt();
            try {
                timerThread.join(); // Wait for the timer thread to finish execution
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt(); // Propagate the interrupt status
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized long getElapsedTime() {
        if (!isRunning) {
            return 0;
        }
        return (System.currentTimeMillis() - startTime) / 1000;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void onFinished() {
        if (isRunning) {
            stop();
        }
    }

    // The method that runs the timer in a separate thread.
    // The timer checks elapsed time every second until the time limit is reached.
        private void runTimer() {
        try {
            while (isRunning) {
                final long elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000;
                if (elapsedSeconds >= TIME_LIMIT) {
                    onFinished();
                    break;
                }
                Thread.sleep(TIME_UNIT);
            }
        } catch (final InterruptedException e) {
            if (isRunning) {
                Thread.currentThread().interrupt(); // Re-interrupt if necessary
            }
        }
    }

    private void switchIsRunning() {
        isRunning = !isRunning;
    }
}
