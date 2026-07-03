package zengine.constants;

import java.awt.event.KeyEvent;

import zengine.interfaces.ZengineInputConstant;

/**
 * This enum is a list of keyboard keys that are passed as argument to some
 * particular functions.
 */
public enum ZengineKeyboardConstant implements ZengineInputConstant {
    // direction keys
    /**
     * Constant representing the up arrow key.
     */
    VK_UP(KeyEvent.VK_UP),

    /**
     * Constant representing the down arrow key.
     */
    VK_DOWN(KeyEvent.VK_DOWN),

    /**
     * Constant representing the left arrow key.
     */
    VK_LEFT(KeyEvent.VK_LEFT),

    /**
     * Constant representing the right arrow key.
     */
    VK_RIGHT(KeyEvent.VK_RIGHT),

    // letter keys
    /**
     * Constant representing the keyboard letter A.
     */
    VK_A(KeyEvent.VK_A),

    /**
     * Constant representing the keyboard letter B.
     */
    VK_B(KeyEvent.VK_B),

    /**
     * Constant representing the keyboard letter C.
     */
    VK_C(KeyEvent.VK_C),

    /**
     * Constant representing the keyboard letter D.
     */
    VK_D(KeyEvent.VK_D),

    /**
     * Constant representing the keyboard letter E.
     */
    VK_E(KeyEvent.VK_E),

    /**
     * Constant representing the keyboard letter F.
     */
    VK_F(KeyEvent.VK_F),

    /**
     * Constant representing the keyboard letter G.
     */
    VK_G(KeyEvent.VK_G),

    /**
     * Constant representing the keyboard letter H.
     */
    VK_H(KeyEvent.VK_H),

    /**
     * Constant representing the keyboard letter I.
     */
    VK_I(KeyEvent.VK_I),

    /**
     * Constant representing the keyboard letter J.
     */
    VK_J(KeyEvent.VK_J),

    /**
     * Constant representing the keyboard letter K.
     */
    VK_K(KeyEvent.VK_K),

    /**
     * Constant representing the keyboard letter L.
     */
    VK_L(KeyEvent.VK_L),

    /**
     * Constant representing the keyboard letter M.
     */
    VK_M(KeyEvent.VK_M),

    /**
     * Constant representing the keyboard letter N.
     */
    VK_N(KeyEvent.VK_N),

    /**
     * Constant representing the keyboard letter O.
     */
    VK_O(KeyEvent.VK_O),

    /**
     * Constant representing the keyboard letter P.
     */
    VK_P(KeyEvent.VK_P),

    /**
     * Constant representing the keyboard letter Q.
     */
    VK_Q(KeyEvent.VK_Q),

    /**
     * Constant representing the keyboard letter R.
     */
    VK_R(KeyEvent.VK_R),

    /**
     * Constant representing the keyboard letter S.
     */
    VK_S(KeyEvent.VK_S),

    /**
     * Constant representing the keyboard letter T.
     */
    VK_T(KeyEvent.VK_T),

    /**
     * Constant representing the keyboard letter U.
     */
    VK_U(KeyEvent.VK_U),

    /**
     * Constant representing the keyboard letter V.
     */
    VK_V(KeyEvent.VK_V),

    /**
     * Constant representing the keyboard letter W.
     */
    VK_W(KeyEvent.VK_W),

    /**
     * Constant representing the keyboard letter X.
     */
    VK_X(KeyEvent.VK_X),

    /**
     * Constant representing the keyboard letter Y.
     */
    VK_Y(KeyEvent.VK_Y),

    /**
     * Constant representing the keyboard letter Z.
     */
    VK_Z(KeyEvent.VK_Z),

    // number keys
    /**
     * Constant representing the keyboard digit 1.
     */
    VK_1(KeyEvent.VK_1),

    /**
     * Constant representing the keyboard digit 2.
     */
    VK_2(KeyEvent.VK_2),

    /**
     * Constant representing the keyboard digit 3.
     */
    VK_3(KeyEvent.VK_3),

    /**
     * Constant representing the keyboard digit 4.
     */
    VK_4(KeyEvent.VK_4),

    /**
     * Constant representing the keyboard digit 5.
     */
    VK_5(KeyEvent.VK_5),

    /**
     * Constant representing the keyboard digit 6.
     */
    VK_6(KeyEvent.VK_6),

    /**
     * Constant representing the keyboard digit 7.
     */
    VK_7(KeyEvent.VK_7),

    /**
     * Constant representing the keyboard digit 8.
     */
    VK_8(KeyEvent.VK_8),

    /**
     * Constant representing the keyboard digit 9.
     */
    VK_9(KeyEvent.VK_9),

    /**
     * Constant representing the keyboard digit 0.
     */
    VK_0(KeyEvent.VK_0),

    // numpad
    /**
     * Constant representing the numpad digit 0.
     */
    VK_NUMPAD0(KeyEvent.VK_NUMPAD0),

    /**
     * Constant representing the numpad digit 1.
     */
    VK_NUMPAD1(KeyEvent.VK_NUMPAD1),

    /**
     * Constant representing the numpad digit 2.
     */
    VK_NUMPAD2(KeyEvent.VK_NUMPAD2),

    /**
     * Constant representing the numpad digit 3.
     */
    VK_NUMPAD3(KeyEvent.VK_NUMPAD3),

    /**
     * Constant representing the numpad digit 4.
     */
    VK_NUMPAD4(KeyEvent.VK_NUMPAD4),

    /**
     * Constant representing the numpad digit 5.
     */
    VK_NUMPAD5(KeyEvent.VK_NUMPAD5),

    /**
     * Constant representing the numpad digit 6.
     */
    VK_NUMPAD6(KeyEvent.VK_NUMPAD6),

    /**
     * Constant representing the numpad digit 7.
     */
    VK_NUMPAD7(KeyEvent.VK_NUMPAD7),

    /**
     * Constant representing the numpad digit 8.
     */
    VK_NUMPAD8(KeyEvent.VK_NUMPAD8),

    /**
     * Constant representing the numpad digit 9.
     */
    VK_NUMPAD9(KeyEvent.VK_NUMPAD9),

    // other
    /**
     * Constant representing the spacebar.
     */
    VK_SPACE(KeyEvent.VK_SPACE),

    /**
     * Constant representing the ESC key.
     */
    VK_ESC(KeyEvent.VK_ESCAPE),

    /**
     * Constant representing the ENTER key.
     */
    VK_ENTER(KeyEvent.VK_ENTER),

    /**
     * Constant representing the backspace key.
     */
    VK_BACKSPACE(KeyEvent.VK_BACK_SPACE),

    /**
     * Constant representing the shift key (Maiusc on italian keyboards).
     */
    VK_SHIFT(KeyEvent.VK_SHIFT),

    /**
     * Constant representing the CTRL key.
     */
    VK_CTRL(KeyEvent.VK_CONTROL),

    /**
     * Constant representing the caps lock key (Block Maiusc on italian
     * keyboards).
     */
    VK_CAPSLOCK(KeyEvent.VK_CAPS_LOCK),

    /**
     * Constant representing the tab key.
     */
    VK_TAB(KeyEvent.VK_TAB),

    /**
     * Constant representing the alt key.
     */
    VK_ALT(KeyEvent.VK_ALT),

    /**
     * Constant representing the alt GR key.
     */
    VK_ALTGR(KeyEvent.VK_ALT_GRAPH),

    // special
    /**
     * Constant representing whatsoever button.
     */
    VK_ANY(KeyEvent.VK_UNDEFINED);

    private int value; // = 0;

    ZengineKeyboardConstant(final int keyCode) {
        value = keyCode;
    }

    @Override
    public int getValue() {
        return value;
    }
}
