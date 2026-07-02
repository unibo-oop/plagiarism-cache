package unibo.exiled.model.game;

import unibo.exiled.model.character.CharacterModel;
import unibo.exiled.model.character.CharacterModelImpl;
import unibo.exiled.model.combat.CombatModel;
import unibo.exiled.model.combat.CombatModelImpl;
import unibo.exiled.model.item.ItemsModel;
import unibo.exiled.model.item.ItemsModelImpl;
import unibo.exiled.model.map.MapModel;
import unibo.exiled.model.map.MapModelImpl;
import unibo.exiled.model.menu.MenuModel;
import unibo.exiled.model.menu.MenuModelImpl;

/**
 * The implementation of the game core.
 */
public final class GameModelImpl implements GameModel {
    private final MapModel mapModel;
    private final MenuModel menuModel;
    private final ItemsModel itemsModel;
    private final CharacterModel characterModel;
    private final CombatModel combatModel;

    /**
     * The constructor of the game core.
     */
    public GameModelImpl() {
        this.menuModel = new MenuModelImpl();
        this.mapModel = new MapModelImpl(this);
        this.itemsModel = new ItemsModelImpl(this);
        this.characterModel = new CharacterModelImpl(this);
        this.combatModel = new CombatModelImpl(this);
    }

    @Override
    public MapModel getMapModel() {
        return this.mapModel;
    }

    @Override
    public MenuModel getMenuModel() {
        return this.menuModel;
    }

    @Override
    public ItemsModel getItemsModel() {
        return this.itemsModel;
    }

    @Override
    public CharacterModel getCharacterModel() {
        return this.characterModel;
    }

    @Override
    public CombatModel getCombatModel() {
        return this.combatModel;
    }
}
