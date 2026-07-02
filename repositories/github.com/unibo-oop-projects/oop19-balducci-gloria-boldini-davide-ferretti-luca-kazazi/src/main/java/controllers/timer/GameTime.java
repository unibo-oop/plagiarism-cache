package controllers.timer;

import java.util.Objects;
import java.util.Timer;

import controllers.camera.Camera;

public class GameTime implements GameTimeInterface {

    private static final int BEST_MIN_LV1 = 1;
    private static final int BEST_SEC_LV1 = 20;
    private static final int MID_MIN_LV1 = 1;
    private static final int MID_SEC_LV1 = 40;
    private static final int BAD_MIN_LV1 = 2;
    private static final int BAD_SEC_LV1 = 0;
    private static final int BEST_MIN_LV2 = 1;
    private static final int BEST_SEC_LV2 = 50;
    private static final int MID_MIN_LV2 = 2;
    private static final int MID_SEC_LV2 = 15;
    private static final int BAD_MIN_LV2 = 2;
    private static final int BAD_SEC_LV2 = 40;
    private static final int BEST_MIN_LV3 = 2;
    private static final int BEST_SEC_LV3 = 0;
    private static final int MID_MIN_LV3 = 2;
    private static final int MID_SEC_LV3 = 30;
    private static final int BAD_MIN_LV3 = 3;
    private static final int BAD_SEC_LV3 = 0;
    private static final int BEST_MIN_LV4 = 3;
    private static final int BEST_SEC_LV4 = 30;
    private static final int MID_MIN_LV4 = 4;
    private static final int MID_SEC_LV4 = 0;
    private static final int BAD_MIN_LV4 = 4;
    private static final int BAD_SEC_LV4 = 30;
    private static final int BEST_MIN_LV5 = 3;
    private static final int BEST_SEC_LV5 = 50;
    private static final int MID_MIN_LV5 = 4;
    private static final int MID_SEC_LV5 = 20;
    private static final int BAD_MIN_LV5 = 4;
    private static final int BAD_SEC_LV5 = 50;
    private static final int MAX_HOURS = 1;
    private Timer timer;
    private int level;
    private MyTimerTask timerTask;
    private int starsGained;
    private Camera camera;
    private int hours;
    private int mins;
    private int secs;

    public GameTime() {
        this.starsGained = 0;
    }

    @Override
    public void setLevel(final int level, final Camera camera) {
        this.level = Objects.requireNonNull(level);
        this.camera = Objects.requireNonNull(camera);
    }

    @Override
    public void start() {
        this.timer = new Timer();
        this.timerTask = new MyTimerTask(camera, hours, mins, secs);
        this.timer.schedule(timerTask, 0, 1000);
    }

    @Override
    public void pause() {
        this.hours = timerTask.getHours();
        this.mins = timerTask.getMins();
        this.secs = timerTask.getSecs();
        this.timer.cancel();
    }

    @Override
    public void reset() {
        this.hours = 0;
        this.mins = 0;
        this.secs = 0;
        timerTask.cancel();
        this.timer.cancel();
    }

    @Override
    public void stop() {

        if (this.level == 1) {
            this.starsGained = this.calculateLevelStars(BEST_MIN_LV1, BEST_SEC_LV1, MID_MIN_LV1, MID_SEC_LV1, BAD_MIN_LV1,
                    BAD_SEC_LV1);
        } else if (this.level == 2) {
            this.starsGained = this.calculateLevelStars(BEST_MIN_LV2, BEST_SEC_LV2, MID_MIN_LV2, MID_SEC_LV2, BAD_MIN_LV2,
                    BAD_SEC_LV2);
        } else if (this.level == 3) {
            this.starsGained = this.calculateLevelStars(BEST_MIN_LV3, BEST_SEC_LV3, MID_MIN_LV3, MID_SEC_LV3, BAD_MIN_LV3,
                    BAD_SEC_LV3);
        } else if (this.level == 4) {
            this.starsGained = this.calculateLevelStars(BEST_MIN_LV4, BEST_SEC_LV4, MID_MIN_LV4, MID_SEC_LV4, BAD_MIN_LV4,
                    BAD_SEC_LV4);
        } else if (this.level == 5) {
            this.starsGained = this.calculateLevelStars(BEST_MIN_LV5, BEST_SEC_LV5, MID_MIN_LV5, MID_SEC_LV5, BAD_MIN_LV5,
                    BAD_SEC_LV5);
        }
        this.timer.cancel();
    }

    private int calculateLevelStars(final int bestMin, final int bestSec, final int middleMin, final int midSec,
            final int badMin, final int badSec) {
        if (this.timerTask.getHours() < MAX_HOURS && ((this.timerTask.getMins() < bestMin)
                || (this.timerTask.getMins() == bestMin && this.timerTask.getSecs() <= bestSec))) {
            return 3;
        } else if (this.timerTask.getHours() < MAX_HOURS && ((this.timerTask.getMins() < middleMin)
                || (this.timerTask.getMins() == middleMin && this.timerTask.getSecs() <= midSec))) {
            return 2;
        } else if (this.timerTask.getHours() < MAX_HOURS && ((this.timerTask.getMins() < badMin)
                || (this.timerTask.getMins() == badMin && this.timerTask.getSecs() <= badSec))) {
            return 1;
        }
        return 0;
    }

    public final int getStarsGained() {
        return this.starsGained;
    }

    public final MyTimerTask getTimerTask() {
        return this.timerTask;
    }
}