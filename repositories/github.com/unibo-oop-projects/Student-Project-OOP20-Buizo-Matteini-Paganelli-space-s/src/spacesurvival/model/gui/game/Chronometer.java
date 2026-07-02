package spacesurvival.model.gui.game;

public class Chronometer extends Thread {
    private String timer;

    private int seconds;
    private int minutes;
    private int hours;

    private boolean play;
    private static final int STEP = 60;

    public Chronometer() {
        super();

        this.timer = "00:00:00";
        this.play = false;
        this.start();
    }

    /**
     * Restart the timer.
     */
    public void restart() {
        this.seconds = 0;
        this.minutes = 0;
        this.hours = 0;
        this.timer = "00:00:00";
    }

    /**
     * Return a String representing the timer.
     * 
     * @return a string representing the current time passed between the start of the game
     */
    public String getTimer() {
        return this.timer;
    }

    /**
     * Play the chronometer.
     * 
     */
    public void play() {
        this.play = true;
    }

    /**
     * Stop the chronometer.
     * 
     */
    public void stopTimer() {
        this.play = false;
    }

    /**
     * Return true if the chronometer is running.
     * 
     * @return true if is playing
     * 
     */
    public boolean isPlaying() {
        return this.play;
    }


    /**
     * Increment the seconds.
     */
    private void incrSecond() {
        this.seconds++;
    }

    /**
     * Check the accuracy of the time, if sixty seconds add a minute and same for the hours.
     */
    private void check() {
        if (this.seconds == STEP) {
            this.seconds = 0;
            this.minutes++;
        }

        if (this.minutes == STEP) {
            this.minutes = 0;
            this.hours++;
        }
    }

    private String controlFormat(final int number) {
        return number < 10 ? "0" + number : Integer.toString(number);
    }

    private String makeFormatTimer(final String hours, final String minutes, final String seconds, final String split) {
        return hours + split + minutes + split + seconds;
    }


    private void pause(final int millis) {
        try {
            sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run the chronometer.
     */
    public void run() {
        while (true) {
            if (this.play) {
                this.incrSecond();
                this.check();
                this.pause(1000);
            }
            this.timer = this.makeFormatTimer(
                    this.controlFormat(this.hours),
                    this.controlFormat(this.minutes),
                    this.controlFormat(this.seconds), ":");
        }
    }
}
