package model.board.manager;

import model.board.Board;
import model.settings.GameSettings;
import utilities.FileManager;
import utilities.FileManagerInterface;

/**
 * This class manipulate the file for the game data.
 * Andrea Serafini.
 *
 */
public final class GameManager implements GameManagerInterface {

    private static GameManager manager;
    private final FileManagerInterface<Board> fileManager = new FileManager<>(GameSettings.getSavedGameFileName());
    private Board game;

    @Override
    public void deleteSavedGame() {
        this.fileManager.deleteFile();
    }

    @Override
    public Board getGame() {
        this.loadSavedGame();
        return this.game;
    }

    @Override
    public boolean isPresent() {
        return this.fileManager.isPresent();
    }

    @Override
    public void loadSavedGame() {
        this.fileManager.loadFile();
        this.game = this.fileManager.get();
    }

    @Override
    public void saveGame() {
        this.game = Board.getLog();
        this.fileManager.saveFile(this.game);
    }

    /**
    *
    * @return a gameManager instance
    */
   public static synchronized GameManager getLog() {
       if (manager == null) {
           manager = new GameManager();
       }
       return manager;
   }
}
