package controller;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.bonus.Bonus;
import model.enemy.Enemy;
import model.entities.Character;
import model.entities.CharacterEnvironment;
import model.entities.EnemyEnvironment;
import model.entities.Position;
import model.palace.Palace;
import utils.Pair;

/**
 * Builds and maintains entities.
 */
public final class EntityFactory {

    private static final double STUNTMAN_Y_RATIO = 0.63;
    private static final double STUNTMAN_X_RATIO = 1.90;
    private static final int INIT_FLOOR_STUNTMAN_LOCATED = 1;
    private static final int INIT_WINDOW_STUNTMAN_LOCATED = 3;
    private static final int SIGHT_FLOOR = 12;
    private static final int WINDOWS_PER_FLOOR = 6;
    private static final double SCREEN_X_RATIO = 0.4;
    private static final double WINDOWS_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 12.5;
    private static final double WINDOWS_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 11;
    private final EntityFactoryObserver factoryObserver;
    private Character stuntman;
    private Palace palace;
    private List<Enemy> hawks = new ArrayList<>();
    private List<Enemy> vases = new ArrayList<>();
    private List<Bonus> bonus = new ArrayList<>();

    /**
     * Instantiate the factory controller and the entities.
     */
    public EntityFactory() {
        this.factoryObserver = new EntityFactoryController();
        this.palace();
        this.hawk();
        this.vase();
        this.stuntman();
        this.bonus();
    }

    /**
     * Initialize the Palace.
     */
    public void palace() {
        this.palace = this.factoryObserver.createPalace(this.createWindowsPosition());
    }

    /**
     * Initialize the Stuntman.
     */
    public void stuntman() {
        final Position initPos = new Position(
                this.palace.getFloors().get(INIT_FLOOR_STUNTMAN_LOCATED).getWindows().get(INIT_WINDOW_STUNTMAN_LOCATED)
                        .getPosition().getX(),
                this.palace.getFloors().get(INIT_FLOOR_STUNTMAN_LOCATED).getWindows().get(INIT_WINDOW_STUNTMAN_LOCATED)
                        .getPosition().getY() + WINDOWS_HEIGHT / 2);
        final Pair<Double, Double> shift = new Pair<Double, Double>(WINDOWS_WIDTH, WINDOWS_HEIGHT);
        this.stuntman = this.factoryObserver.createStuntman(initPos, shift,
                new CharacterEnvironment(initPos, shift.getY() * STUNTMAN_X_RATIO, shift.getX() * STUNTMAN_Y_RATIO, shift));
    }

    /**
     * Add the positions to the windows.
     * 
     * @return The list of position lists.
     */
    private List<List<Position>> createWindowsPosition() {
        List<List<Position>> windowsPosition = new ArrayList<List<Position>>();
        List<Position> tmp = new ArrayList<Position>();
        for (int i = 0; i <= SIGHT_FLOOR; i++) {
            for (int j = 0; j < WINDOWS_PER_FLOOR; j++) {
                tmp.add(new Position(
                        Toolkit.getDefaultToolkit().getScreenSize().getWidth() * SCREEN_X_RATIO + j * WINDOWS_WIDTH
                                + WINDOWS_WIDTH / 2,
                        Toolkit.getDefaultToolkit().getScreenSize().getHeight() - i * WINDOWS_HEIGHT
                                - WINDOWS_HEIGHT / 2));
            }
            windowsPosition.add(new ArrayList<>(tmp));
            tmp.clear();
        }
        return windowsPosition;
    }

    /**
     * @return The Palace.
     */
    public Palace getPalace() {
        return this.palace;
    }

    /**
     * Initialize the Hawk.
     */
    public void hawk() {
        hawks.addAll(Stream
                .generate(() -> this.factoryObserver.createHawk(new Pair<Double, Double>(WINDOWS_WIDTH, WINDOWS_HEIGHT),
                        new EnemyEnvironment(new Position(), WINDOWS_HEIGHT, WINDOWS_WIDTH, Optional.empty())))
                .limit(2).collect(Collectors.toList()));
    }

    /**
     * Initialize the Vase.
     */
    public void vase() {
        vases.add(this.factoryObserver.createVase(new Pair<Double, Double>(WINDOWS_WIDTH / 2, WINDOWS_HEIGHT / 2),
                new EnemyEnvironment(new Position(), WINDOWS_HEIGHT / 2, WINDOWS_WIDTH / 2, Optional.empty())));
        vases.add(this.factoryObserver.createVase(new Pair<Double, Double>(WINDOWS_WIDTH * 2, WINDOWS_HEIGHT),
                new EnemyEnvironment(new Position(), WINDOWS_HEIGHT, WINDOWS_WIDTH * 2, Optional.empty())));
    }

    /**
     * Initialize the LifeBonus.
     */
    public void bonus() {
        this.bonus.add(this.factoryObserver.createLifeBonus(new Pair<Double, Double>(WINDOWS_WIDTH, WINDOWS_HEIGHT),
                new EnemyEnvironment(new Position(), WINDOWS_HEIGHT, WINDOWS_WIDTH, Optional.empty())));
        this.bonus.add(this.factoryObserver.createScoreBonus(new Pair<Double, Double>(WINDOWS_WIDTH, WINDOWS_HEIGHT),
                new EnemyEnvironment(new Position(), WINDOWS_HEIGHT / 2, WINDOWS_WIDTH / 2, Optional.empty())));
    }

    /**
     * @return The Stuntman.
     */
    public Character getStuntman() {
        return this.stuntman;
    }

    /**
     * @return The list of Hawks.
     */
    public List<Enemy> getHawks() {
        return this.hawks;
    }

    /**
     * @return The list of Vases.
     */
    public List<Enemy> getVases() {
        return this.vases;
    }

    /**
     * @return The list of Bonus.
     */
    public List<Bonus> getBonus() {
        return this.bonus;
    }
}
