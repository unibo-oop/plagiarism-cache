package visual;

import cubecontroller.CubeController;
import cubecontroller.GameMode;
import javafx.scene.Scene;

/**
 * Class used to attach to a cube scene Keyboard Controls based on GameMode.
 */
public class KeyBoardControl {

    private final GameMode mode;
    private final Scene scene;
    private final CubeController cube;

    /**
     * Constructor that defines local variables.
     * @param mode - GameMode, this will define which controls to attach.
     * @param scene - Scene that will have these controls.
     * @param cube - The cube that contains methods to be called by KeyPresses.
     */
    public KeyBoardControl(final GameMode mode, final Scene scene, final CubeController cube) {
        this.mode = mode;
        this.scene = scene;
        this.cube = cube;
    }

    /**
     * Initialize Keyboard Control with the previously selected (with constructor) parameters.
     * @throws RuntimeException if the mode passed to the constructor isn't contemplated.
     */
    public void initKeyBoardControl() {
        switch (mode) {
            case RANDOM:
                random();
                break;
            case SOLVE:
                solve();
                break;
            default:
                throw new RuntimeException("Unknown mode: " + mode.toString());
        }
    }

    // Empty, but here in case is necessary
    private void random() {
    }

    // Enter: check if inserted cube is correct.
    // Left arrow: next move
    // Right arrow: previous move
    private void solve() {
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case ENTER:
                    cube.checkInsertedCube();
                    break;
                case LEFT:
                    cube.previousMove();
                    break;
                case RIGHT:
                    cube.nextMove();
                    break;
                default:
                    break;
            }
        });
    }
}
