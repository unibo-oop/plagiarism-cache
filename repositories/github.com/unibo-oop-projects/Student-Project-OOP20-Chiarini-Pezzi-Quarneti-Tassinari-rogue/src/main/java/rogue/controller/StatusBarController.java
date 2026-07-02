package rogue.controller;

import rogue.model.creature.PlayerLife;
import rogue.model.events.EquipmentEvent;
import rogue.model.events.LifeEvent;
import rogue.view.StatusBarView;

/**
 * An interface modeling the status bar controller.
 */
public interface StatusBarController {

    /**
     * Notifies that the {@link PlayerLife} changed.
     * @param event
     *          the {@link LifeEvent} associated to the life change
     */
    void onLifeChange(LifeEvent<PlayerLife> event);

    /**
     * Notifies that the {@link Equipment} changed.
     * @param event
     *          the {@link EquipmentEvent} associated to the equipment change
     */
    void onEquipmentChange(EquipmentEvent event);

    /**
     * @return the associated {@link StatusBarView}
     */
    StatusBarView getView();

}
