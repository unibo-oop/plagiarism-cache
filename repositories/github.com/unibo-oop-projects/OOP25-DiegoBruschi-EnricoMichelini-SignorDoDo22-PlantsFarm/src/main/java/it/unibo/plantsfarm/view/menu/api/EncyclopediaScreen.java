package it.unibo.plantsfarm.view.menu.api;

import java.awt.event.ActionListener;
import java.util.List;

/**
 * Interface defining Encyclopedia view component.
 */
public interface EncyclopediaScreen {

    /**
     * Updates rarity badge.
     *
     * @param rarity the name of the rarity.
     */
    void setRarity(String rarity);

    /**
     * Shows the screen.
     *
     * @param names names list.
     * @param unlocked unlock status list.
     * @param listener controller listener.
     * @param stageCommand the stage command.
     */
    void show(List<String> names, List<Boolean> unlocked,
            ActionListener listener, String stageCommand);

    /**
     * Updates image and returns true if successful.
     *
     * @param name plant name.
     * @param stage stage index.
     * @return true if icon was found, false otherwise.
     */
    boolean updateStageImage(String name, int stage);

    /**
     * Updates details.
     *
     * @param name plant name.
     * @param description plant description.
     */
    void updateDetails(String name, String description);

}
