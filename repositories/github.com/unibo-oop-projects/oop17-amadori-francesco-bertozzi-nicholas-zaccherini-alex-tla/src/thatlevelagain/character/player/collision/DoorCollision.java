package thatlevelagain.character.player.collision;


import thatlevelagain.character.player.Player;
import thatlevelagain.view.sprite.Door;

/**
 * 
 */
public class DoorCollision {
    /**
     * 
     */
    private  int knockCounter;
    private int insideBoundsCounter;


    /**
     * 
     */
    public DoorCollision() {
        this.knockCounter = 0;
        this.insideBoundsCounter = 0;
    }

    /**
     * @param player
     *          Object of class Player.
     * @param door
     *          Object of class Door.
     *
     */
    public static void intersection(final Player player, final Door door) {
        if (player.getRectRight().intersects(door.getRectLeft()) && !door.isOpen() && player.getAumentoX() == Player.INCREMENT_X) {
            player.setAumentoX(Player.STOP);
        }
    }

    /**
     * 
     * @param player
     *          Object of class Player.
     * @param door
     *          Object of class Door.
     */
    public void knockDoor(final Player player, final Door door) {
        if (player.getRectRight().intersects(door.getRectLeft()) && !door.isOpen() && player.getAumentoX() == Player.INCREMENT_X) {
            player.setAumentoX(Player.STOP);
            this.insideBoundsCounter++;
            if (this.insideBoundsCounter == 1) {
                this.knockCounter++;
            }
        } else if (player.getAumentoX() == Player.DECREMENT_X) {
            this.insideBoundsCounter = 0;
        }
        if (this.knockCounter == 3) {
            player.openDoor();
            this.knockCounter = 0;
        }
    }

}
