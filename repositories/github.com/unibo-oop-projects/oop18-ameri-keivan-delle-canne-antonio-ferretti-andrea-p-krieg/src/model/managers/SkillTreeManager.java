package model.managers;

import java.util.List;

import model.player.Player;
import model.skilltree.SkillTreeAttribute;

/**
 * The SkillTreeManager interface is an interface that describes the behavior of
 * a Skilltree manager. It has declaretion of methods to get all the attributes
 * of a Skilltree, to get the attributes that can be updated and to upgrade a
 * certain attribute of a given Skilltree.
 *
 */
public interface SkillTreeManager {

    /**
     * This method could be used to get all the SkillTreeAttribute of a Skilltree of
     * a given player.
     * 
     * @param player is the player that owns a the Skilltree.
     * @return a List of all SkillTreeAttribute in the player's Skilltree.
     */
    List<SkillTreeAttribute> getAllPlayerAttributes(Player player);

    /**
     * This method could be used to get the SkillTreeAttribute of a Skilltree of a
     * given player that can be upgrade.
     * 
     * @param player is the player that owns a the Skilltree.
     * @return a List of SkillTreeAttribute in the player's Skilltree that can be
     *         upgrade.
     */
    List<SkillTreeAttribute> getUpgradeblePlayerAttributes(Player player);

    /**
     * This method could be used to upgrade a given SkillTreeAttribute of a given
     * Player.
     * 
     * @param player    player who owns a Skilltree.
     * @param attribute is the SkillTreeAttribute of the player's Skilltree.
     */
    void upgradePlayerAttribute(Player player, SkillTreeAttribute attribute);

}
