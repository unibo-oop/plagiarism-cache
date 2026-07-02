package model.physics;

import model.entitiesutil.Body;

/**
 *Abstract class that serves as a model for the more specific classes {@link CharacterMovement}, {@link BubbleMovement}, {@link ObjectMovement}.
 */
public abstract class AbstractMovement implements MovementManager {

    /**
     * {@link Body} is the class where AbstractMovement will update the Object's the position.
     */
    private final Body body;

    /**
     * Class constructor.
     * @param body {@link Body}
     */
    public AbstractMovement(final Body body) {
        this.body = body;
    }

    /**
     * method getBody returns {@link Body}.
     * @return body 
     */
    protected Body getBody() {
        return body;
    }


    /**
     * characterJump function, it's needed only by the characters, 
     * if somebody else calls it they will receive an {@link UnsupportedOperationException}.
     * it is implemented in the {@link CharacterMovement} class.
     */
    @Override
    public boolean characterJump() {
        throw new UnsupportedOperationException();
    }

    /**
     * characterMoveUp function, it's needed only by the enemy (character), 
     * if somebody else calls it they will receive an {@link UnsupportedOperationException}.
     * it is implemented in the {@link CharacterMovement} class.
     */
    @Override
    public void characterMoveUp() {
        throw new UnsupportedOperationException();
    }

    /**
     * characterMoveLeft function, it's needed only by the characters, 
     * if somebody else calls it they will receive an {@link UnsupportedOperationException}.
     * it is implemented in the {@link CharacterMovement} class.
     */
    @Override
    public void characterMoveLeft() {
        throw new UnsupportedOperationException();
    }

    /**
     * characterMoveRight function, it's needed only by the characters, 
     * if somebody else calls it they will receive an {@link UnsupportedOperationException}.
     * it is implemented in the {@link CharacterMovement} class.
     */
    @Override
    public void characterMoveRight() {
        throw new UnsupportedOperationException();
    }

    /**
     * fall function, it's needed by the characters and the power_ups, 
     * if somebody else calls it they will receive an {@link UnsupportedOperationException}.
     * it is implemented in the {@link FallableMovement} class.
     */
    @Override
    public void fall() {
        throw new UnsupportedOperationException();
    }
    /**
     * fallUndo function, it's needed by the characters and the power_ups, 
     * if somebody else calls it they will receive an {@link UnsupportedOperationException}.
     * it is implemented in the {@link FallableMovement} class.
     */
    @Override
    public void fallUndo(final int objectY) {
        throw new UnsupportedOperationException();
    }

    /**
     * bubbleMoveLeft function, it's needed only by the bubble, 
     * if somebody else calls it they will receive an {@link UnsupportedOperationException}.
     * it is implemented in the {@link BubbleMovement} class.
     */
    @Override
    public void bubbleMoveLeft() {
        throw new UnsupportedOperationException();
    }

    /**
     * bubbleMoveRight function, it's needed only by the bubble, 
     * if somebody else calls it they will receive an {@link UnsupportedOperationException}.
     * it is implemented in the {@link BubbleMovement} class.
     */
    @Override
    public void bubbleMoveRight() {
        throw new UnsupportedOperationException();
    }

    /**
     * bubbleMoveUp function, it's needed only by the bubble, 
     * if somebody else calls it they will receive an {@link UnsupportedOperationException}.
     * it is implemented in the {@link BubbleMovement} class.
     */
    @Override
    public void bubbleMoveUp() {
        throw new UnsupportedOperationException();
    }

    /**
     * floatUpDown function, it's needed by the bubble and the enemy (character), 
     * if somebody else calls it they will receive an {@link UnsupportedOperationException}.
     * it is implemented in the {@link BubbleMovement} and {@link CharacterMovement} classes.
     */
    @Override
    public void floatUpDown() {
        throw new UnsupportedOperationException();
    }

}
