package bzzbomber.model;

import java.io.Serializable;

/**
 * has two field: name of player and his point.
 */
public class Score implements Serializable {

    /**
     * serial UID.
     */
    private static final long serialVersionUID = -6829807389711146996L;

    private final String nickName;
    private final int point;

    /**
     * constructor of the class.
     * 
     * @param name
     *            the name of the player.
     * @param score
     *            the point that he done during the game.
     * @throws IllegalArgumentException
     *             to control if the score is a valid number.
     */
    public Score(final String name, final int score) throws IllegalArgumentException {
        if (score <= 0 || name.isEmpty()) {
            throw new IllegalArgumentException("Score or Name not vaild");
        }
        this.nickName = name;
        this.point = score;
    }

    /**
     * second constructor of the class.
     * 
     * @param sc
     *            Score
     */
    public Score(final Score sc) {
        this.nickName = sc.getName();
        this.point = sc.getPoint();
    }

    /**
     * return the point.
     * 
     * @return the point done.
     */
    public int getPoint() {
        return this.point;
    }

    /**
     * return the nickname.
     * 
     * @return the name of the player.
     */
    public String getName() {
        return this.nickName;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
        result = prime * result + point;
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Score other = (Score) obj;
        if (nickName == null) {
            if (other.nickName != null) {
                return false;
            }
        } else if (!nickName.equals(other.nickName)) {
            return false;
        }

        return other.point == point;
    }

}
