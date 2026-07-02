package model.player;
import model.ball.Ball;

import java.io.Serializable;
import java.util.List;

public interface Player extends Serializable{

    /**
     *
     * @return the current ball selected by the user
     */
    Ball getCurrentBall();

    /**
     *
     * @param ball the ball to be set as current ball
     */
    void setCurrentBall(Ball ball);

    /**
     *
     * @return the lists of unlocked balls by the user
     */
    List<Ball> getUnlockedBalls();

    /**
     *
     * @param ball the ball to be added in unlocked balls
     */
    void addUnlockedBall(Ball ball);

    /**
     *
     * @return the current amount of money hold by the user
     */
    int getCurrentMoney();

    /**
     *
     * @return the best score of the user
     */
    int getBestScore();

    /**
     *
     * @return the name of the user
     */
    String getName();

    /**
     *
     * @param name the name of the user
     */
    void setName(String name);

    /**
     *
     * @param money money to be added to the users wallet
     */
    void addMoney(int money);

    /**
     *
     * @param money money to be taken to the users wallet for some shop
     */
    void useMoney(int money);

    /**
     *
     * @param bestScore the new best score to be saved/overwritten
     */
    void setNewBestScore(int bestScore);

    /**
     *
     * @param newScore the new score to be saved/overwritten if > of current best score
     */
    void setNewScore(int newScore);

}
