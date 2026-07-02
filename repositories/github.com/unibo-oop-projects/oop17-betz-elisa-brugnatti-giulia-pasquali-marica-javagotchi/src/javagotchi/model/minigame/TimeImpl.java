package javagotchi.model.minigame;

import java.util.Timer;
import java.util.TimerTask;

import javagotchi.controller.minigame.main.MiniGame;
import javagotchi.utility.Utility;

/**
 * 
 * @author marica
 *
 */
public class TimeImpl implements Time {

    private static final long serialVersionUID = -4217914589505361237L;

    private static final int TIME_START = 15;
    private static final int INC = 3;
    private int seconds;
    private final Timer timer = new Timer();

    /**
     * Constructor for ScoreImpl.
     */
    public TimeImpl() {
        seconds = TIME_START;
    }

    /**
     * Constructor for ScoreImpl.
     * 
     * @param secondsStart
     *            previous seconds
     */
    public TimeImpl(final Integer secondsStart) {
        seconds = secondsStart.intValue();
    }

    private final TimerTask timeTask = new TimerTask() {
        @Override
        public void run() {
            if (!isStop()) {
                seconds--;
                Utility.log("Sec: " + seconds);

                MiniGame.getFactoryController().getControllerMiniGame().getView().updateTimeGui(seconds);

            } else {
                MiniGame.getFactoryController().getControllerMiniGame().endGame();
            }
        };
    };

    private boolean isStop() {
        return seconds <= 0;
    }

    @Override
    public final void start() {
        try {
            timer.scheduleAtFixedRate(timeTask, 0, 1000);
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public final void stopTimer() {
        timer.cancel();
        timer.purge();
    }

    @Override
    public final Time restart() {
        return new TimeImpl(seconds);
    }

    @Override
    public final int getSeconds() {
        return seconds;
    }

    @Override
    public final boolean isTimeGame() {
        return seconds < TIME_START && seconds > 0;
    }

    @Override
    public final void addTime() {
        seconds += INC;
        if (seconds > TIME_START) {
            seconds = TIME_START;
        }
        MiniGame.getFactoryController().getControllerMiniGame().getView().updateTimeGui(seconds);
    }

    @Override
    public final int getStartTime() {
        return TIME_START;
    }

    @Override
    public final boolean isStartTime() {
        return this.seconds == TIME_START;
    }

}
