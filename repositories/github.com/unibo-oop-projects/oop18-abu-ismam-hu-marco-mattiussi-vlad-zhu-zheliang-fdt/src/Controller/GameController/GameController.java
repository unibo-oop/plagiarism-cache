package controller.gamecontroller;

import model.GameModel;
import view.gameview.GameScreen;
import view.input.Input;
/**
 * Interface of the controller. Manages the 
 * Gameloop (start, pause/resume, kill),
 * handle inputs and difficulty.
 */
public interface GameController {
    /**
    * Starts the gameloop.
    */
    void init();

    /**
    * pauses the game.
    */
    void pauseGame();

    /**
    * resumes the game.
    */
    void resumeGame();
    /**
    * 
    * @return the data contained in the model.
    */
    GameModel getModel();
    /**
    * @param d difficulty of the game.
    * 1-easy
    * 2-normal
    * 3-hard
    */
    void setDifficulty(int d);

    /**
    * This method gets the input from the View and passes it to the GameLoop.
    * @param i gets the input from the view and pass it to the GameLoop
    */
    void handleInput(Input i);

    /**
     * Kills the gameloop.
     */
    void killGameLoop();
    /**
     * Starts the actual GameLoop.
     * @param v View of the game
     */
    void startLoop(GameScreen v);
}
