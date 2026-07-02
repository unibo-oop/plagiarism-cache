package controller;

import model.Game;
import model.GameImpl;
import model.GameStatus;
import view.ArenaView;
import view.GameP;
import view.HighScoreP;
//import view.HighScoresPanel;
import view.View;
import view.StateV;

/**
 * The Class ControllerImpl it's a filter between the view and the Gameloop. It makes sure the view doesn't access directly
 * to the main thread of the gam.
 */
public class ControllerImpl implements Controller{
	
	/** The view. */
	private View view;
	
	/** The game loop. */
	private GameLoop gameLoop;
	
	/** The game. */
	private Game game;
	
	/** The highscore. */
	private final ScoreController highscore;
	
	
	/**
	 * Instantiates a new controller impl.
	 */
	public ControllerImpl() {
		this.highscore = new ScoreController();
	}
    
    /**
     * Start game.
     *
     * @throws IllegalStateException the illegal state exception
     */
    private void startGame() throws IllegalStateException {
        if (this.view == null) {
            throw new IllegalStateException();
        }
        this.game = new GameImpl();
        final Input input = new Input(game, this);
        this.gameLoop = new GameLoop(this.game, this.view,this.highscore,input);
        this.view.switchWindow(new ArenaView(input), ArenaView.TITLE);
        this.gameLoop.start();
    }
    
    /**
     * Update.
     *
     * @param gamePanel the game panel
     * @param viewStatus the view status
     */
    public void update(final GameP gamePanel, StateV viewStatus) {
        if (viewStatus.equals(StateV.HIGHSCORES) && gamePanel instanceof HighScoreP) {
            ((HighScoreP) gamePanel).setScores(highscore.getScore());
        } else
    	if (viewStatus.equals(StateV.RESUME)) {
            this.gameLoop.resume();
        } else if (viewStatus.equals(StateV.ABORT)) {
            this.gameLoop.abort();
        } else if (viewStatus.equals(StateV.START)) {
            this.startGame();
        } else if (viewStatus.equals(StateV.PAUSE)) {
            this.gameLoop.stop();
        }
    }
    
	/**
	 * Sets the view.
	 *
	 * @param view the new view
	 */
	@Override
	public void setView(View view) {
		this.view = view;
	}
	
	/**
	 * Check game status.
	 *
	 * @return the game status
	 */
	@Override
	public GameStatus checkGameStatus() {
		return this.game.getStatus();
	}


}
