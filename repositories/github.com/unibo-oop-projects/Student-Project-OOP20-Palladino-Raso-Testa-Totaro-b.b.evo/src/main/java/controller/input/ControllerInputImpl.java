package controller.input;

public class ControllerInputImpl implements ControllerInput {

    private boolean moveLeft;
    private boolean moveRight;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMoveLeft() {
        return this.moveLeft;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canMoveRight() {
        return this.moveRight;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMoveRight(final boolean cond) {
        this.moveRight = cond;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMoveLeft(final boolean cond) {
        this.moveLeft = cond;
    }

}
