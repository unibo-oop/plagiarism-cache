package algorithms;

import java.util.List;

import cubestructure.Cube;
import cubestructure.Cube3X3;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import movestructure.Direction;
import movestructure.MoveUtils;

/** The final algorithm to complete the cube. */
public class Complete {
    private final Cube[][][] cube;
    private final List<Pair<Color, Direction>> moves;
    private final Color colorY = Color.YELLOW;
    private final Color colorW = Color.WHITE;
    private final Color colorG = Color.GREEN;
    private final Color colorO = Color.ORANGE;
    private final Color colorB = Color.BLUE;
    private int saveLoop;
    private static final int MAX_LOOP = 50;

    /**
     * Initialize the cube from the previous algorithm.
     * 
     * @param rubik the rubik cube
     * 
     * @param moves the list of the previous moves
     */
    public Complete(final Cube3X3 rubik, final List<Pair<Color, Direction>> moves) {
        this.cube = rubik.getRubikCube();
        this.moves = moves;
        begin();
    }

    private void begin() {
        saveLoop++;
        if (!isEnded()) {
            return;
        }
        if (cube[2][0][0].getBottom().equals(colorY) && cube[2][2][0].getBottom().equals(colorY)
                && cube[2][0][2].getBottom().equals(colorY) && cube[2][2][2].getBottom().equals(colorY)) {
            alignment();
        } else {
            if (cube[2][0][0].getBottom().equals(colorY)) {
                MoveUtils.turn(cube, colorY, Direction.LEFT);
                moves.add(new Pair<Color, Direction>(colorY, Direction.LEFT));
                begin();
            } else {
                resolver();
            }
        }
    }

    private void alignment() {
        if (cube[0][1][0].getFront().equals(colorG)) {
            MoveUtils.turn(cube, colorW, Direction.LEFT);
            moves.add(new Pair<Color, Direction>(colorW, Direction.LEFT));
        }
        if (cube[0][1][0].getFront().equals(colorB)) {
            MoveUtils.turn(cube, colorW, Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(colorW, Direction.RIGHT));
        }
        if (cube[0][1][0].getFront().equals(colorO)) {
            MoveUtils.turn(cube, colorW, Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(colorW, Direction.RIGHT));
            MoveUtils.turn(cube, colorW, Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(colorW, Direction.RIGHT));
        }
        if (cube[2][1][0].getFront().equals(colorG)) {
            MoveUtils.turn(cube, colorY, Direction.LEFT);
            moves.add(new Pair<Color, Direction>(colorY, Direction.LEFT));
        }
        if (cube[2][1][0].getFront().equals(colorB)) {
            MoveUtils.turn(cube, colorY, Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(colorY, Direction.RIGHT));
        }
        if (cube[2][1][0].getFront().equals(colorO)) {
            MoveUtils.turn(cube, colorY, Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(colorY, Direction.RIGHT));
            MoveUtils.turn(cube, colorY, Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(colorY, Direction.RIGHT));
        }
    }

    /**
     * return true at the end.
     * 
     * @return true
     */
    public final boolean isEnded() {
        return (saveLoop != MAX_LOOP);
    }

    private void resolver() {
        MoveUtils.turn(cube, colorG, Direction.LEFT);
        moves.add(new Pair<Color, Direction>(colorG, Direction.LEFT));
        MoveUtils.turn(cube, colorW, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(colorW, Direction.RIGHT));
        MoveUtils.turn(cube, colorG, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(colorG, Direction.RIGHT));
        MoveUtils.turn(cube, colorW, Direction.LEFT);
        moves.add(new Pair<Color, Direction>(colorW, Direction.LEFT));
        begin();
    }

}
