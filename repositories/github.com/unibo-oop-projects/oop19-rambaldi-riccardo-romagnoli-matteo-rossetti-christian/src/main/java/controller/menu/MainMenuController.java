package controller.menu;

/**
 * MainMenuController manages the behavior of main menu buttons when clicked.
 */
public interface MainMenuController {

    /**
     * Manages the behavior of play button by opening the choice menu.
     */
    void playHit();

    /**
     * Manages the behavior of leaderboard button by opening the leaderboard.
     */
    void leaderboardHit();
}
