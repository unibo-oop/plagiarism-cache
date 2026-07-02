package thatlevelagain.character.player;

import thatlevelagain.character.player.collision.AccalappiaGattiCollision;
import thatlevelagain.character.player.collision.ButtonCollision;
import thatlevelagain.character.player.collision.DogCollision;
import thatlevelagain.character.player.collision.DoorCollision;
import thatlevelagain.character.player.collision.ElectricityCollision;
import thatlevelagain.character.player.collision.EndLevelCollision;
import thatlevelagain.character.player.collision.GomitoloCollision;
import thatlevelagain.character.player.collision.RockCollision;

/**
 * 
 */
public final class UpdateManager {
    /**
     * 
     */
    public static final int LVL1 = 1;
    /**
     * 
     */
    public static final int LVL2 = 2;
    private static final int LVL3 = 3;
    /**
     * 
     */
    public static final int LVL4 = 4;
    /**
     * 
     */
    public static final int LVL5 = 5;
    private static final int LVL6 = 6;
    /**
     * 
     */
    public static final int LVL7 = 7;
    /**
     * 
     */
    public static final int LVL8 = 8;
    /**
     * 
     */
    public static final int LVL9 = 9;
    /**
     * 
     */
    public static final int LVL10 = 10;
    /**
     * 
     */
    public static final int LVL11 = 11;
    /**
     * 
     */
    public static final int LVL12 = 12;

    /**
     * 
     */
    private UpdateManager() {
    }

    /**
     * @param player
     *          Object of calss Player.
     * @param button
     *          Object of class ButtonCollision.
     * @param doorCollision
     *          Object of class DoorCollision.
     */
    public static void manage(final Player player, final ButtonCollision button, final DoorCollision doorCollision) {
        switch (player.getMap().getLevel()) {
        case LVL1:
            button.buttonAction(player, player.getMap().getButton());
            DoorCollision.intersection(player, player.getMap().getDoor());
            EndLevelCollision.intersection(player, player.getMap().getEnd());
            break;
        case LVL2:
            button.buttonAction(player, player.getMap().getButton());
            DoorCollision.intersection(player, player.getMap().getDoor());
            EndLevelCollision.intersection(player, player.getMap().getEnd());
            break;
        case LVL3:
            button.buttonAction(player, player.getMap().getButton());
            DoorCollision.intersection(player, player.getMap().getDoor());
            EndLevelCollision.intersection(player, player.getMap().getEnd());
            break;
        case LVL4:
            button.buttonAction(player, player.getMap().getButton());
            EndLevelCollision.intersection(player, player.getMap().getEnd());
            break;
        case LVL5:
            GomitoloCollision.intersection(player, player.getMap().getGomitolo());
            button.buttonAction(player, player.getMap().getButton());
            DoorCollision.intersection(player, player.getMap().getDoor());
            EndLevelCollision.intersection(player, player.getMap().getEnd());
            break;
        case LVL6:
            /*
             * Door and End Object don't do anything!
             */
            button.buttonAction(player, player.getMap().getButton());
            break;
        case LVL7:
            DogCollision.intersection(player, player.getMap().getDog());
            button.buttonAction(player, player.getMap().getButton());
            DoorCollision.intersection(player, player.getMap().getDoor());
            EndLevelCollision.intersection(player, player.getMap().getEnd());
            break;
        case LVL8:
            AccalappiaGattiCollision.intersection(player, player.getMap().getAccalappiaGatti());
            button.buttonAction(player, player.getMap().getButton());
            DoorCollision.intersection(player, player.getMap().getDoor());
            EndLevelCollision.intersection(player, player.getMap().getEnd());
            break;
        case LVL9:
            button.buttonAction(player, player.getMap().getButton());
            DoorCollision.intersection(player, player.getMap().getDoor());
            EndLevelCollision.intersection(player, player.getMap().getEnd());
            break;
        case LVL10:
            button.buttonAction(player, player.getMap().getButton());
            doorCollision.knockDoor(player, player.getMap().getDoor());
            EndLevelCollision.intersection(player, player.getMap().getEnd());
            break;
        case LVL11:
            RockCollision.intersection(player, player.getMap().getRock());
            button.buttonAction(player, player.getMap().getButton());
            DoorCollision.intersection(player, player.getMap().getDoor());
            EndLevelCollision.intersection(player, player.getMap().getEnd());
            break;
        case LVL12:
            ElectricityCollision.intersection(player, player.getMap().getScossa());
            button.buttonAction(player, player.getMap().getButton());
            DoorCollision.intersection(player, player.getMap().getDoor());
            EndLevelCollision.intersection(player, player.getMap().getEnd());
            break;
        default:
            break;
        }
    }

}
