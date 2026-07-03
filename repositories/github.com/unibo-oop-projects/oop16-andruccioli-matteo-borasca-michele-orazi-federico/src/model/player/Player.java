package model.player;

import java.util.Collection;
import java.util.List;

import model.aim.Aim;
import model.bonus.BonusCard;
import model.region.Region;
import model.state.State;

/**
 * 
 * The interface models the concept of Player in Risk, this interface offers
 * different methods to access the various fields of the class.
 *
 */

public interface Player extends PlayerInfo {

    /**
     * Set number of tanks the player owns at the beginning of "initialization".
     * phase.
     * 
     * @param nTanks
     *            set n° of tanks the player owns when game starts
     */
    void setTotalTanksToDeploy(int nTanks);

    /**
     * This method sets the Aim associated to this Player if the field is empty.
     * 
     * @param aim
     *            aim assigned to the player.
     */
    void setAim(Aim aim);

    /**
     * this method decreases value of field "TotalTanksToDeploy" of an amount of
     * tanks equals to nTank.
     * 
     * @param nTanks
     *            amount of tanks deployed
     */
    void decreaseTotalTanksToDeploy(int nTanks);

    /**
     * this method sets amount of tanks player must deploy at the beginning of
     * his turn.
     * 
     * @param tanksToDeploy
     *            amount of tanks available
     */
    void addTanksToDeploy(int tanksToDeploy);

    /**
     * this method sets amount of tanks player must deploy at the beginning of
     * his turn at 0.
     */
    void resetTanksToDeploy();

    /**
     * this method adds a state to the list of player's dominions, and updates
     * the current owner of the state in stateImpl.
     * 
     * @param state
     *            last state conquered by the player
     */
    void addState(State state);

    /**
     * this method removes a state from the list of player's dominions.
     * 
     * @param state
     *            last state lost by the player
     */
    void removeState(State state);

    /**
     * this method adds a region to the list of regions totally conquered by the
     * player.
     * 
     * @param region
     *            last region totally conquered by the player
     */
    void addRegion(Region region);

    /**
     * this method removes a region from the list of regions totally conquered
     * by the player.
     * 
     * @param region
     *            a region which belongs no more to this player
     */
    void removeRegion(Region region);

    /**
     * this method adds a BonusCard to the map of BonusCards owned by the
     * player.
     * 
     * @param bonusCard
     *            bonusCard assigned to the player.
     */
    void addBonusCard(BonusCard bonusCard);

    /**
     * this method adds a collection of BonusCard to the map of BonusCards owned
     * by the player.
     * 
     * @param bonusCardColl
     *            Collection of bonusCard assigned to the player.
     */
    void addBonusCardCollection(Collection<BonusCard> bonusCardColl);

    /**
     * this method removes a BonusCard combo from the map of BonusCards owned by
     * the player.
     * 
     * @param combo
     *            combo the player wants to trade.
     */
    void removeCombo(List<BonusCard> combo);

    /**
     * this method removes all the BonusCard from the map of BonusCards owned by
     * the player. this method can be called only if the player lost.
     * 
     */
    void removeAllBonusCard();

}
