package user.menu.stagelevel;

import java.util.Random;

import user.enums.Objects;
import user.enums.RecurrentNumbers;
import user.enums.RoomsNames;
import user.game.ships.enemies.ObjBossShip;
import user.game.ships.enemies.ObjEnemyShip;
import user.menu.ObjAlarm;
import zengine.core.GameObject;
import zengine.core.Zengine;
import zengine.interfaces.GameEngine;

/**
 * this class administrates enemies spawns.
 */
public class ObjEnemiesSpawns {

    private static final int END_STAGE_DELAY = 120;
    private static final int NUM_MAX_ALLY = 3;
    private static final int SPAWN_RANGE = 500;
    private static final int WAVE2 = 2;
    private static final int WAVE3 = 3;
    private static final int WAVE4 = 4;
    private static final int WAVE5 = 5;
    private final Random random = new Random();
    private final GameEngine z = Zengine.getEngine();

    /**
     * this method administrates enemies spawns for level 1.
     * 
     * @param wave
     *            indicates the wave that will start
     */
    public void level1(final int wave) {

        switch (wave) {
        case 1:
            spawnAlly();
            for (int i = 1; i < WAVE5; i++) {
                final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(
                        random.nextInt(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue()),
                        RecurrentNumbers.LEVEL_RESOLUTION_Y.getValue(), Objects.ENEMY_SHIP.getValue());
                enemy.setType(1);
            }
            for (int i = 1; i < WAVE5; i++) {
                final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(
                        random.nextInt(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue()), 0, Objects.ENEMY_SHIP.getValue());
                enemy.setType(1);
            }
            break;
        case 2:
            spawnAlly();
            for (int i = 1; i < WAVE4; i++) {
                final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(
                        random.nextInt(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue()),
                        RecurrentNumbers.LEVEL_RESOLUTION_Y.getValue(), Objects.ENEMY_SHIP.getValue());
                enemy.setType(1);
            }
            for (int i = 1; i < WAVE4; i++) {
                final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(
                        random.nextInt(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue()), 0, Objects.ENEMY_SHIP.getValue());
                enemy.setType(1);
            }
            final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue() / 2, 0,
                    Objects.ENEMY_SHIP.getValue());
            enemy.setType(2);
            final ObjEnemyShip enemy2 = (ObjEnemyShip) z.instanceCreate(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue() / 2,
                    RecurrentNumbers.LEVEL_RESOLUTION_Y.getValue(), Objects.ENEMY_SHIP.getValue());
            enemy2.setType(2);
            break;
        case 3:
            spawnAlly();
            z.musicPlay("levelMusic");
            final ObjBossShip boss = (ObjBossShip) z.instanceCreate(1000, 1000, Objects.BOSS_SHIP.getValue());
            boss.setType(1);
            break;
        case 4:
            final ObjAlarm alarm = (ObjAlarm) z.instanceCreate(0, 0, "menu.ObjAlarm");
            alarm.setDelay(END_STAGE_DELAY);
            alarm.setAction(g -> {
                z.roomGoto(RoomsNames.POT.getValue());
            });

        default:
            break;
        }
    }

    /**
     * this method administrates enemies spawns for level 2.
     * 
     * @param wave
     *            indicates the wave that will start
     */
    public void level2(final int wave) {

        switch (wave) {
        case 1:
            spawnAlly();
            for (int i = 1; i < WAVE3; i++) {
                final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(
                        random.nextInt(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue()),
                        RecurrentNumbers.LEVEL_RESOLUTION_Y.getValue(), Objects.ENEMY_SHIP.getValue());
                enemy.setType(z.choose(1, 2));
            }
            for (int i = 1; i < WAVE5; i++) {
                final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(
                        random.nextInt(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue()), 0, Objects.ENEMY_SHIP.getValue());
                enemy.setType(1);
            }
            for (int i = 1; i < WAVE3; i++) {
                final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(
                        random.nextInt(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue()),
                        RecurrentNumbers.LEVEL_RESOLUTION_Y.getValue(), Objects.ENEMY_SHIP.getValue());
                enemy.setType(z.choose(1, 2));
            }
            break;
        case 2:
            spawnAlly();
            for (int i = 1; i < WAVE2; i++) {
                final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(
                        random.nextInt(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue()),
                        RecurrentNumbers.LEVEL_RESOLUTION_Y.getValue(), Objects.ENEMY_SHIP.getValue());
                enemy.setType(2);
            }
            for (int i = 1; i < WAVE2; i++) {
                final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(
                        random.nextInt(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue()), 0, Objects.ENEMY_SHIP.getValue());
                enemy.setType(2);
            }
            final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue() / 2, 0,
                    Objects.ENEMY_SHIP.getValue());
            enemy.setType(3);
            final ObjEnemyShip enemy2 = (ObjEnemyShip) z.instanceCreate(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue() / 2,
                    RecurrentNumbers.LEVEL_RESOLUTION_Y.getValue(), Objects.ENEMY_SHIP.getValue());
            enemy2.setType(3);
            break;
        case 3:
            spawnAlly();
            z.musicPlay("levelMusic");
            final ObjBossShip boss = (ObjBossShip) z.instanceCreate(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue() - 1000, 1000,
                    Objects.BOSS_SHIP.getValue());
            boss.setType(2);
            break;
        case 4:
            final ObjAlarm alarm = (ObjAlarm) z.instanceCreate(0, 0, "menu.ObjAlarm");
            alarm.setDelay(END_STAGE_DELAY);
            alarm.setAction(g -> {
                z.roomGoto(RoomsNames.POT.getValue());
            });
        default:
            break;
        }
    }

    /**
     * this method administrates enemies spawns for level 3.
     * 
     * @param wave
     *            indicates the wave that will start
     */
    public void level3(final int wave) {

        switch (wave) {
        case 1:
            spawnAlly();
            for (int i = 1; i < WAVE5; i++) {
                final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(
                        random.nextInt(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue()),
                        RecurrentNumbers.LEVEL_RESOLUTION_Y.getValue(), Objects.ENEMY_SHIP.getValue());
                enemy.setType(z.choose(1, 2, 3));
            }
            for (int i = 1; i < WAVE5; i++) {
                final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(
                        random.nextInt(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue()),
                        RecurrentNumbers.LEVEL_RESOLUTION_Y.getValue(), Objects.ENEMY_SHIP.getValue());
                enemy.setType(z.choose(1, 2, 3));
            }
            break;
        case 2:
            spawnAlly();
            for (int i = 1; i < WAVE3; i++) {
                final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(
                        random.nextInt(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue()),
                        RecurrentNumbers.LEVEL_RESOLUTION_Y.getValue(), Objects.ENEMY_SHIP.getValue());
                enemy.setType(3);
            }
            for (int i = 1; i < WAVE3; i++) {
                final ObjEnemyShip enemy = (ObjEnemyShip) z.instanceCreate(
                        random.nextInt(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue()), 0, Objects.ENEMY_SHIP.getValue());
                enemy.setType(3);
            }
            break;
        case 3:
            spawnAlly();
            z.musicPlay("levelMusic");
            final ObjBossShip boss = (ObjBossShip) z.instanceCreate(1000, 1000, Objects.BOSS_SHIP.getValue());
            final ObjBossShip boss2 = (ObjBossShip) z.instanceCreate(RecurrentNumbers.LEVEL_RESOLUTION_X.getValue() - 1000, 1000,
                    Objects.BOSS_SHIP.getValue());
            boss.setType(1);
            boss2.setType(2);
            break;
        case 4:
            final ObjAlarm alarm = (ObjAlarm) z.instanceCreate(0, 0, "menu.ObjAlarm");
            alarm.setDelay(END_STAGE_DELAY);
            alarm.setAction(g -> {
                z.roomGoto(RoomsNames.CREDITS.getValue());
            });
        default:
            break;
        }
    }

    /**
     * this method spawns allies until they reaches a certain maximum number.
     */
    private void spawnAlly() {
        for (int i = z.instanceNumber(Objects.ALLY_SHIP.getValue()); i < NUM_MAX_ALLY; i++) {
            final GameObject player = z.instanceGet(Objects.PLAYER_SHIP.getValue());
            if (z.instanceExists(Objects.PLAYER_SHIP.getValue())) {
                final int positionX = (int) (player.getX() - (SPAWN_RANGE / 2));
                final int positionY = (int) (player.getY() - (SPAWN_RANGE / 2));
                z.instanceCreate(random.nextInt(SPAWN_RANGE) + positionX, random.nextInt(SPAWN_RANGE) + positionY,
                        Objects.ALLY_SHIP.getValue());
            } else {
                z.instanceCreate(random.nextInt(SPAWN_RANGE) + RecurrentNumbers.PLAYER_SPAWN_X.getValue(),
                        random.nextInt(SPAWN_RANGE) + RecurrentNumbers.PLAYER_SPAWN_X.getValue(), Objects.ALLY_SHIP.getValue());
            }
        }
    }
}
