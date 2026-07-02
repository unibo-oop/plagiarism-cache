package model.skilltree;

import model.Cost;

/**
 * The SkillTreeAttribute interface represent an attribute of a SkillTree. A
 * SkillTreeAttributes has a name, a current level and can has a next level and
 * a cost of the next level. If a next level is present, the attribute can be
 * upgrade to this level.
 */
public interface SkillTreeAttribute {

    /**
     * This method could be use to get the attribute's name.
     * 
     * @return the attribute's name.
     */
    String getAttributeName();

    /**
     * This method can be use to upgrade the level of the attribute.
     */
    void upgrade();

    /**
     * This method can be use to verify if an attribute can be upgrade.
     * 
     * @return true if a next level of the attribute is present.
     */
    boolean canUpgrade();

    /**
     * This method can be used to get the upgrade's cost.
     * @return the upgrade's cost.
     */
    Cost getCost();

    /**
     * This method can be used to get the upgrade's cost to string version.
     * @return the upgrade's cost to string version.
     */
    String getCostToString();

}
