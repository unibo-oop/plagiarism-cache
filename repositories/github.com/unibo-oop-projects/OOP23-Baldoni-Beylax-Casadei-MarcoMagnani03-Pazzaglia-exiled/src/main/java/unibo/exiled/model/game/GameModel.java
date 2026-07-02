package unibo.exiled.model.game;

import unibo.exiled.model.character.CharacterModel;
import unibo.exiled.model.combat.CombatModel;
import unibo.exiled.model.item.ItemsModel;
import unibo.exiled.model.map.MapModel;
import unibo.exiled.model.menu.MenuModel;

/**
 * The model of the game, its core.
 */
public interface GameModel {
    /**
     * Gets the MapModel.
     *
     * @return The MapModel.
     */
    MapModel getMapModel();

    /**
     * Gets the character model.
     *
     * @return The CharacterModel.
     */
    CharacterModel getCharacterModel();

    /**
     * Gets the Items Model.
     *
     * @return The Items Model.
     */
    ItemsModel getItemsModel();

    /**
     * Gets the Menu Model.
     *
     * @return A MenuModel.
     */
    MenuModel getMenuModel();

    /**
     * Gets the Combat Model.
     *
     * @return A CombatModel.
     */
    CombatModel getCombatModel();
}
