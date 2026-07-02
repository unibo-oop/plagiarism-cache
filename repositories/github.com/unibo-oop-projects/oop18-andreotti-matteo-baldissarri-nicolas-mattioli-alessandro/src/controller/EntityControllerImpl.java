package controller;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import model.bonus.Bonus;
import model.enemy.Enemy;
import model.entities.Position;
import model.entities.Character;
import model.palace.Floor;
import model.palace.Palace;
import model.palace.Window;
import model.palace.Window.StatusOfWindow;

/**
 * 
 * Implements Entity Controller.
 *
 */
public final class EntityControllerImpl implements EntityController {

    private List<Window> changedWindows = new ArrayList<>();
    private static final int NUM_WINDOWS_TO_CHANGE_STATUS = 4;
    private static final int MAX_WINDOWS_NOT_DEFAULT_STATUS = 2;
    private static final int DIFFICULTY = 80;
    private static final int BONUS_DIFFICULTY = 200;
    private static final int VISIBLE_FLOOR = 11;
    private static final int SPAWN_RANGE = 5;
    private static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final int SECONDS = 90;
    private int loop = 0;
    private boolean isHawkCallable = false;
    private boolean isVaseCallable = false;
    private final MovementObserver movement;
    private final CollisionObserver collision;

    /**
     * 
     * Instantiate the entity controller.
     * 
     * @param movement  The MovementObserver
     * @param collision The CollisionObserver
     */
    public EntityControllerImpl(final MovementObserver movement, final CollisionController collision) {
        this.movement = movement;
        this.collision = collision;
    }

    @Override
    public void switchPalaceStatus(final Palace palace, final Character stuntman) {
        int count = 0;
        loop++;
        if (loop % SECONDS == 0) {
            for (int i = 0; i < NUM_WINDOWS_TO_CHANGE_STATUS; i++) {
                Floor floor = palace.getRandomFloor();
                Iterator<Window> it = floor.getWindows().iterator();
                while (it.hasNext()) {
                    Window tmp = it.next();
                    if (tmp.getStatus() != StatusOfWindow.OPEN) {
                        count++;
                    }
                }
                if (count <= MAX_WINDOWS_NOT_DEFAULT_STATUS - 1) {
                    Window win = floor.getRandomWindow();
                    if (this.changedWindows.size() == 0 || !this.changedWindows.contains(win)) {
                        win.changeStatus();
                        this.changedWindows.add(win);
                    }
                }
                if (count > MAX_WINDOWS_NOT_DEFAULT_STATUS - 1) {
                    Window win = floor.getRandomWindow();
                    if (!this.changedWindows.contains(win) && win.getStatus() == StatusOfWindow.CLOSE) {
                        win.changeStatus();
                        this.changedWindows.add(win);
                    }
                }
            }
            this.changedWindows.clear();
        }
        this.collision.collideWithClosedWindow(stuntman, palace.getFloors());
    }

    /**
     * 
     * Spawn a Hawk that crosses the map.
     * 
     * @param hawks The list of Hawks.
     * 
     */
    public void spawnHawk(final List<Enemy> hawks) {
        Random r = new Random();
        if (loop % SECONDS == 0 || isHawkCallable) {
            isHawkCallable = true;
            Iterator<Enemy> it = hawks.iterator();
            while (it.hasNext()) {
                Enemy tmp = it.next();
                if (r.nextInt(DIFFICULTY) == r.nextInt(DIFFICULTY)) {
                    if (!tmp.isInGame()) {
                        int casualSpan = (int) (SCREEN_HEIGHT / VISIBLE_FLOOR)
                                * (r.nextInt(SPAWN_RANGE) + (SPAWN_RANGE - 2));
                        tmp.setPosition(new Position(-(tmp.getWidth() / 2),
                                casualSpan + ((SCREEN_HEIGHT / VISIBLE_FLOOR) / 2)));
                        tmp.setInGame(true);
                    }
                    isHawkCallable = false;
                }
            }
        }
        this.movement.moveHawk();
    }

    /**
     * 
     * Spawn a Jar that crosses the map.
     * 
     * @param palace The palace on which to change the state of the windows.
     * @param vases  The list of Vase.
     * 
     */
    public void spawnVase(final Palace palace, final List<Enemy> vases) {
        double windowWidth = (palace.getFloors().get(0).getWindows().get(1).getPosition().getX()
                - palace.getFloors().get(0).getWindows().get(0).getPosition().getX());
        Random r = new Random();
        Window window;
        if (loop % SECONDS == 0 || isVaseCallable) {
            isVaseCallable = true;
            if (r.nextInt(DIFFICULTY) == r.nextInt(DIFFICULTY) && !vases.get(0).isInGame()) {
                window = palace.getRandomFloor().getRandomWindow();
                isVaseCallable = false;
                vases.get(0).setPosition(new Position(window.getPosition().getX(), -(vases.get(0).getHeight() / 2)));
                vases.get(0).setInGame(true);
            }
            if (r.nextInt(DIFFICULTY * 2) == r.nextInt(DIFFICULTY * 2) && !vases.get(1).isInGame()) {
                do {
                    window = palace.getRandomFloor().getRandomWindow();
                } while (window.getPosition()
                        .getX() == (palace.getRandomFloor().getWindows().get(SPAWN_RANGE).getPosition().getX()));
                isVaseCallable = false;
                vases.get(1).setPosition(
                        new Position(window.getPosition().getX() + (windowWidth / 2), -(vases.get(1).getHeight() / 2)));
                vases.get(1).setInGame(true);
            }
        }
        this.movement.moveVase();
    }

    /**
     * Spawn a Bonus at a random point on the map.
     * 
     * @param palace The palace
     * @param bonus  The list of LifeBonus.
     */
    public void spawnBonus(final Palace palace, final List<Bonus> bonus) {
        double windowHeight = (palace.getFloors().get(0).getWindows().get(0).getPosition().getY()
                - palace.getFloors().get(1).getWindows().get(0).getPosition().getY());
        Random r = new Random();
        boolean spawned = false;
        Iterator<Bonus> it = bonus.iterator();
        while (it.hasNext() && !spawned) {
            Bonus lb = it.next();
            if (r.nextInt(BONUS_DIFFICULTY) == r.nextInt(BONUS_DIFFICULTY)) {
                if (!lb.isInGame()) {
                    Window window = palace.getRandomFloor().getRandomWindow();
                    if (bonus.stream().filter(b -> b.isInGame())
                            .noneMatch(b -> b.getPosition().equals(window.getPosition()))) {
                        lb.setPosition(new Position(window.getPosition().getX(), -(windowHeight / 2)));
                        lb.setInGame(true);
                        spawned = true;
                    }
                }
                spawned = false;
            }
        }
    }

}
