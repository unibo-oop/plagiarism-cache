package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import controller.observer.ButtonObserver;
import controller.observer.KeyObserver;
import controller.time.Time;
import controller.utility.Score;
import controller.utility.ScoreImpl;
import controller.utility.ScreenResolution;
import model.World;
import model.WorldImpl;
import model.animated.AbstractCharacter;
import model.utility.ModelUtility;
import model.utility.ProportionUtility;
import utility.Mode;
import view.ViewImpl;
import view.utility.ViewUtils;

/**
 * GameEngineImpl manages all game situations.
 */
public final class GameEngineImpl implements GameEngine {

    private static final String PATH = System.getProperty("user.home") + File.separator + "Leaderboard.txt";
    private static final int NAME = 0;
    private static final int SCORE = 1;
    private static final int TIME = 2;
    private static final int MODE = 3;
    private static final int MINUTES = 0;
    private static final int SECONDS = 1;

    private static GameEngineImpl singleton;
    private GameLoop gameLoop;
    private List<Score> scoreList = new ArrayList<>();
    private Mode selectedMode;

    /**
     * The class constructor.
     */
    private GameEngineImpl() {
    }

    /**
     * Get the instance of GameEngineImpl.
     * 
     * @return the instance of controller.
     */
    public static GameEngine get() {
        if (Objects.isNull(singleton)) {
            singleton = new GameEngineImpl();
        }
        return singleton;
    }

    /**
     * Initialize the view for the kts application.
     */
    @Override
    public void initView() {
        readLeaderboard();
        if (!Objects.isNull(scoreList)) {
            ViewUtils.setScoreBoard(new ArrayList<Score>(scoreList));
        }
        ViewImpl.get().addObserver(new ButtonObserver());
        ViewImpl.get().addObserver(new KeyObserver());
        ViewImpl.get().setInitialHeight(ScreenResolution.getHeigtSize() / ViewUtils.getYScreenProp());
        ViewImpl.get().setInitialWidth(ScreenResolution.getWidthSize() / ViewUtils.getXScreenProp());
        ViewImpl.get().setWorldHeight(ModelUtility.getWorldHeight());
        ViewImpl.get().setWorldWidth(ModelUtility.getWorldWidth());
        ViewImpl.get().setWorldHeightProportion(ModelUtility.getWorldHeightProp());
        ViewImpl.get().setWorldWidthProportion(ModelUtility.getWorldWidthProp());
        ViewImpl.get().setIWallMinorDimension(ProportionUtility.getWallVerticalWidth());
        ViewImpl.get().viewStart();
    }

    /**
     * Create a new game.
     * 
     * @param name
     *            of the player.
     */
    @Override
    public void newGame(final String name) {
        final World world = new WorldImpl();
        world.createEnvironment();
        if (ViewImpl.get().isGodModeSelected()) {
            world.setMode(Mode.GOD);
            selectedMode = Mode.GOD;
        } else if (ViewImpl.get().isSurvivalModeSelected()) {
            world.setMode(Mode.SURVIVAL);
            selectedMode = Mode.SURVIVAL;
        } else {
            world.setMode(Mode.NORMAL);
            selectedMode = Mode.NORMAL;
        }

        ViewImpl.get().initTimeCanvas();
        ViewImpl.get().roomChanged(ModelUtility.getRoom());
        ViewImpl.get().playerLifeChanged(((AbstractCharacter) ModelUtility.getPlayer()).getLife());

        this.gameLoop = new GameLoopImpl(world, name, selectedMode);
        resumeGameLoop();
    }

    /**
     * Stop the game.
     */
    @Override
    public void stopGame() {
        gameLoop.stop();
    }

    /**
     * Resume the execution of the game.
     */
    @Override
    public void resumeGameLoop() {
        if (!Objects.isNull(gameLoop)) {
            gameLoop.start();
        }
    }

    /**
     * The player has lost.
     */
    @Override
    public void gameOver(final int point) {
        ViewImpl.get().notifyGameOverEvent(point);
        stopGame();
    }

    /**
     * The player has won.
     */
    @Override
    public void victory(final int points) {
        ViewImpl.get().notifyVictoryGameEvent(points);
        stopGame();
    }

    /**
     * @return the gameLoop object.
     */
    public GameLoop getGameLoop() {
        return this.gameLoop;
    }

    /**
     * Get the leader board of this computer.
     * 
     * @return the leader board.
     */
    public List<Score> getLeaderboard() {
        return this.scoreList;
    }

    /**
     * Set the leader board.
     */
    @Override
    public void setLeaderboard(final List<Score> list) {
        this.scoreList = list;
        writeLeaderboard();
        ViewUtils.setScoreBoard(new ArrayList<Score>(scoreList));
    }

    /**
     * Read the saves.
     */
    private void readLeaderboard() {
        final File file = new File(PATH);
        List<String> items;
        List<String> splitTime;
        String modeRead;

        try {
            if (!file.createNewFile()) {
                final BufferedReader in = new BufferedReader(new FileReader(file));
                for (String x = in.readLine(); x != null; x = in.readLine()) {
                    items = Arrays.asList(x.split(" "));
                    splitTime = Arrays.asList(items.get(TIME).split(":"));
                    modeRead = items.get(MODE);
                    Mode enumMode;
                    if (modeRead.equals(Mode.GOD.toString())) {
                        enumMode = Mode.GOD;
                    } else if (modeRead.equals(Mode.SURVIVAL.toString())) {
                        enumMode = Mode.SURVIVAL;
                    } else {
                        enumMode = Mode.NORMAL;
                    }
                    scoreList.add(new ScoreImpl(items.get(NAME), Integer.parseInt(items.get(SCORE)),
                            new Time(Integer.parseInt(splitTime.get(MINUTES)),
                                    Integer.parseInt(splitTime.get(SECONDS))),
                            enumMode));
                }
                in.close();
            }
        } catch (Exception e) {
            System.out.println("Error on reading leaderboard: " + e.getMessage()); // To change.
        }
    }

    private void writeLeaderboard() {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(PATH)))) {
            scoreList.forEach(x -> out.println(x));
        } catch (IOException e) {
            System.out.println("Error reading leaderboard: " + e.getMessage());
        }
    }
}
