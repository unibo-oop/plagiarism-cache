package talisman.util;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import talisman.Controllers;

import talisman.controller.board.TalismanBoardController;
import talisman.controller.board.TalismanBoardControllerFactory;
import talisman.controller.cards.TalismanDeckControllerImpl;
import talisman.controller.character.CharacterControllerImpl;
import talisman.controller.character.CharactersController;
import talisman.controller.character.CurrentPlayerChoicesController;
import talisman.model.battle.CraftEnemy;
import talisman.model.battle.EnemyInfos;
import talisman.model.battle.EnemyModel;
import talisman.model.battle.StrengthEnemy;
import talisman.model.cards.DeckImpl;
import talisman.model.cards.DeckType;
import talisman.model.cards.TalismanDeckFactory;
import talisman.model.character.CharacterModel;
import talisman.model.character.CharacterModelImpl;
import talisman.model.character.defaultCharacters.TalismanCharacterFactory;
import talisman.model.menu.PlayerInfo;
import talisman.view.CurrentPlayerChoicesWindow;
import talisman.view.DebugView;
import talisman.view.GameVictoryWindow;
import talisman.view.GameWindow;

/**
 * Class used to start a new game. It functions as a bridge between the setup
 * menu entities and their respective game counterparts.
 * 
 * @author Alberto Arduini
 *
 */
public final class GameSetupUtil {
    private static final int STARTING_GOLD = 1;
    private static final boolean SHOW_DEBUG = false;
    private static final GameSetupUtil SINGLETON = new GameSetupUtil();

    private GameWindow mainWindow;
    private boolean ready;
    private EndedListener endedListener;

    /**
     * Listener used to wait for the game to end.
     * 
     * @author Alberto Arduini
     *
     */
    public interface EndedListener extends EventListener {
        /**
         * Called when the game ends.
         */
        void gameEnded();
    }

    private GameSetupUtil() {
        this.ready = false;
    }

    /**
     * Setups a new game.
     * 
     * @param playerInfos the list of players
     * @return this utility
     */
    public GameSetupUtil setupGame(final List<PlayerInfo> playerInfos) {
        // Setup character controller
        final CharactersController charactersController = new CharacterControllerImpl();
        Controllers.setCharactersController(charactersController);
        final int playerCount = playerInfos.size();
        final List<CharacterModel> characters = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            final CharacterModelImpl character = this.createIngameCharacter(playerInfos.get(i));
            characters.add(character);
            Controllers.getCharactersController().addPlayer(character);
        }
        // Setup enemies
        EnemyInfos.addEnemy(new StrengthEnemy(1, "Pitfiend"));
        EnemyInfos.addEnemy(new StrengthEnemy(1, "Wild Boar"));
        EnemyInfos.addEnemy(new StrengthEnemy(2, "Wolf"));
        EnemyInfos.addEnemy(new StrengthEnemy(4, "Serpent"));
        EnemyInfos.addEnemy(new StrengthEnemy(7, "Dragon"));
        EnemyInfos.addEnemy(new CraftEnemy(1, "Lemure"));
        EnemyInfos.addEnemy(new CraftEnemy(2, "Shadow"));
        EnemyInfos.addEnemy(new CraftEnemy(5, "Wraith"));

        // Setup board controller
        final TalismanBoardController boardController = TalismanBoardControllerFactory.createController(characters);
        Controllers.setBoardController(boardController);

        // Setup decks
        Controllers.setDeckController(DeckType.ADVENTURE, new TalismanDeckControllerImpl(DeckType.ADVENTURE).shuffle());
        Controllers.setDeckController(DeckType.TALISMAN, new TalismanDeckControllerImpl(DeckType.TALISMAN).shuffle());
        Controllers.setDeckController(DeckType.SHOP, new TalismanDeckControllerImpl(DeckType.SHOP).shuffle());

        // Setup window
        this.mainWindow = new GameWindow(boardController.getView());

        this.ready = true;
        return this;
    }

    /**
     * Starts the currently prepared game.
     * 
     * @return this utility
     */
    public GameSetupUtil startGame() {
        if (!this.ready) {
            throw new IllegalStateException("The game needs to be prepared with setupGame before starting");
        }

        this.mainWindow.setVisible(true);

        if (GameSetupUtil.SHOW_DEBUG) {
            final DebugView debugView = new DebugView(Controllers.getBoardController());
            debugView.setVisible(true);
        }

        Controllers.setChoiceController(CurrentPlayerChoicesController.create(0));

        return this;
    }

    /**
     * sets the listener that is waiting for the game to end.
     * 
     * @param endedListener the listener
     * @return this utility
     */
    public GameSetupUtil setClosedListener(final EndedListener endedListener) {
        this.endedListener = endedListener;
        return this;
    }

    /**
     * Stops the game and sets who the winner of the game is.
     * 
     * @param winner the game winner
     * @return this utility
     */
    public GameSetupUtil winGame(final int winner) {
        GameVictoryWindow.show(winner);
        return this;
    }

    /**
     * Ends the current game.
     * 
     * @return this utility
     */
    public GameSetupUtil endGame() {
        this.mainWindow.close();
        Controllers.getChoiceController().getView().closeWindow();
        Controllers.reset();
        this.ready = false;
        this.endedListener.gameEnded();
        return this;
    }

    private CharacterModelImpl createIngameCharacter(final PlayerInfo playerInfo) {
        final CharacterModelImpl character;
        switch (playerInfo.getCharacter()) {
        case ASSASSIN:
            character = TalismanCharacterFactory.createAssassinCharacter();
            break;
        case DRUID:
            character = TalismanCharacterFactory.createDruidCharacter();
            break;
        case DWARF:
            character = TalismanCharacterFactory.createDwarfCharacter();
            break;
        case ELF:
            character = TalismanCharacterFactory.createElfCharacter();
            break;
        case GHOUL:
            character = TalismanCharacterFactory.createGhoulCharacter();
            break;
        default:
            return null;
        }
        // Since the starting gold is the same for all characters i set it here
        character.setGold(GameSetupUtil.STARTING_GOLD);
        return character;
    }

    /**
     * Gets the singleton instance of this utility.
     * 
     * @return the singleton instance
     */
    public static GameSetupUtil getSingleton() {
        return GameSetupUtil.SINGLETON;
    }
}
