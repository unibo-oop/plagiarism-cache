package controller.ballscontroller;

public interface Pause {


    /**
     * @throws UnsupportedOperationException if the class doesn't support pause
     *                                       <p>
     *                                       restart it
     *                                       </p>
     */

    void restart();

    /**
     * @throws UnsupportedOperationException if the class doesn't support pause
     *                                       <p>
     *                                       pause it
     *                                       </p>
     */

    void pause();


    /**
     * @return true if it's in pause else false
     */

    boolean isPause();

    /**
     * @return true if it's over else false
     */

    boolean isOver();
}
