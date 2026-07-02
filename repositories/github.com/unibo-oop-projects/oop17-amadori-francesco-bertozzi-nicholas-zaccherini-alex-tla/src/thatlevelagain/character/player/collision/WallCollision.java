package thatlevelagain.character.player.collision;



import java.util.List;

import thatlevelagain.character.player.Player;
import thatlevelagain.view.sprite.Mattoni;

/**
 * 
 */
public class WallCollision {
    private boolean collidingUpperWall;

    /**
     * 
     */
    public WallCollision() {
        this.collidingUpperWall = false;
    }

    /**
     * 
     * @param player
     *          Object of class Player.
     * @param wall
     *          List of object Mattoni.
     * 
     */
    public void intersection(final Player player, final List<Mattoni> wall) {
        wall.forEach(brick -> {
            if (player.getAumentoY() == Player.INCREMENT_Y && (player.getRectBottom().intersects(brick.getRectUp()))) {
                player.setAumentoY(Player.STOP);
            }
            if (player.getRectUp().intersects(brick.getRectBottom())) {
                this.setCollidingUpperWall(true);
            }
            if (player.getAumentoX() == Player.DECREMENT_X && player.getRectLeft().intersects(brick.getRectRight())) {
                player.setAumentoX(Player.STOP);
            }
            if (player.getAumentoX() == Player.INCREMENT_X && player.getRectRight().intersects(brick.getRectLeft())) {
                player.setAumentoX(Player.STOP);
            }
        });
    }

    /**
     * Setter for the boolean variable that set if the plyaer is colliding to the upper wall.
     * 
     * @param value
     *  set the value.
     */
    public void setCollidingUpperWall(final boolean value) {
        this.collidingUpperWall = value;
    }

    /**
     * @return 
     *  return the value of the boolean variable.
     */
    public boolean isColidingUpperWall() {
        return this.collidingUpperWall;
    }

}
