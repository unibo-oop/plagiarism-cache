package model.components;

import model.components.GameTimer;
import model.components.GameTimerImpl;
import model.components.Score;
import model.components.ScoreImpl;

/**
 * 
 * Tests the functionality of the the threads associated to the two Runnable objects ScoreImpl and GameTimerImpl.
 *
 */
public class RunnableTest {

    private static final int SLEEP = 10_000;

    /**
     * Tests whether the score decreases every second.
     */
    public void testScore() {
        final Score score = new ScoreImpl();
        score.setPoints(100);
        final Thread scoreThread = new Thread(score);
        scoreThread.start();
        final Thread thread = new Thread(() -> {
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            scoreThread.interrupt();
            System.out.println("SCORE: Expected: 190, Result: " + score.getGlobalScore());
        });
        thread.start();
    }

    /**
     * Tests whether the timer increases every tenth of second.
     */
    public void testGameTimer() {
        final GameTimer timer = new GameTimerImpl();
        final Thread timerThread = new Thread(timer);
        timerThread.start();
        final Thread thread = new Thread(() -> {
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timerThread.interrupt();
            System.out.println("TIMER: Expected: 00 : 00 : 09 : 00, Result: " + timer.toString());
            System.out.println("(It's impossible to predict the perfect timing so this is a valuation)");
        });
        thread.start();
    }

    /**
     * Tests "testScore" and "testGameTimer".

     * @param args
     *      unused
     */
    public static void main(final String[] args) {
        final RunnableTest test = new RunnableTest();
        test.testScore();
        test.testGameTimer();
    }
}
