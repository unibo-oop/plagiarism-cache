package user.debug.automatedtest;

import static zengine.constants.ZengineKeyboardConstant.VK_R;

import zengine.constants.ZengineColor;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class runs an automated test and displays the result.
 */
public class ObjAutotest extends GameObject {

    private boolean testResult = true;

    @Override
    public void create() {
        runTest();
        displayIfTrue(testResult, "GLOBAL TEST RESULT");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        if (z().keyboardCheckPressed(VK_R)) {
            z().gameRestart();
        }
    }

    @Override
    public void draw() {
        if (testResult) {
            z().drawTextHUD("test is SUCCESSFULL", 10, 10, "eurostile", ZengineColor.C_GREEN);
        } else {
            z().drawTextHUD("test is FAILED", 10, 10, "eurostile", ZengineColor.C_RED);
        }
        z().drawTextHUD("press R to restart the application. Info is displayed on the console", 10, 10 + 10 + 10, "eurostile",
                ZengineColor.C_RED);
    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

    private void displayIfTrue(final boolean test, final String testName) {
        if (test) {
            z().loggerMessage(testName + ": successful");
        } else {
            z().loggerWarning(testName + ": failed");
        }
    }

    private void updateTestResult(final boolean updateValue) {
        if (testResult && (!updateValue)) {
            testResult = false;
        }
    }

    private void runTest() {
        // this method is long because it contains an automated test. There is
        // no use in splitting it.
        boolean result = true;
        final String room = "debug/dummyRoom";
        final int bigAmount = 1000;
        final String dummy = "debug.automatedtest.ObjDummy";

        z().loggerMessage("");
        z().loggerMessage("EXPECT SOME WARNINGS, it is ok");
        z().loggerMessage("");

        result = !z().roomDefine("thisWillFail", -1, -1);
        displayIfTrue(result, "can't define room outside initialize()");
        updateTestResult(result);

        result = z().roomAddElement(room, "debug.automatedtest.ObjAutotest", 0, 0);
        displayIfTrue(result, "adding one element to room");
        updateTestResult(result);

        result = z().roomGetNumberOfElements(room) == 1;
        displayIfTrue(result, "there is one element in room");
        updateTestResult(result);

        result = z().roomClear(room);
        displayIfTrue(result, "cleared room");
        updateTestResult(result);

        result = z().roomGetWidth(room) == 0;
        displayIfTrue(result, "room size is 0");
        updateTestResult(result);

        for (int i = 0; i < bigAmount; i++) {
            z().instanceCreate(0, 0, dummy);
        }
        result = z().instanceNumber(dummy) == bigAmount;
        displayIfTrue(result, "created " + bigAmount + " ObjDummy");
        updateTestResult(result);

        result = z().roomAddSituation(room);
        displayIfTrue(result, "added current situation to room");
        updateTestResult(result);

        result = z().roomGetNumberOfElements(room) == z().instanceNumberGeneral();
        displayIfTrue(result, "room correctly stored the current situation");
        updateTestResult(result);

        result = z().instanceDestroyAll(dummy, null);
        displayIfTrue(result, "cleared all ObjDummy");
        updateTestResult(result);

        result = z().instanceNumber(dummy) == 0;
        displayIfTrue(result, "no ObjDummy is left");
        updateTestResult(result);

        for (int i = 0; i < bigAmount; i++) {
            z().instanceCreate(0, 0, dummy);
        }
        z().with(dummy, self -> {
            z().instanceDestroy(self);
        });
        result = !z().instanceExists(dummy);
        displayIfTrue(result, "created and killed other " + bigAmount + " dummies");
        updateTestResult(result);

        final GameObject g = z().instanceCreate(0, 0, dummy);
        int startElems = z().instanceNumberGeneral();
        z().instanceAdd(0, 0, g);
        result = z().instanceNumberGeneral() == startElems;
        displayIfTrue(result, "unable to add the same object twice");
        updateTestResult(result);

        result = z().instanceExists(dummy);
        displayIfTrue(result, "a dummy exists");
        updateTestResult(result);

        result = !z().instanceExists("class.that.does.not.exist");
        displayIfTrue(result, "an invalid class does not exists");
        updateTestResult(result);

        z().instanceCreate(0, 0, dummy);
        result = z().instanceGet(dummy).equals(z().instanceGet(dummy));
        displayIfTrue(result, "instanceGet() returns the same reference twice");
        updateTestResult(result);

        result = !z().musicExists(z().musicCurrent());
        displayIfTrue(result, "no existing music is being played");
        updateTestResult(result);

        result = !z().soundExists("sound/that/does/not/exist");
        displayIfTrue(result, "invalid sound does not exist");
        updateTestResult(result);

        result = !z().musicExists("music/that/does/not/exist");
        displayIfTrue(result, "invalid music does not exist");
        updateTestResult(result);

        result = !z().fontExists("font/that/does/not/exist");
        displayIfTrue(result, "invalid font does not exist");
        updateTestResult(result);

        result = !z().fontAliasExists("font/alias/that/does/not/exist");
        displayIfTrue(result, "invalid font alias does not exist");
        updateTestResult(result);

        result = !z().spriteExists("sprite/that/does/not/exist");
        displayIfTrue(result, "invalid sprite does not exist");
        updateTestResult(result);

        result = !z().roomExists("room/that/does/not/exist");
        displayIfTrue(result, "invalid room does not exist");
        updateTestResult(result);

        startElems = z().roomGetNumberOfElements(room);
        result = z().roomMerge(room, room, false);
        displayIfTrue(result, "merged a room with itself");
        updateTestResult(result);

        result = z().roomGetNumberOfElements(room) == startElems * 2;
        displayIfTrue(result, "merged room has twice the elements");
        updateTestResult(result);

        boolean ris = true;
        double num;
        for (int i = 0; i < bigAmount * 4; i++) {
            num = z().randomRange(-1, 1);
            if (!z().valueInRange(num, -1d, 1d)) {
                ris = false;
                break;
            }
        }
        result = ris;
        displayIfTrue(result, "randomRange() consistency");
        updateTestResult(result);

        int numInt;
        ris = true;
        for (int i = 0; i < bigAmount * 2; i++) {
            numInt = z().choose(-1, 1, 64);
            if (!(numInt == -1 || numInt == 1 || numInt == 64)) {
                ris = false;
                break;
            }
        }
        result = ris;
        displayIfTrue(result, "choose() consistency");
        updateTestResult(result);

        final int modulus = 58;
        num = 0;
        ris = true;
        for (int i = 0; i < bigAmount * 2; i++) {
            num = num - 1 / 3;
            num = z().wrapToModulus(num, modulus);
            if (!z().valueInRange(num, 0d, (double) modulus)) {
                ris = false;
                break;
            }
        }
        result = ris;
        displayIfTrue(result, "wrapToModulus() consistency");
        updateTestResult(result);
    }
}
