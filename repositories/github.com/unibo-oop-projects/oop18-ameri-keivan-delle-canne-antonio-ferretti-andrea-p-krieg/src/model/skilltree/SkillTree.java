package model.skilltree;

import java.util.List;

/**
 * The SkillTree interface represent a skill tree. A skill tree has some
 * attributes and methods for update or get it.
 */
public interface SkillTree {

    /**
     * This method could be used to get all the SkillTreeAttribute of a Skilltree.
     * 
     * @return a List of all SkillTreeAttribute in the player's Skilltree.
     */
    List<SkillTreeAttribute> getAllAttributes();

    /**
     * This method could be used to get the SkillTreeAttribute of a Skilltree that
     * can be upgrade.
     * 
     * @return a List of SkillTreeAttribute in the player's Skilltree that can be
     *         upgrade.
     */
    List<SkillTreeAttribute> getUpgradebleAttributes();

    /**
     * This method could be used to upgrade a given SkillTreeAttribute.
     * 
     * @param attribute is the SkillTreeAttribute of the player's Skilltree.
     */
    void upgradeAttribute(SkillTreeAttribute attribute);

}
