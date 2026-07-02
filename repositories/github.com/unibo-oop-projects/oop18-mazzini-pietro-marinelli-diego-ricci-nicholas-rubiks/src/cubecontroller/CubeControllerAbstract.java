package cubecontroller;

import java.util.List;
import java.util.Optional;

import algorithms.GeneralAlgorithm;
import cubestructure.Cube;
import cubestructure.CubeCheck;
import cubestructure.LogicStructure;
import cubevisual.RubikCube;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;
import movestructure.Direction;
import movestructure.Face;
import movestructure.MoveUtils;
import movestructure.SideUtils;
import visual.GroupRotation;
import visual.SourceContextMenu;

/**
 * Implementing this abstract class to make software extension and further implementation possible.
 * This class should work well for all the odds Rubik Cubes except for static external classes that i don't know
 * how are implemented..
 */
public abstract class CubeControllerAbstract implements CubeController {

    // Proportion
    private static final double SIZE = Screen.getPrimary().getVisualBounds().getHeight()
            * Screen.getPrimary().getVisualBounds().getWidth() * 300 / 2073600;

    private RubikCube visualCube;
    private LogicStructure logicCube;
    private Optional<GroupRotation> visualRotate;

    private Optional<CubeCheck> check;
    private Optional<GeneralAlgorithm> solver;
    private final GameMode mode;

    /**
     * This constructor creates a CubeController by a fixed GameMode.
     * @param mode - A {@link GameMode}.
     */
    public CubeControllerAbstract(final GameMode mode) {
        this.solver = Optional.empty();
        this.check = Optional.empty();
        this.visualRotate = Optional.empty();
        this.mode = mode;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void rotateCubeFromVisual(final Node node, final Direction d) {
        rotate(getVisualCube().getCentersColorMap().get(node).getColor(), d);
    }

    private void rotate(final Color color, final Direction d) {
        if (!visualRotate.isPresent() || visualRotate.get().isFinished()) {
            MoveUtils.turn(getLogicCube().getRubikCube(), color, d);
            final Group rotationGroup = getVisualCube().getRotationGroup(SideUtils.getSideID());
            visualRotate = Optional.of(new GroupRotation(rotationGroup, d, Face.getFaceByColor(color)));
            visualRotate.get().setOnFinished(e -> {
                final List<Cube> cubeList = SideUtils.getSide();
                cubeList.forEach(r -> {
                    final cubevisual.Cube cube = getVisualCube().getCubeMap().get(r.getID());
                    cube.setBackColor(r.getBack());
                    cube.setBottomColor(r.getBottom());
                    cube.setFrontColor(r.getFront());
                    cube.setLeftColor(r.getLeft());
                    cube.setRightColor(r.getRight());
                    cube.setTopColor(r.getTop());
                });
                checkCompletedCube();
            });
            visualRotate.get().play();
            getVisualCube().getCubeGroup().getChildren().addAll(rotationGroup);
        }
    }

    /**
     * Method used to attach to the cube centers ContextMenus for face turning.
     */
    protected void setRotatable() {
        getVisualCube().getCentersMap().forEach((k, v) -> {
            final SourceContextMenu contextMenu = new SourceContextMenu(v);
            final MenuItem left = new MenuItem("Rotate Left");
            final MenuItem right = new MenuItem("Rotate Right");
            contextMenu.getItems().addAll(left, right);
            left.setOnAction(event -> rotateCubeFromVisual(contextMenu.getSourceNode(), Direction.LEFT));
            right.setOnAction(event -> rotateCubeFromVisual(contextMenu.getSourceNode(), Direction.RIGHT));
            v.setOnContextMenuRequested(e -> contextMenu.show(v, e.getScreenX(), e.getScreenY()));
        });
    }

    /**
     * Method used to attach to the cube ContextMenus for face color setting.
     */
    protected void setEditable() {
        SideUtils.reset();
        for (final Face face : Face.values()) {
            SideUtils.extractSide(getLogicCube().getRubikCube(), face.getColor());
            final List<Cube> side = SideUtils.getSide();
            // side.size -1 because the last element of size is the central face.
            for (int i = 0; i < side.size() - 1; i++) {
                final Cube cube = side.get(i);
                final SourceContextMenu contextMenu = new SourceContextMenu(
                        getVisualCube().getCubeMap().get(cube.getID()).getFace(face));
                final MenuItem red = new MenuItem("Set Red");
                final MenuItem green = new MenuItem("Set Green");
                final MenuItem blue = new MenuItem("Set Blue");
                final MenuItem white = new MenuItem("Set White");
                final MenuItem yellow = new MenuItem("Set Yellow");
                final MenuItem orange = new MenuItem("Set Orange");
                contextMenu.getItems().addAll(red, green, blue, white, yellow, orange);
                red.setOnAction(event -> setFace(Color.RED, face, cube));
                green.setOnAction(event -> setFace(Color.GREEN, face, cube));
                blue.setOnAction(event -> setFace(Color.BLUE, face, cube));
                white.setOnAction(event -> setFace(Color.WHITE, face, cube));
                yellow.setOnAction(event -> setFace(Color.YELLOW, face, cube));
                orange.setOnAction(event -> setFace(Color.ORANGE, face, cube));
                getVisualCube().getCubeMap().get(cube.getID()).getFace(face).setOnContextMenuRequested(event -> {
                    contextMenu.show(getVisualCube().getCubeMap().get(cube.getID()).getFace(face), event.getScreenX(),
                            event.getScreenY());
                });
            }
        }
    }

    /**
     * Method used to detach from the cube ContextMenus for face color setting.
     */
    protected void unsetEditable() {
        SideUtils.reset();
        for (final Face face : Face.values()) {
            SideUtils.extractSide(getLogicCube().getRubikCube(), face.getColor());
            final List<Cube> side = SideUtils.getSide();
            // side.size -1 because the last element of size is the central face.
            for (int i = 0; i < side.size() - 1; i++) {
                final Cube cube = side.get(i);
                getVisualCube().getCubeMap().get(cube.getID()).getFace(face).setOnContextMenuRequested(null);
            }
        }
    }

    private void setFace(final Color color, final Face face, final Cube cube) {
        getVisualCube().getCubeMap().get(cube.getID()).getFace(face).setFill(color);
        cube.setFromVisual(face, color);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public RubikCube getVisualCube() {
        return visualCube;
    }

    private void checkCompletedCube() {
        if (GeneralAlgorithm.cubeVerify(getLogicCube().getRubikCube())) {
            final Alert alert = new Alert(AlertType.INFORMATION);
            switch (mode) {
                case RANDOM:
                    alert.setTitle("You win!");
                    alert.setHeaderText("You solved Rubik Cube!");
                    alert.setContentText("Congratulations from Piccio, Dede and Myansik :)");
                    alert.show();
                    alert.setOnCloseRequest(e -> {
                        final Stage stage = (Stage) getVisualCube().getCubeGroup().getScene().getWindow();
                        stage.close();
                    });
                    break;
                case SOLVE:
                    alert.setTitle("Cube solved");
                    alert.setHeaderText("And this is how a rubik cube is solved :)");
                    alert.setContentText("To go back to main menu just close the window.");
                    alert.show();
                    break;
                default:
                    throw new RuntimeException("Unknown game mode: " + mode.toString());
            }
        }
    }

    /**
     * {@inheritDoc}.
     * In this case Alert Windows are spawned and if the cube is correct the flow continues, else the cube resets.
     */
    @Override
    public void checkInsertedCube() {
        if (!check.isPresent()) {
            check = Optional.of(new CubeCheck());
        }
        if (!check.get().isItDone()) {
            if (!check.get().checkUserWork(getLogicCube().getCube3X3())) {
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Wrong colors");
                alert.setHeaderText("The color combination inserted isn't correct.");
                alert.setContentText("Please, insert a correct combination.");
                alert.show();
                alert.setOnCloseRequest(e -> updateVisualCube());
            } else {
                final Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Autosolver");
                alert.setHeaderText("The solve phase is now starting.");
                alert.setContentText("Use RIGHT ARROW to advance and LEFT ARROW to go back.");
                alert.show();
                unsetEditable();
                solver = Optional.of(check.get().getAlgorithm());
            }
        }
    }

    private void updateVisualCube() {
        for (int depth = 0; depth < 3; depth++) {
            for (int rows = 0; rows < 3; rows++) {
                for (int cols = 0; cols < 3; cols++) {
                    final cubevisual.Cube cube = getVisualCube().getCubeMap().get(getLogicCube().getRubikCube()[rows][cols][depth].getID());
                    cube.setBackColor(getLogicCube().getRubikCube()[rows][cols][depth].getBack());
                    cube.setBottomColor(getLogicCube().getRubikCube()[rows][cols][depth].getBottom());
                    cube.setFrontColor(getLogicCube().getRubikCube()[rows][cols][depth].getFront());
                    cube.setLeftColor(getLogicCube().getRubikCube()[rows][cols][depth].getLeft());
                    cube.setRightColor(getLogicCube().getRubikCube()[rows][cols][depth].getRight());
                    cube.setTopColor(getLogicCube().getRubikCube()[rows][cols][depth].getTop());
                }
            }
        }
    }

    private boolean canMove() {
        return check.isPresent() && check.get().isItDone()
                && (!visualRotate.isPresent() || visualRotate.get().isFinished())
                && solver.isPresent();
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void nextMove() {
        if (canMove()) {
            final Optional<Pair<Color, Direction>> move = solver.get().nextMove();
            if (move.isPresent()) {
                rotate(move.get().getKey(), move.get().getValue());
            }
        }
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void previousMove() {
        if (canMove()) {
            final Optional<Pair<Color, Direction>> move = solver.get().previousMove();
            if (move.isPresent()) {
                rotate(move.get().getKey(), move.get().getValue());
            }
        }
    }

    /**
     * LogicStructure getter.
     * @return A {@link LogicStructure}, the logic cube.
     */
    protected LogicStructure getLogicCube() {
        return logicCube;
    }

    /**
     * LogicStructure setter.
     * @param logicCube - {@link LogicStructure} to be set.
     */
    protected void setLogicCube(final LogicStructure logicCube) {
        this.logicCube = logicCube;
    }

    /**
     * VisualCube setter.
     * @param visualCube - {@link RubikCube} to be set.
     */
    protected void setVisualCube(final RubikCube visualCube) {
        this.visualCube = visualCube;
    }

    /**
     * Size getter.
     * @return SIZE - Cube size.
     */
    protected static double getSize() {
        return SIZE;
    }
}
