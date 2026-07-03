package user;

import user.enums.MenuClassesNames;
import user.enums.Objects;
import user.enums.RecurrentNumbers;
import user.enums.RoomsNames;

import user.game.ships.friends.ObjPlayerShip;
import user.watcher.LevelWatcher;

import user.watcher.OverallWatcher;
import zengine.core.Zengine;
import zengine.interfaces.GameEngine;

/**
 * This class contains a method that is used by Zengine to initialize your game.
 * Use it to define rooms, fonts, to initialize variables and to create the
 * initial conditions of your game.
 */
public final class ZengineInitializer {

    private GameEngine z() {
        return Zengine.getEngine();
    }

    /**
     * this method is called once by Zengine before the game starts. It is also
     * called once every time the game restarts.
     */
    public void initialize() {

        // carico i font che userò nel gioco
        z().fontDefine("Emulogic", "emulogic", 64);
        z().fontDefine("Emulogic", "emulogicSmall", 32);
        z().fontDefine("Emulogic", "emulogicExtraSmall", 16);
        z().fontDefine("GoodTimesRg", "something", 16);
        z().fontDefine("EurostileNormal", "eurostile", 16);

        z().roomDefine(RoomsNames.MAIN_MENU.getValue(), RecurrentNumbers.RESOLUTION_X.getValue(),
                RecurrentNumbers.RESOLUTION_Y.getValue());
        z().roomAddElement(RoomsNames.MAIN_MENU.getValue(), MenuClassesNames.PLAY_BUTTON.getValue(),
                RecurrentNumbers.MAIN_MENU_BUTTON_X.getValue(), RecurrentNumbers.MAIN_MENU_FIRST_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.MAIN_MENU.getValue(), MenuClassesNames.HOWTOPLAY_BUTTON.getValue(),
                RecurrentNumbers.MAIN_MENU_BUTTON_X.getValue(), RecurrentNumbers.MAIN_MENU_SECOND_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.MAIN_MENU.getValue(), MenuClassesNames.CREDITS_BUTTON.getValue(),
                RecurrentNumbers.MAIN_MENU_BUTTON_X.getValue(), RecurrentNumbers.MAIN_MENU_THIRD_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.MAIN_MENU.getValue(), MenuClassesNames.QUIT_BUTTON.getValue(),
                RecurrentNumbers.MAIN_MENU_BUTTON_X.getValue(), RecurrentNumbers.MAIN_MENU_FOURTH_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.MAIN_MENU.getValue(), MenuClassesNames.TITLE.getValue(),
                RecurrentNumbers.MAIN_MENU_TITLE_X.getValue(), RecurrentNumbers.MAIN_MENU_TITLE_Y.getValue());
        z().roomAddElement(RoomsNames.MAIN_MENU.getValue(), MenuClassesNames.MUSIC_LOOPER.getValue(), 0, 0);
        z().roomAddElement(RoomsNames.MAIN_MENU.getValue(), MenuClassesNames.MENU_BACKGROUND.getValue(), 0, 0);
        z().roomSetCreationCode(RoomsNames.MAIN_MENU.getValue(), s -> {
            z().viewSetScale(1);
            if (!z().musicIsPlaying("menuMusic")) {
                z().musicPlay("menuMusic");
            }
            z().viewSetPosition(RecurrentNumbers.POINT_OF_VIEW_X.getValue(), RecurrentNumbers.POINT_OF_VIEW_Y.getValue());
            z().viewSetLocked(true);
        });

        z().roomDefine(RoomsNames.HOWTOPLAY.getValue(), RecurrentNumbers.RESOLUTION_X.getValue(),
                RecurrentNumbers.RESOLUTION_Y.getValue());
        z().roomAddElement(RoomsNames.HOWTOPLAY.getValue(), MenuClassesNames.HOWTOPLAY.getValue(),
                RecurrentNumbers.HOWTOPLAY_BUTTON_X.getValue(), RecurrentNumbers.HOWTOPLAY_BUTTON_X.getValue());
        z().roomSetCreationCode(RoomsNames.HOWTOPLAY.getValue(), s -> {
            z().viewSetScale(1);
            z().viewSetPosition(RecurrentNumbers.POINT_OF_VIEW_X.getValue(), RecurrentNumbers.POINT_OF_VIEW_Y.getValue());
            z().viewSetLocked(true);
        });

        z().roomDefine(RoomsNames.CREDITS.getValue(), RecurrentNumbers.RESOLUTION_X.getValue(),
                RecurrentNumbers.RESOLUTION_Y.getValue());
        z().roomAddElement(RoomsNames.CREDITS.getValue(), MenuClassesNames.CREDITS.getValue(),
                RecurrentNumbers.CREDITS_BUTTON_X.getValue(), RecurrentNumbers.CREDITS_BUTTON_X.getValue());
        z().roomSetCreationCode(RoomsNames.CREDITS.getValue(), s -> {
            z().viewSetScale(1);
            z().musicPlay("credits");
            z().viewSetPosition(RecurrentNumbers.POINT_OF_VIEW_X.getValue(), RecurrentNumbers.POINT_OF_VIEW_Y.getValue());
            z().viewSetLocked(true);
        });

        z().roomDefine(RoomsNames.POT.getValue(), RecurrentNumbers.RESOLUTION_X.getValue(),
                RecurrentNumbers.RESOLUTION_Y.getValue());
        z().roomAddElement(RoomsNames.POT.getValue(), MenuClassesNames.HEALTH_BUTTON.getValue(),
                RecurrentNumbers.POT_ROOM_LEFT_BUTTON_X.getValue(), RecurrentNumbers.POT_ROOM_UPPER_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.POT.getValue(), MenuClassesNames.LIFE_BUTTON.getValue(),
                RecurrentNumbers.POT_ROOM_RIGHT_BUTTON_X.getValue(), RecurrentNumbers.POT_ROOM_UPPER_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.POT.getValue(), MenuClassesNames.DAMAGE_BUTTON.getValue(),
                RecurrentNumbers.POT_ROOM_LEFT_BUTTON_X.getValue(), RecurrentNumbers.POT_ROOM_MEDIUM_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.POT.getValue(), MenuClassesNames.PPS_BUTTON.getValue(),
                RecurrentNumbers.POT_ROOM_RIGHT_BUTTON_X.getValue(), RecurrentNumbers.POT_ROOM_MEDIUM_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.POT.getValue(), MenuClassesNames.SPEED_BUTTON.getValue(),
                RecurrentNumbers.POT_ROOM_LEFT_BUTTON_X.getValue(), RecurrentNumbers.POT_ROOM_LOWER_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.POT.getValue(), MenuClassesNames.UTILIZABLE_POINT.getValue(),
                RecurrentNumbers.POT_ROOM_RIGHT_BUTTON_X.getValue(), RecurrentNumbers.POT_ROOM_LOWER_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.POT.getValue(), MenuClassesNames.NEXT_LEVEL_BUTTON.getValue(),
                RecurrentNumbers.POT_ROOM_NEXT_LEVEL_BUTTON_X.getValue(),
                RecurrentNumbers.POT_ROOM_NEXT_LEVEL_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.POT.getValue(), MenuClassesNames.MUSIC_LOOPER.getValue(), 0, 0);
        z().roomAddElement(RoomsNames.POT.getValue(), MenuClassesNames.MENU_BACKGROUND.getValue(), 0, 0);
        z().roomSetCreationCode(RoomsNames.POT.getValue(), s -> {
            z().viewSetScale(1);
            z().musicPlay("upgradeMusic");
            z().viewSetPosition(RecurrentNumbers.POINT_OF_VIEW_X.getValue(), RecurrentNumbers.POINT_OF_VIEW_Y.getValue());
            z().viewSetLocked(true);
        });

        z().roomDefine(RoomsNames.LEVEL1.getValue(), RecurrentNumbers.LEVEL_RESOLUTION_X.getValue(),
                RecurrentNumbers.LEVEL_RESOLUTION_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL1.getValue(), MenuClassesNames.OBJ_LIFE.getValue(),
                RecurrentNumbers.STAGE_LEVEL_RIGHT_BUTTON_X.getValue(), RecurrentNumbers.STAGE_LEVEL_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL1.getValue(), MenuClassesNames.OBJ_SCORE.getValue(),
                RecurrentNumbers.STAGE_LEVEL_LEFT_BUTTON_X.getValue(), RecurrentNumbers.STAGE_LEVEL_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL1.getValue(), MenuClassesNames.OBJ_TIME.getValue(),
                RecurrentNumbers.STAGE_LEVEL_CENTRAL_BUTTON_X.getValue(), RecurrentNumbers.STAGE_LEVEL_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL1.getValue(), MenuClassesNames.MUSIC_LOOPER.getValue(), 0, 0);
        z().roomAddElement(RoomsNames.LEVEL1.getValue(), MenuClassesNames.STAGE_BACKGROUND.getValue(), 0, 0);
        z().roomAddElement(RoomsNames.LEVEL1.getValue(), Objects.PLAYER_SHIP.getValue(),
                RecurrentNumbers.PLAYER_SPAWN_X.getValue(), RecurrentNumbers.PLAYER_SPAWN_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL1.getValue(), Objects.VIEW_CONTROLLER.getValue(), 0, 0);
        z().roomSetCreationCode(RoomsNames.LEVEL1.getValue(), s -> {
            final ObjPlayerShip ship = (ObjPlayerShip) z().instanceGet(Objects.PLAYER_SHIP.getValue());
            ship.initializeShipStats(OverallWatcher.getWatcher().getHealth(), OverallWatcher.getWatcher().getDamage(),
                    OverallWatcher.getWatcher().getProjectilePerSecond(), OverallWatcher.getWatcher().getSpeed());
            LevelWatcher.getWatcher().reset();
            LevelWatcher.getWatcher().setLife(OverallWatcher.getWatcher().getLife());
            z().musicPlay("levelMusic2");
            // wave 0 is "cleared"; this is needed to start the level.
            LevelWatcher.getWatcher().clearedWave();
        });
        z().roomSetEndingCode(RoomsNames.LEVEL1.getValue(), s -> {
            LevelWatcher.getWatcher().passToOverallWatcher();
        });

        z().roomDefine(RoomsNames.LEVEL2.getValue(), RecurrentNumbers.LEVEL_RESOLUTION_X.getValue(),
                RecurrentNumbers.LEVEL_RESOLUTION_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL2.getValue(), MenuClassesNames.OBJ_LIFE.getValue(),
                RecurrentNumbers.STAGE_LEVEL_RIGHT_BUTTON_X.getValue(), RecurrentNumbers.STAGE_LEVEL_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL2.getValue(), MenuClassesNames.OBJ_SCORE.getValue(),
                RecurrentNumbers.STAGE_LEVEL_LEFT_BUTTON_X.getValue(), RecurrentNumbers.STAGE_LEVEL_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL2.getValue(), MenuClassesNames.OBJ_TIME.getValue(),
                RecurrentNumbers.STAGE_LEVEL_CENTRAL_BUTTON_X.getValue(), RecurrentNumbers.STAGE_LEVEL_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL2.getValue(), MenuClassesNames.MUSIC_LOOPER.getValue(), 0, 0);
        z().roomAddElement(RoomsNames.LEVEL2.getValue(), MenuClassesNames.STAGE_BACKGROUND.getValue(), 0, 0);
        z().roomAddElement(RoomsNames.LEVEL2.getValue(), Objects.PLAYER_SHIP.getValue(),
                RecurrentNumbers.PLAYER_SPAWN_X.getValue(), RecurrentNumbers.PLAYER_SPAWN_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL2.getValue(), Objects.VIEW_CONTROLLER.getValue(), 0, 0);
        z().roomSetCreationCode(RoomsNames.LEVEL2.getValue(), s -> {
            final ObjPlayerShip ship = (ObjPlayerShip) z().instanceGet(Objects.PLAYER_SHIP.getValue());
            ship.initializeShipStats(OverallWatcher.getWatcher().getHealth(), OverallWatcher.getWatcher().getDamage(),
                    OverallWatcher.getWatcher().getProjectilePerSecond(), OverallWatcher.getWatcher().getSpeed());
            LevelWatcher.getWatcher().reset();
            LevelWatcher.getWatcher().setLife(OverallWatcher.getWatcher().getLife());
            z().musicPlay("levelMusic2");
            // wave 0 is "cleared"; this is needed to start the level.
            LevelWatcher.getWatcher().clearedWave();
        });
        z().roomSetEndingCode(RoomsNames.LEVEL2.getValue(), s -> {
            LevelWatcher.getWatcher().passToOverallWatcher();
        });

        z().roomDefine(RoomsNames.LEVEL3.getValue(), RecurrentNumbers.LEVEL_RESOLUTION_X.getValue(),
                RecurrentNumbers.LEVEL_RESOLUTION_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL3.getValue(), MenuClassesNames.OBJ_LIFE.getValue(),
                RecurrentNumbers.STAGE_LEVEL_RIGHT_BUTTON_X.getValue(), RecurrentNumbers.STAGE_LEVEL_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL3.getValue(), MenuClassesNames.OBJ_SCORE.getValue(),
                RecurrentNumbers.STAGE_LEVEL_LEFT_BUTTON_X.getValue(), RecurrentNumbers.STAGE_LEVEL_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL3.getValue(), MenuClassesNames.OBJ_TIME.getValue(),
                RecurrentNumbers.STAGE_LEVEL_CENTRAL_BUTTON_X.getValue(), RecurrentNumbers.STAGE_LEVEL_BUTTON_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL3.getValue(), MenuClassesNames.MUSIC_LOOPER.getValue(), 0, 0);
        z().roomAddElement(RoomsNames.LEVEL3.getValue(), MenuClassesNames.STAGE_BACKGROUND.getValue(), 0, 0);
        z().roomAddElement(RoomsNames.LEVEL3.getValue(), Objects.PLAYER_SHIP.getValue(),
                RecurrentNumbers.PLAYER_SPAWN_X.getValue(), RecurrentNumbers.PLAYER_SPAWN_Y.getValue());
        z().roomAddElement(RoomsNames.LEVEL3.getValue(), Objects.VIEW_CONTROLLER.getValue(), 0, 0);
        z().roomSetCreationCode(RoomsNames.LEVEL3.getValue(), s -> {
            final ObjPlayerShip ship = (ObjPlayerShip) z().instanceGet(Objects.PLAYER_SHIP.getValue());
            ship.initializeShipStats(OverallWatcher.getWatcher().getHealth(), OverallWatcher.getWatcher().getDamage(),
                    OverallWatcher.getWatcher().getProjectilePerSecond(), OverallWatcher.getWatcher().getSpeed());
            LevelWatcher.getWatcher().reset();
            LevelWatcher.getWatcher().setLife(OverallWatcher.getWatcher().getLife());
            z().musicPlay("levelMusic2");
            // wave 0 is "cleared"; this is needed to start the level.
            LevelWatcher.getWatcher().clearedWave();
        });

        z().roomDefine(RoomsNames.GAME_OVER.getValue(), RecurrentNumbers.RESOLUTION_X.getValue(),
                RecurrentNumbers.RESOLUTION_Y.getValue());
        z().roomAddElement(RoomsNames.GAME_OVER.getValue(), MenuClassesNames.GAME_OVER_BUTTON.getValue(),
                RecurrentNumbers.GAME_OVER_BUTTON_X.getValue(), RecurrentNumbers.GAME_OVER_BUTTON_Y.getValue());
        z().roomSetCreationCode(RoomsNames.GAME_OVER.getValue(), s -> {
            z().viewSetScale(1);
            z().musicPlay("gameOver");
            z().viewSetPosition(RecurrentNumbers.POINT_OF_VIEW_X.getValue(), RecurrentNumbers.POINT_OF_VIEW_Y.getValue());
            z().viewSetLocked(true);
        });

        // finally, load the first room
        z().roomGoto(RoomsNames.MAIN_MENU.getValue());

        // uncomment the following line to start the debug test instead of the
        // game
        // -------------------------------
        // user.debug.TestUnit.startTest();
    }
}