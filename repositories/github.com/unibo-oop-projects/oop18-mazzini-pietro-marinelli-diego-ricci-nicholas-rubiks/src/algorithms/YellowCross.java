package algorithms;

import java.util.List;

import cubestructure.Cube;
import cubestructure.Cube3X3;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import movestructure.Direction;
import movestructure.MoveUtils;

/**
 * It makes a yellow cross without considering the center alignment of the
 * cross.
 */
public class YellowCross {
    private final Cube[][][] cube;
    private boolean stop;
    private final List<Pair<Color, Direction>> moves;
    private int saveLoop;
    private static final int MAX_LOOP = 15;

    /**
     * initialized rubik and get the list of the previous moves.
     * 
     * @param rubik the rubik cube
     * @param moves the list of moves
     */
    public YellowCross(final Cube3X3 rubik, final List<Pair<Color, Direction>> moves) {
        this.cube = rubik.getRubikCube();
        this.moves = moves;
        while (!stop && saveLoop < MAX_LOOP) {
            begin();
        }
    }

    /**
     * return true at the end.
     * 
     * @return stop
     */
    public final boolean isEnded() {
        return stop;
    }

    private void begin() {
        saveLoop++;
        boolean contRed = false;
        boolean contBlue = false;
        boolean contGreen = false;
        boolean contOrange = false;
        if (cube[2][1][0].getBottom().equals(Color.YELLOW)) {
            contRed = true;
        }

        if (cube[2][0][1].getBottom().equals(Color.YELLOW)) {
            contGreen = true;
        }

        if (cube[2][2][1].getBottom().equals(Color.YELLOW)) {
            contBlue = true;
        }

        if (cube[2][1][2].getBottom().equals(Color.YELLOW)) {
            contOrange = true;
        }

        verifyCross();
        if (!stop && contRed && contGreen) {
            makeCross(Color.ORANGE, Color.BLUE, Color.YELLOW);
            verifyCross();
            begin();

        }

        if (!stop && contGreen && contOrange) {
            makeCross(Color.BLUE, Color.RED, Color.YELLOW);
            verifyCross();
            begin();

        }

        if (!stop && contBlue && contRed) {
            makeCross(Color.GREEN, Color.ORANGE, Color.YELLOW);
            verifyCross();
            begin();

        }

        if (!stop && contOrange && contBlue) {
            makeCross(Color.RED, Color.GREEN, Color.YELLOW);
            verifyCross();
            begin();

        }

        if (!stop && contBlue && contGreen) {
            makeCross(Color.RED, Color.GREEN, Color.YELLOW);
            verifyCross();
            begin();

        }

        if (!stop && contRed && contOrange) {
            makeCross(Color.GREEN, Color.ORANGE, Color.YELLOW);
            verifyCross();
            begin();

        }
        if (!stop) {
            makeCross(Color.GREEN, Color.ORANGE, Color.YELLOW);
            verifyCross();

        }
    }

    private void verifyCross() {
        if (cube[2][1][0].getBottom().equals(Color.YELLOW) && cube[2][0][1].getBottom().equals(Color.YELLOW)
                && cube[2][2][1].getBottom().equals(Color.YELLOW) && cube[2][1][2].getBottom().equals(Color.YELLOW)) {
            stop = true;
        }
    }

    private void makeCross(final Color color1, final Color color2, final Color color3) {
        MoveUtils.turn(cube, color1, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(color1, Direction.RIGHT));
        MoveUtils.turn(cube, color2, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(color2, Direction.RIGHT));
        MoveUtils.turn(cube, color3, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(color3, Direction.RIGHT));
        MoveUtils.turn(cube, color2, Direction.LEFT);
        moves.add(new Pair<Color, Direction>(color2, Direction.LEFT));
        MoveUtils.turn(cube, color3, Direction.LEFT);
        moves.add(new Pair<Color, Direction>(color3, Direction.LEFT));
        MoveUtils.turn(cube, color1, Direction.LEFT);
        moves.add(new Pair<Color, Direction>(color1, Direction.LEFT));

    }
}
