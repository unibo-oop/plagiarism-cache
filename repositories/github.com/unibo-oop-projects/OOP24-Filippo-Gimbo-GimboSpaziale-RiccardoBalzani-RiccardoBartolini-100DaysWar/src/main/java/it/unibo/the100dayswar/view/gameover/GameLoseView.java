package it.unibo.the100dayswar.view.gameover;

/**
 * The view that shows when the player loses the game.
 */
public class GameLoseView extends AbstractGameOverView {
    private static final long serialVersionUID = 1L;
    /** 
     * The constructor of the game lose view.
     */
    public GameLoseView() {
        super("Sorry, you lost!", "/gameover/lose.png");
    }
}
