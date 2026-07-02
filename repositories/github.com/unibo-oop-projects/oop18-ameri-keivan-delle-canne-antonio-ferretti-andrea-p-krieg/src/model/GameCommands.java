package model;

import java.util.Optional;
import java.util.Set;

import model.map.ObservableGameMap;
import model.objects.unit.Unit;
import model.player.Player;
import model.resources.Resource;
import util.Coordinates;

/**
 * this interface is used to play the game. it allows the player to: -create
 * units -move units around the map and make them interact with it -pass the
 * turn -upgrade the skill-tree
 * 
 * it also allows to retrieve information useful to the game such as: -player
 * objective -player resources -actual state of the game map -information about
 * the objects on the game map
 */
public interface GameCommands extends ObservableGameMap, SkillTreeCommands, TurnManagementCommands {

    // ----------------------Action----------------------------------------

    /**
     * the unit on from moves on to, beware, after movement the unit can change
     * since the unit conquers any structure on it if present and steps on a vehicle
     * if a vehicle producer is there.
     * 
     * @param from actual unit position
     * @param to   destination
     * @throws IllegalArgumentException
     */
    void moveUnit(Coordinates from, Coordinates to);

    /**
     * 
     * @param attackerCords the attacker position
     * @param defenderCords the defender position
     * @return the coordinates of the attacker unit after the fight if still alive
     * @throws IllegalArgumentException if any of the passed coordinates doesn't
     *                                  have unit on them or the defender isn't the
     *                                  the attackers range
     */
    Optional<Coordinates> makeUnitFight(Coordinates attackerCords, Coordinates defenderCords);

    /**
     * @param unitCords the unit position
     * @return the coordinates the unit can move to
     */
    Set<Coordinates> getValidUnitMovements(Coordinates unitCords);

    /**
     * @param unitCords the unit position
     * @return the coordinates the unit can attack
     */
    Set<Coordinates> getValidUnitAttacks(Coordinates unitCords);

    // ------------------------------------Creation---------------------------------

    /**
     * This method could be used to create an unit from a selected city.
     * 
     * @param cityPosition the position of the city where to create the unit;
     * @param unit         is the unit to create.
     */
    void createUnitFromCity(Unit unit, Coordinates cityPosition);

    /**
     * This method could be used to verify if an unit can be create from a given
     * city. It also verify that the player can't create more than one kind of Hero
     * for each Hero type.
     * 
     * @param unit         is the unit to verify.
     * 
     * @param cityPosition is the position of the city.
     * 
     * @return true if the unit can be created.
     */
    boolean canCreateUnitFromCity(Unit unit, Coordinates cityPosition);

    /**
     * This method could be used to verify if a city can create. A city can create
     * if is the turn of its owner and there isn't someone over the city.
     * 
     * @param cityPosition the position of the city to verify.
     * @return true if city's owner is the actual player and there isn't an unit
     *         over the city.
     */
    boolean canCityCreate(Coordinates cityPosition);

    /**
     * @param player   the player owning the resources
     * 
     * @param resource the resource to be checked
     * 
     * @return the quantity of this resource for the player
     */
    int getResourceQuantityForPlayer(Player player, Resource resource);

}
