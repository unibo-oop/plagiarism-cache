package it.unibo.the100dayswar.view.gameover;

/**
 * The view that shows when the player wins the game.
 */
public class GameWinView extends AbstractGameOverView {
    private static final long serialVersionUID = 1L;
    /** 
     * The constructor of the game win view.
     */
    public GameWinView() {
        super("Congrats, you won!", "/gameover/win.png");
    }
}
