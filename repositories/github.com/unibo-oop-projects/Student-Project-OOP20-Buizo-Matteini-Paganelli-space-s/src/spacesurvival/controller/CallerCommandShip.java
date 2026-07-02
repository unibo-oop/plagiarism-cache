package spacesurvival.controller;

import spacesurvival.model.commandship.command.CommandShip;
import spacesurvival.model.commandship.command.implementation.Fire;
import spacesurvival.model.commandship.command.implementation.RotateLeft;
import spacesurvival.model.commandship.command.implementation.RotateRight;
import spacesurvival.model.commandship.command.implementation.Up;
import spacesurvival.model.commandship.command.implementation.UpRelease;
import spacesurvival.model.gameobject.fireable.SpaceShipSingleton;
import spacesurvival.utilities.CommandKey;

/**
 * Caller command for the ship.
 */
public class CallerCommandShip {
    private final CommandShip cmdUp;
    private final CommandShip cmdReleaseUp;
    private final CommandShip cmdRotateLeft;
    private final CommandShip cmdRotateRight;
    private final CommandShip cmdShot;

    private final SpaceShipSingleton ship;


    /** 
     * Constructor for CallerCommand, initialize a new CallerCommand for the spaceship.
     * 
     * @param ship ship on which to execute the commands.
     */
    public CallerCommandShip(final SpaceShipSingleton ship) {
        this.cmdUp = new Up();
        this.cmdReleaseUp = new UpRelease();
        this.cmdRotateLeft = new RotateLeft();
        this.cmdRotateRight = new RotateRight();
        this.cmdShot = new Fire();
        this.ship = ship;
    }

    /** 
     * Read the passed command and execute the command on the specified concrete command.
     * 
     * @param cmd the command to execute
     */
    public void execute(final CommandKey cmd) {
        switch (cmd) {
        case KEY_UP_PRESSED:
            cmdUp.execute(ship);
            break;
        case KEY_UP_RELEASED:
            cmdReleaseUp.execute(ship);
            break;
        case KEY_W_PRESSED:
            cmdUp.execute(ship);
            break;
        case KEY_W_RELEASED:
            cmdReleaseUp.execute(ship);
            break;
        case KEY_LEFT:
            cmdRotateLeft.execute(ship);
            break;
        case KEY_A:
            cmdRotateLeft.execute(ship);
            break;
        case KEY_RIGHT:
            cmdRotateRight.execute(ship);
            break;
        case KEY_D:
            cmdRotateRight.execute(ship);
            break;
        case KEY_SPACE_BAR:
            cmdShot.execute(ship);
            break;
        case KEY_K:
            cmdShot.execute(ship);
            break;
        default:
            break;
        }
    }

}
