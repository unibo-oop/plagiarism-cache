package view;

import controller.after_battle.AfterBattleUIController;
import controller.global_statistics.GlobalStatisticsUIController;
import javafx.stage.Stage;

/**
 * This interface is responsible for
 * the alternation of
 * {@link Scene} into the main {@link Stage}.
 */
public interface MasterView {

    /**
     * Sets the primary stage for the application.
     * @param stage the primary stage
     */
    void setPrimaryStage(Stage stage);

    /**
     * Getter for the primary stage.
     * @return the primary stage
     */
    Stage getPrimaryStage();

    /**
     * Shows the main menu view.
     */
    void showMainMenu();

    /**
     * Shows the navy setup view.
     */
    void showNavySetup();

    /**
     * Shows the select player view.
     */
    void showSelectPlayer();

    /**
     * Shows the navy disposal view.
     */
    void showNavyDisposal();

    /**
     * Shows the after battle view.
     * @param controller the controller needed by the UI
     */
    void showEndBattle(AfterBattleUIController controller);

    /**
     * Shows the after battle view.
     * @param controller the controller needed by the UI
     */
    void showGlobalStatistics(GlobalStatisticsUIController controller);
    /**
     * Shows a pop-up in order to view information from development team members.
     */
    void credits();
    /**
     * Shows the game ui view.
     */
    void showGameUI();

    /**
     * Shows a ui for input some file path.
     */
    void initialSetup();
}
