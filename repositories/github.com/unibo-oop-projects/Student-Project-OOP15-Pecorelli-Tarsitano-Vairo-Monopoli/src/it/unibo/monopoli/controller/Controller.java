package it.unibo.monopoli.controller;

import java.util.List;
import java.util.Map;

import it.unibo.monopoli.model.mainunits.Bank;
import it.unibo.monopoli.model.mainunits.ClassicPawn;
import it.unibo.monopoli.model.mainunits.Pawn;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.view.InPlay;

/**
 * This is an interface represents the contract for all action used by GUI.
 */
public interface Controller {

    /**
     * This method add {@link Player} in a list.
     * 
     * @param name
     *            -name of the {@link Player}
     * @param pawn
     *            -{@link Pawn} of the {@link Player}
     * @param isHuman
     *            -boolean for type of {@link Player}
     */
    void addPlayer(String name, ClassicPawn pawn, boolean isHuman);

    /**
     * This method allow to get the possible actions to do.
     * 
     * @param box
     *            -{@link Box} of actual position.
     * @param player
     *            -{@link Player} of actual player.
     * @return -List of {@link Actions}s to do.
     */
    List<Actions> getNextBoxsActions(final Box box, final Player player);

    /**
     * This method is a setter for the actual position.
     * 
     * @param position
     *            .
     */
    void setActualPosition(int position);

    /**
     * This method is a starter for the game. This method initialize all the
     * field used during the game.
     * 
     * @param versionEnum
     *            -{@link EVersion} the actual version of the game.
     */
    void initializedVersion(EVersion versionEnum);

    /**
     * This method ad a {@link InPlay} view for Controller.
     * 
     * @param view
     *            .
     */
    void addView(InPlay view);

    /**
     * This method allow to get set of all Box.
     * 
     * @return Set of {@link Box}s.
     */
    List<Box> getAllBoxes();

    /**
     * This method allow to get Bank.
     * 
     * @return {@link Bank}.
     */
    Bank getBank();

    /**
     * This method get the list of player {@link Player}.
     * 
     * @return the list <@link List> of <@link Player>
     */
    List<Player> getPlayers();

    /**
     * This method allow to roll dice.
     * 
     * @return the list of {@link Dices} rolled;
     */
    int toRollDices();

    /**
     * This method allow to set position for next player.
     */
    void endTurn();

    /**
     * This method is a getter for the actual box used during the game.
     * 
     * @return actual {@link Box}.
     */
    Box getActualBox();

    /**
     * This method is used for buy an ownership.
     */
    void buyOwnership();

    /**
     * This method is used for sell an ownership. .
     */
    void sellOwnership();

    /**
     * This method allow to build house or hotel.
     */
    void build();

    /**
     * This method allow to sell building.
     */
    void sellBuilding();

    /**
     * This method is used for mortgage an ownership. .
     */
    void mortgageOwnership();

    /**
     * This method is used for revoke a mortgage of ownership.
     */
    void revokeMortgageOwnership();

    /**
     * Returns the winner!
     * 
     * @return - map with {@link Player} as key and money of all property as
     *         value.
     */
    Map<Player, Integer> endGame();

    /**
     * This method is a getter of actual {@link Player}.
     * 
     * @return {@link Player}.
     */
    Player getActualPlayer();

    /**
     * This method is a getter of actual position id.
     * 
     * @return the id of actual position.
     */
    int getActualPosition();

    /**
     * This is a method for start the game.
     * @param version -the version of the game.
     */
    void play(EVersion version);

}
