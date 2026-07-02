package algorithms;

import java.util.List;

import cubestructure.Cube;
import cubestructure.Cube3X3;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import movestructure.Direction;
import movestructure.MoveUtils;

/** Align the yellow cross with the center colors. */
public class CenterAlignment {
    private final Cube[][][] cube;
    private final List<Pair<Color, Direction>> moves;
    private final Color color1 = Color.YELLOW;
    private boolean[] centerRight = new boolean[4];
    private int tmpContTrue;
    private int saveLoop;
    private static final int MAX_LOOP = 15;

    /**
     * Initialized rubik cube and get the list of previous moves.
     * 
     * @param rubik the rubik cube
     * @param moves the list of moves
     */
    public CenterAlignment(final Cube3X3 rubik, final List<Pair<Color, Direction>> moves) {
        this.cube = rubik.getRubikCube();
        this.moves = moves;
        begin();
    }

    private void begin() {
        saveLoop++;
        if (!isEnded()) {
            return;
        }
        tmpContTrue = 0;
        if (cube[2][1][0].getFront().equals(Color.RED)) {
            centerRight[0] = true;
        } else {
            centerRight[0] = false;
        }

        if (cube[2][0][1].getLeft().equals(Color.GREEN)) {
            centerRight[1] = true;
        } else {
            centerRight[1] = false;
        }

        if (cube[2][1][2].getBack().equals(Color.ORANGE)) {
            centerRight[2] = true;
        } else {
            centerRight[2] = false;
        }

        if (cube[2][2][1].getRight().equals(Color.BLUE)) {
            centerRight[3] = true;
        } else {
            centerRight[3] = false;
        }
        for (int i = 0; i < 4; i++) {
            if (centerRight[i]) {
                tmpContTrue++;
            }
        }
        selectCase(tmpContTrue);
    }

    /**
     * return true at the end of the algorithm.
     * 
     * @return true
     */
    public final boolean isEnded() {
        return (saveLoop != MAX_LOOP);

    }

    private void selectCase(final int tmpContTrue) {
        if (tmpContTrue == 0) {
            MoveUtils.turn(cube, color1, Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(color1, Direction.RIGHT));
            begin();
        } else {
            if (tmpContTrue == 2) {
                special();
            }
            if (tmpContTrue < 4 && tmpContTrue != 2) {
                findCenter();
            }
            if (tmpContTrue == 4) {
                isEnded();
            }
        }
    }

    private void special() {
        if (cube[2][1][0].getFront().equals(Color.RED) && cube[2][1][2].getBack().equals(Color.ORANGE)
                && cube[2][0][1].getLeft().equals(Color.GREEN)) {
            swapCenter(Color.GREEN);
            MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
            moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));
        }
        if (cube[2][0][1].getLeft().equals(Color.GREEN) && cube[2][2][1].getRight().equals(Color.BLUE)
                && cube[2][1][0].getFront().equals(Color.ORANGE)) {
            swapCenter(Color.ORANGE);
            MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
            moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));
        }
        findCenter();
    }

    private void findCenter() {
        Color center = null;
        for (int i = 0; i < 4; i++) {
            if (centerRight[i]) {
                switch (i) {
                    case 0:
                        center = Color.GREEN;
                        break;
                    case 1:
                        center = Color.ORANGE;
                        break;
                    case 2:
                        center = Color.BLUE;
                        break;
                    case 3:
                        center = Color.RED;
                        break;
                    default:
                        break;
                }
            }
        }

        swapCenter(center);
    }

    private void swapCenter(final Color center) {
        MoveUtils.turn(cube, center, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(center, Direction.RIGHT));

        MoveUtils.turn(cube, color1, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(color1, Direction.RIGHT));

        MoveUtils.turn(cube, center, Direction.LEFT);
        moves.add(new Pair<Color, Direction>(center, Direction.LEFT));

        MoveUtils.turn(cube, color1, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(color1, Direction.RIGHT));

        MoveUtils.turn(cube, center, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(center, Direction.RIGHT));

        MoveUtils.turn(cube, color1, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(color1, Direction.RIGHT));

        MoveUtils.turn(cube, color1, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(color1, Direction.RIGHT));

        MoveUtils.turn(cube, center, Direction.LEFT);
        moves.add(new Pair<Color, Direction>(center, Direction.LEFT));

        if (tmpContTrue == 2) {
            MoveUtils.turn(cube, color1, Direction.LEFT);
            moves.add(new Pair<Color, Direction>(color1, Direction.LEFT));
        }
        begin();
    }

}
