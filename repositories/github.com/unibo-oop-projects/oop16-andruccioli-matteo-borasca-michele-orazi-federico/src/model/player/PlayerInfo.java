package model.player;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.aim.Aim;
import model.bonus.Bonus;
import model.bonus.BonusCard;
import model.region.Region;
import model.state.State;
import utils.enumerations.Color;
import utils.enumerations.ControlType;

/**
 * 
 * An interface that makes accessible the information of a Player.
 *
 */
public interface PlayerInfo extends Serializable {

    /**
     * @return The name of this Player.
     */
    String getName();

    /**
     * @return The Color associated to this Player.
     */
    Color getColor();

    /**
     * @return {@link ControlType}.AI if the player is controlled by AI,
     *         {@link ControlType}.HUMAN otherwise.
     */
    ControlType playerType();

    /**
     * @return a set of states occupied by the player.
     */
    Set<State> getStates();

    /**
     * @return a set of regions occupied by the player.
     */
    Set<Region> getRegions();

    /**
     * @return number of tanks still not deployed by the player during the
     *         "initialization" phase.
     */
    int getTotalTanksToDeploy();

    /**
     * @return amount of tanks which can be deployed this turn
     */
    int getTanksToDeploy();

    /**
     * @return The Aim associated to this Player.
     */
    Aim getAim();

    /**
     * @return a Map whose values contain all the player's bonusCards, it's a
     *         copy of the map contained in player Object.
     */
    Map<Bonus, List<BonusCard>> getBonusCards();

    /**
     * @return a List whose values contain all the player's bonusCards.
     */
    List<BonusCard> getBonusCardsList();

    /**
     * @return An instance of the extended interface.
     */
    Player forwarder();
}
