package it.dpg.minigames.molegame.model;

public class ScoreImpl implements Score {

    private int score=0;

    public ScoreImpl(){}

    /**
     * return the final Score of the game
     */
    @Override
    public int finalScore() {
        return score;
    }

    /**
     * when player hit the mole add 1 point to his score
     */
    @Override
    public void addPoint() {
        score++;
    }


}
