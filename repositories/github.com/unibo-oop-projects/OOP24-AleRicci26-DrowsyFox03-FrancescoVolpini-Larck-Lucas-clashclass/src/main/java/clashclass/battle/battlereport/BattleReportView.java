package clashclass.battle.battlereport;

import clashclass.resources.ResourceManager;

/**
 * Interface for the Battle Report View.
 * Defines how the battle report is displayed to the user.
 */
public interface BattleReportView {

    /**
     * Update the view with the current model data.
     *
     * @param model The BattleReportModel containing the data to display
     */
    void update(BattleReportModel model);

    /**
     * Display the destruction percentage.
     *
     * @param percentage The destruction percentage to display
     */
    void displayDestructionPercentage(double percentage);

    /**
     * Display the stars earned in the battle.
     *
     * @param stars The number of stars (0-3)
     */
    void displayStars(int stars);

    /**
     * Display the resources stolen during the battle.
     *
     * @param resources The ResourceManager containing stolen resources
     */
    void displayStolenResources(ResourceManager resources);

    /**
     * Display the battle result (victory or defeat).
     *
     * @param isVictory true if the battle was won, false otherwise
     */
    void displayBattleResult(boolean isVictory);

    /**
     * Display the count of troops used in the battle.
     *
     * @param troopCount The number of troops used
     */
    void displayTroopCount(int troopCount);

    /**
     * Shows the menu.
     */
    void show();

    /**
     * Clears the scene.
     */
    void clearScene();

    /**
     * Sets the battle report controller reference.
     *
     * @param controller the battle report controller
     */
    void setController(BattleReportController controller);
}
