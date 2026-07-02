package controllers.timer;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Objects;
import java.util.TimerTask;

import controllers.camera.Camera;

import java.awt.Color;

public class MyTimerTask extends TimerTask implements MyTimerTaskInterface {

    private static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int NEXTMIN = 59;
    private int hours;
    private int mins;
    private int secs;
    private final Camera camera;

    public MyTimerTask(final Camera camera, final int hours, final int mins, final int secs) {
        this.hours = Objects.requireNonNull(hours);
        this.mins = Objects.requireNonNull(mins);
        this.secs = Objects.requireNonNull(secs);
        this.camera = Objects.requireNonNull(camera);
    }

    @Override
    public int getHours() {
        return this.hours;
    }

    @Override
    public int getMins() {
        return this.mins;
    }

    @Override
    public int getSecs() {
        return this.secs;
    }

    @Override
    public void setHours(final int hours) {
        this.hours = hours;
    }

    @Override
    public void setMins(final int mins) {
        this.mins = mins;
    }

    @Override
    public void setSecs(final int secs) {
        this.secs = secs;
    }

    @Override
    public void run() {
        if (this.secs < NEXTMIN) {
            this.secs++;
        } else if (this.secs >= NEXTMIN) {
            this.secs = 0;
            if (this.mins >= NEXTMIN) {
                this.mins = 0;
                this.hours++;
            } else if (this.mins < NEXTMIN) {
                this.mins++;
            }
        }
    }

    @Override
    public void drawTime(final Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.BOLD, (int) SCREEN_HEIGHT / 50));
        g.drawString((this.hours + ":" + this.mins + ":" + this.secs), (int) SCREEN_WIDTH / 100 + (int) camera.getX(),
                (int) SCREEN_HEIGHT / 10 + (int) camera.getY());
    }

}
