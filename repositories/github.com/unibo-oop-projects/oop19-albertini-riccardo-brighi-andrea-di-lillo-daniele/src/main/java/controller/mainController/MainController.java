package controller.mainController;

import model.ball.Ball;
import model.block.*;

import java.util.List;

public interface MainController {
    /**
     *
     * @return true if its time to launch
     */
    boolean isLaunchPossible(); //metodo privato

    /**
     *
     * @param blocks hitted blocks to be notified
     */
    void notifyHittedBlocks(List<BlockImpl> blocks);

    /**
     *
     * @return boolean value true if game is finisched, false on the other way
     */
    boolean isGameFinished();

    /**
     *
     * @return the current score
     */
    int getScore();

    /**
     * add one score unity to the current score
     */
    void addScore();

    /**
     *
     * @param money money to be added
     */
    void addMoney(int money);
    /**
     * add one score unity to the current score
     */
    void addBall();

    /**
     *
     * @param x x component of the launch position
     * @param y y component of the launch position
     */
    void setPositionLaunch(int x, int y);

    /**
     *
     * @param newRow new row to be added to the block grid
     */
    void updateGridBlocks(List<BlockImpl> newRow);

    /**
     *
     * @param colIndex column index
     * @return the column with index specified
     */
    List<BlockImpl> getColumnOfBlocks(int colIndex);

    /**
     *
     * @param rowIndex row index
     * @return the row with index specified
     */
    List<BlockImpl> getRowOfBlocks(int rowIndex);

    /**
     *
     * @return the balls list
     */
    List<Ball> getBalls();

    /**
     *
     * @return the blocks list
     */
    List<BlockImpl> getBlocks();

    void allBallsBackToHome();

    void setBallsObservers();

    void pauseGame();

    void restartGame();

    boolean isPaused();

}
