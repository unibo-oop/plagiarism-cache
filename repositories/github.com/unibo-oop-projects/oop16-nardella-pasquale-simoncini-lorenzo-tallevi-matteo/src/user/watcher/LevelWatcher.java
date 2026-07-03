package user.watcher;

import user.enums.Objects;
import user.enums.RecurrentNumbers;
import user.enums.RoomsNames;
import user.game.ships.friends.ObjPlayerShip;
import user.menu.ObjAlarm;
import user.menu.stagelevel.ObjEnemiesSpawns;
import zengine.core.Zengine;
import zengine.interfaces.GameEngine;

/**
 * This class represent the level watcher that keep tracks of what happens
 * during the stage and update the objects on screen.
 */
public final class LevelWatcher {

    private static final int MOLTIPLICATOR = 30;
    private final OverallWatcher overWatch = OverallWatcher.getWatcher();
    private static LevelWatcher watcher = new LevelWatcher();
    private final ObjEnemiesSpawns spawn = new ObjEnemiesSpawns();
    private final GameEngine z = Zengine.getEngine();
    private int life = 3;
    private int score; // = 0;
    private int time; // = 0;
    private int wave; // = 0;

    private LevelWatcher() {
    };

    /**
     * the constructor of the Singleton LevelWatcher.
     * 
     * @return the unique level watcher.
     */
    public static LevelWatcher getWatcher() {
        return watcher;
    }

    /**
     * this method resets the watcher at its initial state.
     */
    public void reset() {
        this.score = 0;
        this.time = 0;
        this.wave = 0;
    }

    private int countEnemies() {
        return z.instanceNumber(Objects.ENEMY_SHIP.getValue()) + z.instanceNumber(Objects.BOSS_SHIP.getValue());

    }

    /**
     * increase score based on the rank of the enemy.
     * 
     * @param rank
     *            the rank of the enemy.
     */
    public void enemyIsDead(final int rank) {
        this.setScore(this.score + (MOLTIPLICATOR * rank));
        if (this.countEnemies() <= 1) {
            this.clearedWave();
        }
    }

    /**
     * set life to one less and respawn the player.
     */
    public void playerIsDead() {
        this.setLife(this.life - 1);
        if (this.life <= 0) {
            final ObjAlarm alarm = (ObjAlarm) z.instanceCreate(0, 0, "menu.ObjAlarm");
            alarm.setDelay(90);
            alarm.setAction(g -> {
                z.roomGoto(RoomsNames.GAME_OVER.getValue());
            });
        } else {
            final ObjAlarm alarm = (ObjAlarm) z.instanceCreate(0, 0, "menu.ObjAlarm");
            this.setScore(this.score - 100);

            alarm.setDelay(90);
            alarm.setAction(g -> {
                z.instanceDestroyAll(Objects.ENEMY_BULLET.getValue(), null);
                z.instanceDestroyAll(Objects.FRIEND_BULLET.getValue(), null);
                final ObjPlayerShip ship = (ObjPlayerShip) z.instanceCreate(RecurrentNumbers.PLAYER_SPAWN_X.getValue(),
                        RecurrentNumbers.PLAYER_SPAWN_Y.getValue(), Objects.PLAYER_SHIP.getValue());
                ship.initializeShipStats(OverallWatcher.getWatcher().getHealth(), OverallWatcher.getWatcher().getDamage(),
                        OverallWatcher.getWatcher().getProjectilePerSecond(), OverallWatcher.getWatcher().getSpeed());
            });
        }
    }

    /**
     * the current wave is cleared; calls the next wave.
     */
    public void clearedWave() {
        this.wave++;
        switch (overWatch.getCurrentLevel()) {
        case 1:
            spawn.level1(this.wave);
            break;
        case 2:
            spawn.level2(this.wave);
            break;
        case 3:
            spawn.level3(this.wave);
            break;
        default:
            break;
        }
    }

    /**
     * returns value of life.
     * 
     * @return value of life.
     */
    public int getLife() {
        return this.life;
    }

    /**
     * This method sets the variable life.
     * 
     * @param life
     *            the current life.
     */
    public void setLife(final int life) {
        this.life = life;
    }

    /**
     * returns value of score.
     * 
     * @return value of score.
     */
    public int getScore() {
        return score;
    }

    /**
     * This method sets the variable score.
     * 
     * @param score
     *            the current score.
     */
    public void setScore(final int score) {
        if (score <= 0) {
            this.score = 0;
        } else {
            this.score = score;
        }
    }

    /**
     * returns value of time.
     * 
     * @return value of time.
     */
    public int getTime() {
        return time;
    }

    /**
     * This method sets the variable time.
     * 
     * @param time
     *            the current time.
     */
    public void setTime(final int time) {
        this.time = time;
    }

    /**
     * passes the stats to overallWatcher (it needs them to initialize the
     * potentiation menu).
     */
    public void passToOverallWatcher() {
        overWatch.setLife(this.life);
        overWatch.scoreToExpendablePoints(this.score, this.time);
    }

}
