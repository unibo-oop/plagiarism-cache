package clashclass.battle.battlereport;

import clashclass.resources.ResourceManager;

/**
 * Implementation of the BattleReportView interface.
 * Handles displaying the battle report to the user in a Clash of Clans style.
 */
public class BattleReportViewImpl implements BattleReportView {
    // Constants for star display
    // private static final String STAR_FILLED = "★";
    // private static final String STAR_EMPTY = "☆";

    /**
     * {@inheritDoc}
     * Updates all display elements with the current model data.
     */
    @Override
    public void update(final BattleReportModel model) {
        displayDestructionPercentage(model.getDestructionPercentage());
        displayStars(model.getStars());
        displayStolenResources(model.getStolenResources());
        displayBattleResult(model.isVictory());
        displayTroopCount(model.getTroopCount());
    }

    /**
     * {@inheritDoc}
     * Displays the destruction percentage in a formatted way.
     */
    @Override
    public void displayDestructionPercentage(final double percentage) {
        // System.out.println("Destruction: " + String.format("%.1f", percentage) + "%");
    }

    /**
     * {@inheritDoc}
     * Displays the stars earned in the battle in a Clash of Clans style.
     * Example: ★★☆ for 2 stars.
     */
    @Override
    public void displayStars(final int stars) {
        // final StringBuilder starDisplay = new StringBuilder();

        // Add filled stars
        // for (int i = 0; i < stars; i++) {
        //starDisplay.append(STAR_FILLED);
        //}
        //}

        // Add empty stars
        // for (int i = stars; i < 3; i++) {
        //    starDisplay.append(STAR_EMPTY);
        //}

        // System.out.println("Stars: " + starDisplay.toString());
    }

    /**
     * {@inheritDoc}
     * Displays the resources stolen during the battle.
     */
    @Override
    public void displayStolenResources(final ResourceManager resources) {
        // System.out.println("Resources stolen:");
        // // System.out.println("  Gold: " + resources.getGold());
        // // System.out.println("  Elixir: " + resources.getElixir());
    }

    /**
     * {@inheritDoc}
     * Displays the battle result (victory or defeat).
     */
    @Override
    public void displayBattleResult(final boolean isVictory) {
        //if (isVictory) {
            // System.out.println("VICTORY!");
        //} else {
            // System.out.println("DEFEAT!");
        //}
    }

    /**
     * {@inheritDoc}
     * Displays the count of troops used in the battle.
     */
    @Override
    public void displayTroopCount(final int troopCount) {
        // System.out.println("Troops used: " + troopCount);
    }

    @Override
    public void show() {

    }

    @Override
    public void clearScene() {

    }

    @Override
    public void setController(final BattleReportController controller) {

    }

    // /**
    // * Displays a complete battle report summary.
    //  */
    //private void displayBattleReportSummary() {
    // System.out.println("=== BATTLE REPORT ===");

    // Display stars
    //final StringBuilder starDisplay = new StringBuilder();
    //for (int i = 0; i < model.getStars(); i++) {
    //   starDisplay.append(STAR_FILLED);
    //}
    //for (int i = model.getStars(); i < 3; i++) {
    //    starDisplay.append(STAR_EMPTY);
    //}
    // System.out.println(starDisplay.toString());

    // Display destruction percentage
    // System.out.println("Destruction: " + String.format("%.1f", model.getDestructionPercentage()) + "%");

    // Display resources
    // ResourceManager resources = model.getStolenResources();
    // System.out.println("Resources stolen:");
    // // System.out.println("  Gold: " + resources.getGold());
    // // System.out.println("  Elixir: " + resources.getElixir());

    // Display troop count
    // System.out.println("Troops used: " + model.getTroopCount());

    // Display result
    // if (model.isVictory()) {
    //      System.out.println("VICTORY!");
    //} else {
    //      System.out.println("DEFEAT!");
    //}

    // System.out.println("=====================");
    //}
}
