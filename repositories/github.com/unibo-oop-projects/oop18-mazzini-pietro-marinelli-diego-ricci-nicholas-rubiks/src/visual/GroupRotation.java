package visual;

import movestructure.Direction;
import movestructure.Face;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.util.Duration;

/**
 * Wrapper of {@link RotateTransition}, it's like a false rotate animation. 
 * "Come se fosse antani"
 * 
 * This class is a dirty hack but that's the only working method i found..
 */
public class GroupRotation {

    private final RotateTransition rotateT;
    private final RotateTransition resetT;

    private static final Duration DURATION = Duration.millis(500);

    /**
     * This constructor creates a fake rotation of given Group around his axis in a given direction.
     * @param rotationGroup - Group of cubes to be rotated.
     * @param direction - Rotate direction.
     * @param face - The face that is composed by the rotationGroup.
     * @throws RuntimeException if the direction passed isn't contemplated.
     */
    public GroupRotation(final Group rotationGroup, final Direction direction, final Face face) {
        rotateT = new RotateTransition(DURATION, rotationGroup);
        rotateT.setAxis(face.getAxis());

        //Doing this cube will rotate back instantly
        resetT = new RotateTransition(Duration.millis(1), rotationGroup);
        resetT.setAxis(face.getAxis());

        switch (direction) {
            case LEFT:
                rotateT.setByAngle(face.getLeft());
                resetT.setByAngle(-1 * face.getLeft());
                break;
            case RIGHT:
                rotateT.setByAngle(face.getRight());
                resetT.setByAngle(-1 * face.getRight());
                break;
            default:
                throw new RuntimeException("Unknown direction: " + direction.toString());
        }
    }

    /**
     * Play the animation.
     */
    public void play() {
        this.rotateT.play();
        rotateT.setOnFinished(e -> {
            resetT.play();
        });
    }

    /**
     * Sets the action to be done when animation is concluded.
     * @param e - Action to be done.
     */
    public void setOnFinished(final EventHandler<ActionEvent> e) {
        resetT.setOnFinished(e);
    }

    /**
     * Is animation concluded?
     * @return True if animation is concluded, else false.
     */
    public boolean isFinished() {
        return rotateT.getStatus().equals(Animation.Status.STOPPED)
                && resetT.getStatus().equals(Animation.Status.STOPPED);
    }
}
