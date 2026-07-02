package model.balllaucher;

import org.apache.commons.lang3.tuple.Pair;

import model.ball.Ball;

/**
 * Interface that model the launcher of a {@link Ball}.
 */
public interface BallLauncher {

    /**
     * Update the position and status of the launcher.
     * @param mousePosition the direction the launcher has to points towards
     */
    void update(Pair<Double, Double> mousePosition);

    /**
     * @param b the {@link Ball} that has to be launched
     */
    void load(Ball b);

    /**
     * Launch the {@link Ball}.
     */
    void launch();

    /**
     * @return the position of the mouth of the launcher
     */
    Pair<Double, Double> getPosition();

    /**
     * @return the width of the launcher
     */
    double getWidth();

    /**
     * @return the height of the launcher
     */
    double getHeight();
}
