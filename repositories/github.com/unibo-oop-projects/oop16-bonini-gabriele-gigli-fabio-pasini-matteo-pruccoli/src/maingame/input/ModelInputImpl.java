package maingame.input;

import util.Vector2;

/**
 * Model per input di gioco.
 */
public class ModelInputImpl implements ModelInput {

    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;
    private boolean esc;
    private boolean enter;
    private boolean space;
    private Vector2<Integer> mouseCoordinate;
    private int mouseButton;

    @Override
    public boolean isPressedUp() {
        return this.up;
    }

    @Override
    public boolean isPressedDown() {
        return this.down;
    }

    @Override
    public boolean isPressedRight() {
        return this.right;
    }

    @Override
    public boolean isPressedLeft() {
        return this.left;
    }

    @Override
    public boolean isPressedEsc() {
        return this.esc;
    }

    @Override
    public boolean isPressedEnter() {
        return this.enter;
    }

    @Override
    public boolean isPressedSpace() {
        return this.space;
    }

    @Override
    public void isUp(final boolean pressedUp) {
        this.up = pressedUp;
    }

    @Override
    public void setDown(final boolean pressedDown) {
        this.down = pressedDown;
    }

    @Override
    public void setRight(final boolean pressedright) {
        this.right = pressedright;
    }

    @Override
    public void setLeft(final boolean pressedleft) {
        this.left = pressedleft;
    }

    @Override
    public void setEsc(final boolean pressedesc) {
        this.esc = pressedesc;
    }

    @Override
    public void setEnter(final boolean pressedenter) {
        this.enter = pressedenter;
    }

    @Override
    public void setSpace(final boolean pressedspace) {
        this.space = pressedspace;
    }
    @Override
    public void resetKeyBoard() {
        this.setDown(false);
        this.setEnter(false);
        this.setEsc(false);
        this.setLeft(false);
        this.setRight(false);
        this.setSpace(false);
        this.isUp(false);
    }

    @Override
    public Vector2<Integer> getMouseCoordinate() {
        return this.mouseCoordinate;
    }

    @Override
    public int getButton() {
        return this.mouseButton;
    }

    @Override
    public void setMouseCoordinate(final Vector2<Integer> coordinate) {
        this.mouseCoordinate = coordinate;
    }

    @Override
    public void setMouseButton(final int button) {
        this.mouseButton = button;
    }

}
