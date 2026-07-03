package user.debug;

import zengine.core.Zengine;
import zengine.interfaces.GameEngine;

/**
 * This class only purpose is to define rooms for automated and semi-automated
 * tests.
 */
public final class TestUnit {

    private TestUnit() {
    }

    private static GameEngine z() {
        return Zengine.getEngine();
    }

    /**
     * defines test rooms and starts the test.
     */
    public static void startTest() {
        final String roomWelcome = "debug/roomWelcome";

        final String roomTitle = "debug/roomTitle";
        final String roomGame = "debug/roomGame";
        final String roomCredits = "debug/roomCredits";
        final String roomDemo3D0 = "debug/demo3D/room0";
        final String roomDemo3D1 = "debug/demo3D/room1";
        final String roomBenchmark = "debug/roomBenchmark";
        final String roomAutomated = "debug/roomAutomated";

        final int resX = 800;
        final int resY = 600;

        final int titleY = 40;
        final int buttonX = 100;
        final int buttonY1 = 300;
        final int buttonY2 = 350;
        final int buttonY3 = 400;

        final int welcomeButtonY1 = 200;
        final int welcomeButtonY2 = 270;
        final int welcomeButtonY3 = 340;
        final int welcomeButtonY4 = 410;

        final int stars = 500;
        final int balls = 30;

        final int roomSize3D = 3000;
        final int backgroundpos3D = 1200;
        final int spawnmin3D = 1300;
        final int spawnmax3D = 1700;

        // defining welcome room
        z().roomDefine(roomWelcome, resX, resY);
        z().roomAddElement(roomWelcome, "debug.welcomemenu.ObjButtonAutomated", buttonX, welcomeButtonY1);
        z().roomAddElement(roomWelcome, "debug.welcomemenu.ObjButtonBenchmark", buttonX, welcomeButtonY2);
        z().roomAddElement(roomWelcome, "debug.welcomemenu.ObjButtonDemo3D", buttonX, welcomeButtonY3);
        z().roomAddElement(roomWelcome, "debug.welcomemenu.ObjButtonTestGame", buttonX, welcomeButtonY4);
        z().roomSetCreationCode(roomWelcome, s -> {
            z().loggerMessage("welcome to the debug section");
            z().viewSetPosition(resX / 2, resY / 2);
            z().viewSetLocked(true);
        });

        // defining automated test room
        z().roomDefine(roomAutomated, resX, resY);
        z().roomAddElement(roomAutomated, "debug.automatedtest.ObjAutotest", 0, 0);
        z().roomSetCreationCode(roomAutomated, s -> {
            z().viewSetPosition(resX / 2, resY / 2);
            z().viewSetLocked(true);
        });
        // dummy room for tests
        z().roomDefine("debug/dummyRoom", 0, 0);

        // defining benchmark room
        z().roomDefine(roomBenchmark, resX, resY);
        z().roomAddElement(roomBenchmark, "debug.benchmark.ObjBenchmarkController", 0, 0);
        z().roomSetCreationCode(roomBenchmark, s -> {
            z().viewSetPosition(resX / 2, resY / 2);
            z().viewSetLocked(true);
        });

        // defining title room
        z().roomDefine(roomTitle, resX, resY);
        z().roomAddElement(roomTitle, "debug.testgame.ObjTitle", resX / 2, titleY);
        z().roomAddElement(roomTitle, "debug.testgame.ObjMenuButtonPlay", buttonX, buttonY1);
        z().roomAddElement(roomTitle, "debug.testgame.ObjMenuButtonCredits", buttonX, buttonY2);
        z().roomAddElement(roomTitle, "debug.testgame.ObjMenuButtonQuit", buttonX, buttonY3);
        z().roomAddElement(roomTitle, "debug.testgame.ObjMusicLooper", 0, 0);
        z().roomSetCreationCode(roomTitle, s -> {
            z().loggerMessage("this is a creation code");
            z().viewSetPosition(resX / 2, resY / 2);
            z().viewSetLocked(true);
        });

        // defining game room
        z().roomDefine(roomGame, resX, resY);
        for (int i = 0; i < 3; i++) {
            z().roomAddElement(roomGame, "debug.testgame.game.ObjAsteroid", z().random(z().roomGetWidth(roomGame)),
                    z().random(z().roomGetHeight(roomGame)));
        }
        z().roomAddElement(roomGame, "debug.testgame.game.ObjPlane", resX / 2, resY / 2);
        z().roomAddElement(roomGame, "debug.testgame.ObjMusicLooper", 0, 0);

        // defining credits room
        z().roomDefine(roomCredits, resX, resY);
        z().roomAddElement(roomCredits, "debug.testgame.credits.ObjCredits", 0, 0);
        z().roomAddElement(roomCredits, "debug.testgame.ObjMusicLooper", 0, 0);

        // 3D demo rooms
        z().roomDefine(roomDemo3D0, roomSize3D, roomSize3D);
        z().roomAddElement(roomDemo3D0, "debug.demo3d.ObjCameraController", 0, 0);
        z().roomSetCreationCode(roomDemo3D0, s -> {
            z().viewSetScale(1);
            for (int i = 0; i < stars; i++) {
                z().instanceCreate(z().random(roomSize3D), z().random(roomSize3D), "debug.demo3d.Obj3DElement");
            }
            System.out.println(z().instanceNumberGeneral() + " instances are saying hello from " + s);
        });
        z().roomSetEndingCode(roomDemo3D0, s -> {
            System.out.println(z().instanceNumberGeneral() + " instances are saying goodbye from " + s);
        });

        z().roomDefine(roomDemo3D1, roomSize3D, roomSize3D);
        z().roomAddElement(roomDemo3D1, "debug.demo3d.ObjCameraController", 0, 0);
        z().roomAddElement(roomDemo3D1, "debug.demo3d.ObjBackground", backgroundpos3D, backgroundpos3D);
        z().roomSetCreationCode(roomDemo3D1, s -> {
            z().viewSetScale(1);
            for (int i = 0; i < balls; i++) {
                z().instanceCreate(z().randomRange(spawnmin3D, spawnmax3D), z().randomRange(spawnmin3D, spawnmax3D),
                        "debug.demo3d.Obj3DBall");
            }
            System.out.println(z().instanceNumberGeneral() + " instances are saying hello from " + s);
        });
        z().roomSetEndingCode(roomDemo3D1, s -> {
            System.out.println(z().instanceNumberGeneral() + " instances are saying goodbye from " + s);
        });

        // finally, go to the welcome room
        z().roomGoto(roomWelcome);
    }
}
