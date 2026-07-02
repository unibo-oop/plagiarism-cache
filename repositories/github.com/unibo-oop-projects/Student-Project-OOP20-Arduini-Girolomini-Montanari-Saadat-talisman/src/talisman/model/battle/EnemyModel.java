package talisman.model.battle;

import talisman.model.character.CharacterModel;

/**
 * Interface of enemy's informations in battle.
 * 
 * @author Alice Girolomini
 *
 */
public interface EnemyModel extends CharacterModel {
    /**
     * Gets the enemie's name.
     * 
     * @return the name
     */
    String getName();
}
