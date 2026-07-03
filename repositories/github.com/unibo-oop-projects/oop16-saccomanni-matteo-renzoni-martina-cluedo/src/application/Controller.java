package application;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import model.GameInit;
import model.CareMementoTaker;
import model.Model;
import model.ModelMemento;
import model.cards.Card;
import model.cards.Solution;
import model.cards.SolutionImpl;
import model.player.PlayerInfo;
import utilities.Pair;
import utilities.enumerations.CharacterCard;
import utilities.enumerations.IconType;
import utilities.enumerations.PlayerType;
import utilities.enumerations.RoomCard;
import utilities.enumerations.WeaponCard;
import view.View;
import view.ViewImpl;
import view.dialogs.CardsDialog;
import view.dialogs.GuessAccuseDialog;
import view.dialogs.SaveDialog;
import view.dialogs.UploadDialog;
import view.scenes.BoardScene;
import view.scenes.Clues;
import view.scenes.MenuScene;

/**
 * Class Controller. There are all the methods for the union of model and view.
 */
public final class Controller implements ViewObserver {

    private static final String SAVING_DIR_PATH = System.getProperty("user.home") + System.getProperty("file.separator")
            + ".cluedo" + System.getProperty("file.separator");
    private static final Controller SINGLETON = new Controller();
    private Model model;
    private final View view;
    private int dado;
    private final AudioPlayer backgroundMusic;

    private Controller() {
        this.model = null;
        this.view = new ViewImpl();
        final File dir = new File(SAVING_DIR_PATH);
        if (!dir.exists()) {
            final boolean success = dir.mkdir();
            if (!success) {
                view.drawNotification("Cannot create the folder for saves", IconType.ERROR);
            }
        }
        this.backgroundMusic = new AudioPlayer("/sounds/song.wav");
        this.backgroundMusic.play(true);
        dado = 0;
    }

    /**
     * Method that return the only one instance of Controller.
     * 
     * @return instance of Controller.
     */
    public static Controller getController() {
        return SINGLETON;
    }

    private void setBackgroundMusic() {
        MenuScene.setMusic(backgroundMusic);
    }

    /**
     * View initialization to start the user interface.
     * 
     * @param args
     *            parameter to pass at the view
     */
    public void init(final String... args) {
        this.setBackgroundMusic();
        view.init(args);
    }

    @Override
    public void upload() {
        if (!savedGameList().isEmpty()) {
            UploadDialog.getDialog().createUploadDialog(savedGameList());
        } else {
            view.drawNotification("There aren't saved games to resume, create a new one.\nClick the NEW GAME button!",
                    IconType.INFO);
        }
    }

    @Override
    public void guessClick() {
        if (model != null) {
            if (!(model.getCurrentPlayer().canSuspect())) {
                view.drawNotification("You can't make an hypothesis now: you aren't in a room or you "
                        + "have already made an hypothesis during this round", IconType.ERROR);
            } else {
                GuessAccuseDialog.getDialog().createGuessAccuseDialog(true, model.getCurrentPlayer());
            }
        }
    }

    @Override
    public void accuseClick() {
        if (model != null) {
            if (!(model.getCurrentPlayer().getRoom().isPresent())) {
                view.drawNotification("You can't accuse now: you aren't in a room", IconType.ERROR);
            } else {
                GuessAccuseDialog.getDialog().createGuessAccuseDialog(false, model.getCurrentPlayer());
            }
        }
    }

    @Override
    public void setupNewGame(final Map<CharacterCard, PlayerType> m) {
        if (m.size() < 3) {
            view.drawNotification("You have to select at least 3 characters", IconType.ERROR);
        } else if (model != null) {
            view.drawNotification("Game already inizialized", IconType.ERROR);
        } else {
            try {
                model = GameInit.getInstance().initGame(m);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (model != null) {
                final List<CharacterCard> characters = this.model.getPlayers().stream().map(player -> player.getName())
                        .collect(Collectors.toList());
                view.drawNotification(
                        "Players will play according to the following rounds:\n\n" + Joiner.on("\n").join(characters),
                        IconType.INFO);
                for (final PlayerInfo p : model.getPlayers()) {
                    p.addHistoryEvent(
                            "Players play according to the following rounds: " + Joiner.on(", ").join(characters));
                    p.addHistoryEvent("Your cards are: " + Joiner.on(", ").join(p.getCards()));
                    if (!this.model.getCommonCards().isEmpty()) {
                        p.addHistoryEvent("Common cards known to all players are: "
                                + Joiner.on(", ").join(this.model.getCommonCards()));
                    }
                }
                this.view.switchBoardScene(model.getPlayers());
                view.updateNextPlayerView(this.model.getCurrentPlayer());
                view.drawNotification("Now it's the turn of " + model.getCurrentPlayer().getName(), IconType.INFO);
                BoardScene.showLeftToolbar(this.model.getCurrentPlayer().getType().equals(PlayerType.HUMAN));
                this.playGame();
            }
        }
    }

    @Override
    public void quit() {
        SaveDialog.getDialog().createSaveDialog();
    }

    @Override
    public void save() {
        if (this.model.getCurrentPlayer().getType().equals(PlayerType.HUMAN)) {
            this.model.getCurrentPlayer().setClues(Clues.getNotes());
            this.model.getCurrentPlayer().setNotes(BoardScene.getTextArea());
        }
        this.model.saveGame();
        String nameFile = "Cluedo_" + Instant.now();
        nameFile = nameFile.trim();
        nameFile = nameFile.replace(':', '-');
        nameFile = nameFile.substring(0, nameFile.lastIndexOf('.'));
        nameFile = SAVING_DIR_PATH + nameFile;
        try {
            final OutputStream file = new FileOutputStream(nameFile);
            final OutputStream bstream = new BufferedOutputStream(file);
            final ObjectOutputStream ostream = new ObjectOutputStream(bstream);
            ostream.writeObject(CareMementoTaker.getInstance().getMemento());
            ostream.close();
        } catch (Exception e) {
            view.drawNotification("Error during the file saving.", IconType.ERROR);
        }
    }

    @Override
    public void endTurn() {
        BoardScene.showLeftToolbar(false);
        if (this.model.getCurrentPlayer().getType().equals(PlayerType.HUMAN)) {
            this.model.getCurrentPlayer().setClues(Clues.getNotes());
            this.model.getCurrentPlayer().setNotes(BoardScene.getTextArea());
        }
        model.nextTurn();
        view.updateNextPlayerView(model.getCurrentPlayer());
        view.drawNotification("Now it's the turn of " + model.getCurrentPlayer().getName(), IconType.INFO);
        BoardScene.showLeftToolbar(this.model.getCurrentPlayer().getType().equals(PlayerType.HUMAN));
        playGame();
    }

    @Override
    public void accuse(final RoomCard room, final WeaponCard weapon, final CharacterCard character) {
        final boolean win = model.checkSolution(new SolutionImpl(character, weapon, room));
        for (final PlayerInfo p : model.getPlayers()) {
            if (p.getName().equals(character)) {
                view.updatePlayerPosition(p);
            }
        }
        if (win) {
            new AudioPlayer("/sounds/win.wav").play(false);
            view.drawNotification(model.getCurrentPlayer().getName() + " has accused and wins the game!"
                    + "\nThe killer was Mario " + character + " with the " + weapon + " in the " + room,
                    IconType.WINNER);
            backToMenu();
        } else {
            new AudioPlayer("/sounds/lose.wav").play(false);
            final String event = model.getCurrentPlayer().getName() + " has accused " + character
                    + " of murder with the " + weapon + " in the " + room + " wrongly and lost the game";
            view.drawNotification(event, IconType.ERROR);
            for (final PlayerInfo p : model.getPlayers()) {
                p.addHistoryEvent(event);
            }
            giveUp();
        }
    }

    @Override
    public void guess(final RoomCard s, final WeaponCard a, final CharacterCard p) {
        String str = "";
        final Solution guess = new SolutionImpl(p, a, s);
        final Optional<Pair<PlayerInfo, Card>> sol = model.disproveSuspect(guess);
        this.model.getAIManager().collectClue(model.getCurrentPlayer(), guess, sol);
        for (final PlayerInfo player : model.getPlayers()) {
            if (player.getName().equals(p)) {
                view.updatePlayerPosition(player);
            }
        }
        if (this.model.getCurrentPlayer().getType().equals(PlayerType.HUMAN)) {
            if (sol.isPresent()) {
                str = sol.get().getX().getName() + " shows you the " + sol.get().getY() + " card";
                view.drawNotification(str, IconType.INFO);
            } else {
                view.drawNotification("No one has cards to show you!", IconType.INFO);
            }
        }
        addHistoryGuess(guess, sol);
    }

    @Override
    public void chooseRoom(final RoomCard selectedRoom) {
        model.movePlayer(selectedRoom, dado);
        view.updatePlayerPosition(model.getCurrentPlayer());
    }

    @Override
    public void resumeSelectedGame(final String gamename) {
        if (!gamename.equals("")) {
            try {
                final InputStream file2 = new FileInputStream(SAVING_DIR_PATH + gamename);
                final InputStream bstream2 = new BufferedInputStream(file2);
                final ObjectInputStream ostream2 = new ObjectInputStream(bstream2);
                final ModelMemento meme = (ModelMemento) ostream2.readObject();
                ostream2.close();
                model = GameInit.getInstance().loadGame(meme);
            } catch (Exception e) {
                e.printStackTrace();
            }
            final boolean success = new File(SAVING_DIR_PATH + gamename).delete();
            if (!success) {
                view.drawNotification("Cannot delete the save file", IconType.ERROR);
            }
            final List<CharacterCard> characters = this.model.getPlayers().stream().map(player -> player.getName())
                    .collect(Collectors.toList());
            view.drawNotification(
                    "Players will play according to the following rounds:\n\n" + Joiner.on("\n").join(characters),
                    IconType.INFO);
            this.view.switchBoardScene(model.getPlayers());
            this.view.updateNextPlayerView(model.getCurrentPlayer());
            view.drawNotification("Now it's the turn of " + model.getCurrentPlayer().getName(), IconType.INFO);
            BoardScene.showLeftToolbar(this.model.getCurrentPlayer().getType().equals(PlayerType.HUMAN));

        } else {
            view.drawNotification("You have to select something!", IconType.ERROR);
        }
    }

    @Override
    public void giveUp() {
        final CharacterCard currentCharacter = this.model.getCurrentPlayer().getName();
        view.removePawnPlayer(model.getCurrentPlayer());
        model.removePlayer(model.getCurrentPlayer());
        for (final PlayerInfo p : model.getPlayers()) {
            p.addHistoryEvent(currentCharacter + " left the game. The cards of " + currentCharacter + " were: "
                    + Joiner.on(", ").join(this.model.getCurrentPlayer().getCards()));
        }
        if (model.getPlayers().size() == 1) {
            model.nextTurn();
            view.updateNextPlayerView(model.getCurrentPlayer());
            new AudioPlayer("/sounds/win.wav").play(false);
            view.drawNotification(model.getCurrentPlayer().getName() + " wins the game!\n"
                    + "All other players have lost or left the game", IconType.WINNER);
            backToMenu();
        } else {
            endTurn();
        }
    }

    @Override
    public void showCards() {
        CardsDialog.getDialog()
                .createCardsDialog(Sets.union(model.getCurrentPlayer().getCards(), model.getCommonCards()));
    }

    @Override
    public void backToMenu() {
        if (model != null) {
            model.endGame();
            model = null;
        }
        view.switchScene("MENUSCENE");
    }

    @Override
    public void newGame() {
        view.switchScene("SETUPGAME");
    }

    private void playGame() {
        final PlayerInfo pAttual = model.getCurrentPlayer();
        if (!model.isOver()) {
            dado = model.rollDices();
            if (pAttual.getType() == PlayerType.AI) {
                final RoomCard r = model.getAIManager().chooseDestination(dado);
                model.movePlayer(r, dado);
                view.updatePlayerPosition(pAttual);
                String aiTurnEvents = pAttual.getName() + " finished the turn.\n";
                if (model.getCurrentPlayer().canSuspect()) {
                    final Solution suspect = model.getAIManager().generateSuspect();
                    guess(suspect.getRoom(), suspect.getWeapon(), suspect.getCharacter());
                    aiTurnEvents += pAttual.getHistory().get(pAttual.getHistory().size() - 1).replaceAll("the.*card",
                            "a card") + ".\n";
                    view.drawNotification(aiTurnEvents, IconType.INFO);
                    if (model.getAIManager().wantToAccuse()) {
                        final Solution sol = model.getAIManager().giveSolution();
                        accuse(sol.getRoom(), sol.getWeapon(), sol.getCharacter());
                    } else {
                        endTurn();
                    }
                } else {
                    endTurn();
                }
            } else {
                view.showRooms(dado);
            }
        } else {
            view.drawNotification("Game over", IconType.GAMEOVER);
            this.quit();
        }
    }

    private List<String> savedGameList() {
        final File dir = new File(SAVING_DIR_PATH);
        final String[] list = dir.list();
        if (list != null && list.length > 0) {
            Arrays.sort(list);
            return Arrays.asList(list);
        } else {
            return new ArrayList<>();
        }
    }

    private void addHistoryGuess(final Solution guess, final Optional<Pair<PlayerInfo, Card>> result) {
        for (final PlayerInfo player : model.getPlayers()) {
            String event = this.model.getCurrentPlayer().getName() + " has supposed that the murderer was "
                    + guess.getCharacter() + " with the " + guess.getWeapon() + " in the " + guess.getRoom() + ".\n";
            if (result.isPresent()) {
                event += result.get().getX().getName() + " showed to " + this.model.getCurrentPlayer().getName()
                        + " %card%";
                if (player.equals(this.model.getCurrentPlayer()) || player.equals(result.get().getX())) {
                    event = event.replaceAll("%card%", "the " + result.get().getY()) + " card";
                } else {
                    event = event.replaceAll("%card%", "a card");
                }
            } else {
                event += "Nobody showed him a card";
            }
            event = event.substring(0, 1).toUpperCase(new Locale("en")) + event.substring(1);
            player.addHistoryEvent(event);
            if (player.equals(this.model.getCurrentPlayer())) {
                view.updateHistoryCurrentPlayer(event);
            }
        }
    }
}