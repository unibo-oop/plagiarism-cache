package it.tbt.model.statechange;

import it.tbt.model.fight.api.FightModel;
import it.tbt.model.shop.Shop;

/**
 * Interface implemented by the object who wants to be able to handle {@link it.tbt.model.GameState}.
 * transitions and define an appropriate response.
 */
public interface StateObserver {
    /**
     * Defines the action to be taken when the {@link it.tbt.model.GameState#EXPLORE} GameState is to be triggered.
     */
    void onExplore();
    /**
     * Defines the action to be taken when the {@link it.tbt.model.GameState#MENU} GameState is to be.
     * triggered.
     */
    void onMenu();
    /**
     * Defines the action to be taken when the {@link it.tbt.model.GameState#PAUSE} menu GameState is to be.
     * triggered.
     */
    void onPause();
    /**
     * Defines the action to be taken when the {@link it.tbt.model.GameState#FIGHT} GameState is to be triggered.
     * @param fightModel object modeling the fight.
     */
    void onFight(FightModel fightModel);
    /**
     * Defines the action to be taken when the {@link it.tbt.model.GameState#INVENTORY} GameState is to be triggered.
     */
    void onInventory();
    /**
     * Defines the action to be taken when the {@link it.tbt.model.GameState#SHOP} GameState is to be triggered.
     * @param shop
     */
    void onShop(Shop shop);
    /**
     * Defines the action to be taken when the {@link it.tbt.model.GameState#ENDING} GameState is to be triggered.
     * @param message
     */
    void onEnding(String message);
}
