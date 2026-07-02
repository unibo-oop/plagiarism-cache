/**
 * 
 */
package bzzbomber.view;

import bzzbomber.model.Score;

/**
 * this class represents one row of the table.
 */
public class StatisticsLine {

    private final int position;
    private final String nickName;
    private final int score;

    /**
     * each line is composed by a position in ranking and a Score (name + point).
     * 
     * @param pos
     *            a progressive number from one to ten.
     * @param score
     *            Score that the player has done.
     */
    public StatisticsLine(final int pos, final Score score) {
        this.position = pos;
        this.nickName = score.getName();
        this.score = score.getPoint();
    }

    /**
     * return the name contained in Score.
     * 
     * @return the nickname of the player.
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * return the point contained in Score.
     * 
     * @return the point of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * return the position of the player in ranking.
     * 
     * @return the position in ranking.
     */
    public int getPosition() {
        return position;
    }

}
