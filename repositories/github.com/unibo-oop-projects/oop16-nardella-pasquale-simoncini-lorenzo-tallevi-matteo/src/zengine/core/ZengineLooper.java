package zengine.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

final class ZengineLooper implements ActionListener {
    // does not require to implement ZengineComponent since the looper is
    // something different, above other components

    private static volatile ZengineLooper looper = new ZengineLooper();
    private boolean running; // = false;

    private boolean warmingUp = true;
    private static final int WARM_UP_DELAY = 5;
    private int warmUpTimer = WARM_UP_DELAY;

    private final Timer timer;
    private static final int TIMER_DELAY = 16;
    private static final int DESIRED_UPDATE_SPEED = 33;

    private ZengineLooper() {
        // the timer will wait a little and then it will start the loop
        timer = new Timer(TIMER_DELAY, this);
        timer.start();
    }

    public static ZengineLooper getLooper() {
        return looper;
    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {

        timer.stop();
        running = true;
        runGameLoop();
    }

    private void gameLoop() {
        while (running) {
            gameCycle();
            try {
                Thread.sleep(DESIRED_UPDATE_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void runGameLoop() {
        final Thread loop = new Thread() {
            public void run() {
                gameLoop();
            }
        };
        loop.start();
    }

    private void gameCycle() {
        // game loop not including wait time and cpu time. To improve in future
        // releases
        warmUp();

        ZengineSystem.getSystem().getComponent().update();

        ZengineInput.getInput().getComponent().update();
        ZengineAudio.getAudio().getComponent().update();

        ZengineGUI.getGUI().getComponent().update();
    }

    // should have been package private, but public is fine as well since the
    // class is package private
    public boolean isWarmingUp() {
        return warmingUp;
    }

    private void warmUp() {
        if (warmUpTimer > 0) {
            warmUpTimer -= 1;
        } else {
            if (warmingUp) {
                warmingUp = false;
            }
        }
    }
}
