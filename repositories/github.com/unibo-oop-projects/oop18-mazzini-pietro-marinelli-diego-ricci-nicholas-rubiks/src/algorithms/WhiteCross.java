package algorithms;

import java.util.ArrayList;
import java.util.List;

import cubestructure.Cube;
import cubestructure.Cube3X3;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import movestructure.Direction;
import movestructure.MoveUtils;
import research.CubeResearch;

/**
 * At the end it produces a white cross with the color align to the relatives
 * center.
 */
public class WhiteCross {
    private final Cube[][][] cube;
    private final CubeResearch ricerca;
    private final Color color1 = Color.WHITE;
    private Color color2;
    private final List<Color> colors = new ArrayList<>();
    private final List<Pair<Color, Direction>> moves;

    /**
     * Initialize rubik cube and the previous moves.
     * 
     * @param rubik the rubik cube
     * @param moves the list of moves
     */
    public WhiteCross(final Cube3X3 rubik, final List<Pair<Color, Direction>> moves) {
        this.cube = rubik.getRubikCube();
        this.ricerca = new CubeResearch(cube);
        setColors();
        this.moves = moves;
        for (int i = 0; i < 4; i++) {
            color2 = colors.get(i);
            begin();
        }
        isEnded();
    }

    /**
     * Return true at the end of the algorithm.
     * 
     * @return true
     */
    public final boolean isEnded() {
        return true;
    }

    private void setColors() {
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.ORANGE);
    }

    private void begin() {
        Integer[] pos = new Integer[3];
        pos = ricerca.twoFacePosition(color1, color2);
        if (pos[0] == 2) {
            onBottom(pos);
        }
        if (pos[0] == 1) {
            onSide(pos);
        }
        if (pos[0] == 0) {
            onTop(pos);
        }
    }

    private void onTop(final Integer... posTmp) {
        Integer[] pos = posTmp;
        Integer[] tmp = new Integer[3];
        System.arraycopy(pos, 0, tmp, 0, 3);
        tmp[0] = tmp[0] + 1;
        if (!color2.equals(ricerca.getCenterColor(tmp)) || cube[pos[0]][pos[1]][pos[2]].getTop().equals(color2)) {
            MoveUtils.turn(cube, ricerca.getCenterColor(tmp), Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(ricerca.getCenterColor(tmp), Direction.RIGHT));

            MoveUtils.turn(cube, ricerca.getCenterColor(tmp), Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(ricerca.getCenterColor(tmp), Direction.RIGHT));

            pos = ricerca.twoFacePosition(color1, color2);
            onBottom(pos);
        }
    }

    private void onBottom(final Integer... posTmp) {
        Integer[] pos = posTmp;
        // align non-white colors with their center
        if (color2.equals(Color.RED)) {
            if (pos[1] == 0) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
            }
            if (pos[1] == 2) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));
            }
            if (pos[1] == 1 && pos[2] != 0) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));

                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
            }
        }

        if (color2.equals(Color.BLUE)) {
            if (pos[2] == 0) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
            }
            if (pos[2] == 2) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));
            }
            if (pos[2] == 1 && pos[1] != 2) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));

                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
            }
        }

        if (color2.equals(Color.GREEN)) {
            if (pos[2] == 0) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));
            }
            if (pos[2] == 2) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
            }
            if (pos[2] == 1 && pos[1] != 0) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));

                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
            }
        }

        if (color2.equals(Color.ORANGE)) {
            if (pos[1] == 0) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));
            }
            if (pos[1] == 2) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
            }
            if (pos[1] == 1 && pos[2] != 2) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));

                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
            }
        }
        pos = ricerca.twoFacePosition(color1, color2);
        // verify that white face is not on bottom
        if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.WHITE)) {
            MoveUtils.turn(cube, color2, Direction.LEFT);
            moves.add(new Pair<Color, Direction>(color2, Direction.LEFT));

            MoveUtils.turn(cube, color2, Direction.LEFT);
            moves.add(new Pair<Color, Direction>(color2, Direction.LEFT));

        } else {

            MoveUtils.turn(cube, color2, Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(color2, Direction.RIGHT));

            MoveUtils.turn(cube, Color.WHITE, Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(Color.WHITE, Direction.RIGHT));

            if (color2.equals(Color.RED)) {
                MoveUtils.turn(cube, Color.GREEN, Direction.LEFT);
                moves.add(new Pair<Color, Direction>(Color.GREEN, Direction.LEFT));
            }

            if (color2.equals(Color.BLUE)) {
                MoveUtils.turn(cube, Color.RED, Direction.LEFT);
                moves.add(new Pair<Color, Direction>(Color.RED, Direction.LEFT));
            }

            if (color2.equals(Color.GREEN)) {
                MoveUtils.turn(cube, Color.ORANGE, Direction.LEFT);
                moves.add(new Pair<Color, Direction>(Color.ORANGE, Direction.LEFT));
            }

            if (color2.equals(Color.ORANGE)) {
                MoveUtils.turn(cube, Color.BLUE, Direction.LEFT);
                moves.add(new Pair<Color, Direction>(Color.BLUE, Direction.LEFT));
            }
            MoveUtils.turn(cube, Color.WHITE, Direction.LEFT);
            moves.add(new Pair<Color, Direction>(Color.WHITE, Direction.LEFT));
        }
    }

    private void onSide(final Integer... posTmp) {
        Integer[] pos = posTmp;
        Direction tmp = null;
        if (pos[1] == 0 && pos[2] == 0) {
            pos[2] = pos[2] + 1;
            MoveUtils.turn(cube, ricerca.getCenterColor(pos), Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(ricerca.getCenterColor(pos), Direction.RIGHT));
            tmp = Direction.RIGHT;
        }

        if (pos[1] == 2 && pos[2] == 0) {
            pos[2] = pos[2] + 1;
            MoveUtils.turn(cube, ricerca.getCenterColor(pos), Direction.LEFT);
            moves.add(new Pair<Color, Direction>(ricerca.getCenterColor(pos), Direction.LEFT));
            tmp = Direction.LEFT;
        }

        if (pos[1] == 2 && pos[2] == 2) {
            pos[2] = pos[2] - 1;
            MoveUtils.turn(cube, ricerca.getCenterColor(pos), Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(ricerca.getCenterColor(pos), Direction.RIGHT));
            tmp = Direction.RIGHT;

        }

        if (pos[1] == 0 && pos[2] == 2) {
            pos[2] = pos[2] - 1;
            MoveUtils.turn(cube, ricerca.getCenterColor(pos), Direction.LEFT);
            moves.add(new Pair<Color, Direction>(ricerca.getCenterColor(pos), Direction.LEFT));
            tmp = Direction.LEFT;
        }

        if (!ricerca.getCenterColor(pos).equals(color2)) {
            MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));

            if (tmp.equals(Direction.RIGHT)) {
                MoveUtils.turn(cube, ricerca.getCenterColor(pos), Direction.LEFT);
                moves.add(new Pair<Color, Direction>(ricerca.getCenterColor(pos), Direction.LEFT));
            } else {
                MoveUtils.turn(cube, ricerca.getCenterColor(pos), Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(ricerca.getCenterColor(pos), Direction.RIGHT));
            }
        }

        pos = ricerca.twoFacePosition(color1, color2);
        onBottom(pos);
    }
}
