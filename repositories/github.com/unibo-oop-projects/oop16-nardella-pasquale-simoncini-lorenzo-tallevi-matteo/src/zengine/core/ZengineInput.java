package zengine.core;

import java.awt.MouseInfo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import zengine.constants.ZengineKeyboardConstant;
import zengine.constants.ZengineMouseConstant;
import zengine.interfaces.GameFunctionsInput;

final class ZengineInput implements ZengineComponent, GameFunctionsInput {

    private boolean linked; // = false;
    private static ZengineInput input = new ZengineInput();

    // valid integer values that must be intercepted
    private final Set<Integer> validKeyboardValues = new HashSet<>();
    private final Set<Integer> validMouseValues = new HashSet<>();

    // map that describes, update by update, what is the situation of each mouse
    // button (pressed, being pressed or released)
    private final Map<Integer, Boolean> mousePressedMap = new HashMap<>();
    private final Map<Integer, Boolean> mouseSteppedMap = new HashMap<>();
    private final Map<Integer, Boolean> mouseReleasedMap = new HashMap<>();

    // map that describes, update by update, what is the situation of each
    // keyboard key (pressed, being pressed or released)
    private final Map<Integer, Boolean> keyPressedMap = new HashMap<>();
    private final Map<Integer, Boolean> keySteppedMap = new HashMap<>();
    private final Map<Integer, Boolean> keyReleasedMap = new HashMap<>();

    private ZengineInput() {
        initialize();
    }

    public static ZengineInput getInput() {
        return input;
    }

    @Override
    public ZengineComponent getComponent() {
        return input;
    }

    @Override
    public void initialize() {
        inputMapInit();
        link();
    }

    @Override
    public void reinitialize() {
        inputMapUpdate();
    }

    @Override
    public void restoreDefaultValues() {
        inputMapUpdate();
    }

    @Override
    public void link() {
        if (!linked) {
            linked = true;
            // adds the listener for keyboard events
            ZengineGUI.getGUI().addKeyListener(new TAdapter());

            // adds the listener for mouse events
            ZengineGUI.getGUI().addMouseListener(new MAdapter());
        }
    }

    @Override
    public void update() {
        inputMapUpdate();
    }

    private void inputMapInit() {
        // initializes the input maps (nothing pressed, released ecc..)
        for (final ZengineKeyboardConstant c : ZengineKeyboardConstant.values()) {
            validKeyboardValues.add(c.getValue());
            keyPressedMap.put(c.getValue(), false);
            keySteppedMap.put(c.getValue(), false);
            keyReleasedMap.put(c.getValue(), false);
        }

        for (final ZengineMouseConstant c : ZengineMouseConstant.values()) {
            validMouseValues.add(c.getValue());
            mousePressedMap.put(c.getValue(), false);
            mouseSteppedMap.put(c.getValue(), false);
            mouseReleasedMap.put(c.getValue(), false);
        }
    }

    @Override
    public boolean keyboardCheckPressed(final ZengineKeyboardConstant keyCode) {
        if (keyCode.equals(ZengineKeyboardConstant.VK_ANY)) {
            return keyPressedMap.containsValue(true);
        } else {
            return keyPressedMap.get(keyCode.getValue());
        }
    }

    @Override
    public boolean keyboardCheck(final ZengineKeyboardConstant keyCode) {
        if (keyCode.equals(ZengineKeyboardConstant.VK_ANY)) {
            return keySteppedMap.containsValue(true);
        } else {
            return keySteppedMap.get(keyCode.getValue());
        }
    }

    @Override
    public boolean keyboardCheckReleased(final ZengineKeyboardConstant keyCode) {
        if (keyCode.equals(ZengineKeyboardConstant.VK_ANY)) {
            return keyReleasedMap.containsValue(true);
        } else {
            return keyReleasedMap.get(keyCode.getValue());
        }
    }

    public void inputMapUpdate() {
        keyboardMapUpdate();
        mouseMapUpdate();
    }

    private void keyboardMapUpdate() {
        // update boolean maps used by keyboard functions
        for (final Entry<Integer, Boolean> e : keyPressedMap.entrySet()) {
            if (e.getValue()) {
                e.setValue(false);
            }
        }
        for (final Entry<Integer, Boolean> e : keyReleasedMap.entrySet()) {
            if (e.getValue()) {
                e.setValue(false);
            }
        }
    }

    private void mouseMapUpdate() {
        // update boolean maps used by mouse functions
        for (final Entry<Integer, Boolean> e : mousePressedMap.entrySet()) {
            if (e.getValue()) {
                e.setValue(false);
            }
        }
        for (final Entry<Integer, Boolean> e : mouseReleasedMap.entrySet()) {
            if (e.getValue()) {
                e.setValue(false);
            }
        }
    }

    @Override
    public int displayMouseX() {
        return MouseInfo.getPointerInfo().getLocation().x;
    }

    private void notifyInvalidKeyboardKey() {
        // nothing to do here, but this is called when an invalid key is pressed
        // if you want to do more complicated things in the future
    }

    private void notifyInvalidMouseButton() {
        // nothing to do here, but this is called when an invalid mouse button
        // if you want to do more complicated things in the future
        // is pressed
    }

    @Override
    public int displayMouseY() {
        return MouseInfo.getPointerInfo().getLocation().y;
    }

    @Override
    public double mouseX() {
        return Zengine.getEngine().windowToWorldX(windowMouseX());
    }

    @Override
    public double mouseY() {
        return Zengine.getEngine().windowToWorldY(windowMouseY());
    }

    @Override
    public int windowMouseX() {
        return displayMouseX() - ZengineGUI.getGUI().getLocationOnScreen().x;
    }

    @Override
    public int windowMouseY() {
        return displayMouseY() - ZengineGUI.getGUI().getLocationOnScreen().y;
    }

    @Override
    public boolean mouseCheck(final ZengineMouseConstant mouseButtonCode) {
        if (mouseButtonCode.equals(ZengineMouseConstant.MB_ANY)) {
            return mouseSteppedMap.containsValue(true);
        } else {
            return mouseSteppedMap.get(mouseButtonCode.getValue());
        }
    }

    @Override
    public boolean mouseCheckPressed(final ZengineMouseConstant mouseButtonCode) {
        if (mouseButtonCode.equals(ZengineMouseConstant.MB_ANY)) {
            return mousePressedMap.containsValue(true);
        } else {
            return mousePressedMap.get(mouseButtonCode.getValue());
        }
    }

    @Override
    public boolean mouseCheckReleased(final ZengineMouseConstant mouseButtonCode) {
        if (mouseButtonCode.equals(ZengineMouseConstant.MB_ANY)) {
            return mouseReleasedMap.containsValue(true);
        } else {
            return mouseReleasedMap.get(mouseButtonCode.getValue());
        }
    }

    private class TAdapter extends KeyAdapter {
        // this class handles keyboard events by putting correct values into
        // boolean maps
        @Override
        public void keyPressed(final KeyEvent e) {
            if (validKeyboardValues.contains(e.getKeyCode())) {
                if (!keySteppedMap.get(e.getKeyCode())) {
                    keySteppedMap.put(e.getKeyCode(), true);
                    keyPressedMap.put(e.getKeyCode(), true);
                } else {
                    notifyInvalidKeyboardKey();
                }
            }
        }

        @Override
        public void keyReleased(final KeyEvent e) {
            if (validKeyboardValues.contains(e.getKeyCode())) {
                keySteppedMap.put(e.getKeyCode(), false);
                keyReleasedMap.put(e.getKeyCode(), true);
            }
        }
    }

    private class MAdapter extends MouseAdapter {
        // this class handles keyboard events by putting correct values into
        // boolean maps
        @Override
        public void mousePressed(final MouseEvent e) {
            if (validMouseValues.contains(e.getButton())) {
                if (!mouseSteppedMap.get(e.getButton())) {
                    mouseSteppedMap.put(e.getButton(), true);
                    mousePressedMap.put(e.getButton(), true);
                }
            } else {
                notifyInvalidMouseButton();
            }
        }

        @Override
        public void mouseReleased(final MouseEvent e) {
            if (validMouseValues.contains(e.getButton())) {
                mouseSteppedMap.put(e.getButton(), false);
                mouseReleasedMap.put(e.getButton(), true);
            }
        }
    }
}
