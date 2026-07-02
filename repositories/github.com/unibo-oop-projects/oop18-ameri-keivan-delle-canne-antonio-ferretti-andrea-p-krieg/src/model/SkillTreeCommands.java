package model;

import java.util.List;

import model.objects.unit.Unit;
import model.skilltree.SkillTreeAttribute;

/**
 * The SkillTreeCommands interface is an interface that contains commands
 * affects the skill-tree. This interface contains methods for managing
 * SkillTreeAttributes and their particular uses.
 */
public interface SkillTreeCommands {

    /**
     * This method could be used to get all the possible unit that the player that
     * are playing his turn can create.
     * 
     * @return a list of all possible unit that the current player can create .
     */
    List<Unit> getPossibleUnit();

    /**
     * This method could be used to get all the attributes of the current player
     * that can be upgrade.
     * 
     * @return a list of skill attributes that can be upgrade.
     */
    List<SkillTreeAttribute> getSkillTreeUpgradableAttribute();

    /**
     * This method could be used to verify if an attribute can be upgrade.
     * 
     * @param attributeToUpgrade is the attribute to verify.
     * @return true if attributeToUpgrade could be upgrade.
     */
    boolean canUpgradeAttribute(SkillTreeAttribute attributeToUpgrade);

    /**
     * This method could be used to upgrade an attribute.
     * 
     * @param attribute is the attribute to upgrade
     */
    void upgradeAttribute(SkillTreeAttribute attribute);

}
