package algorithms;

import java.util.List;

import cubestructure.Cube;
import cubestructure.Cube3X3;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import movestructure.Direction;
import movestructure.MoveUtils;

/** Set the yellow angles in the right place but eventually facing wrong. */
public class YellowAngles {
    private final Cube[][][] cube;
    private final List<Pair<Color, Direction>> moves;
    private final Color colorY = Color.YELLOW;
    private final Color colorR = Color.RED;
    private final Color colorG = Color.GREEN;
    private final Color colorB = Color.BLUE;
    private final Color colorO = Color.ORANGE;
    private int saveLoop;
    private static final int MAX_LOOP = 15;

    /**
     * Initialized rubik cube and get the list of previous moves.
     * 
     * @param rubik the
     * 
     * @param moves the list of moves
     */
    public YellowAngles(final Cube3X3 rubik, final List<Pair<Color, Direction>> moves) {
        this.cube = rubik.getRubikCube();
        this.moves = moves;
        begin();
    }

    private void begin() {
        saveLoop++;
        if (!isEnded()) {
            return;
        }
        Integer[] pos = new Integer[3];
        int rightAngleCont = 0;
        if ((cube[2][0][0].getFront().equals(colorY) || cube[2][0][0].getFront().equals(colorR)
                || cube[2][0][0].getFront().equals(colorG))
                && (cube[2][0][0].getLeft().equals(colorY) || cube[2][0][0].getLeft().equals(colorR)
                        || cube[2][0][0].getLeft().equals(colorG))
                && (cube[2][0][0].getBottom().equals(colorY) || cube[2][0][0].getBottom().equals(colorR)
                        || cube[2][0][0].getBottom().equals(colorG))) {
            rightAngleCont++;
            pos[0] = 2;
            pos[1] = 0;
            pos[2] = 0;
        }

        if ((cube[2][2][0].getFront().equals(colorY) || cube[2][2][0].getFront().equals(colorR)
                || cube[2][2][0].getFront().equals(colorB))
                && (cube[2][2][0].getRight().equals(colorY) || cube[2][2][0].getRight().equals(colorR)
                        || cube[2][2][0].getRight().equals(colorB))
                && (cube[2][2][0].getBottom().equals(colorY) || cube[2][2][0].getBottom().equals(colorR)
                        || cube[2][2][0].getBottom().equals(colorB))

        ) {
            rightAngleCont++;
            pos[0] = 2;
            pos[1] = 2;
            pos[2] = 0;
        }

        if ((cube[2][0][2].getBack().equals(colorY) || cube[2][0][2].getBack().equals(colorO)
                || cube[2][0][2].getBack().equals(colorG))
                && (cube[2][0][2].getLeft().equals(colorY) || cube[2][0][2].getLeft().equals(colorO)
                        || cube[2][0][2].getLeft().equals(colorG))
                && (cube[2][0][2].getBottom().equals(colorY) || cube[2][0][2].getBottom().equals(colorO)
                        || cube[2][0][2].getBottom().equals(colorG))

        ) {
            rightAngleCont++;
            pos[0] = 2;
            pos[1] = 0;
            pos[2] = 2;

        }

        if ((cube[2][2][2].getBack().equals(colorY) || cube[2][2][2].getBack().equals(colorO)
                || cube[2][2][2].getBack().equals(colorB))
                && (cube[2][2][2].getRight().equals(colorY) || cube[2][2][2].getRight().equals(colorO)
                        || cube[2][2][2].getRight().equals(colorB))
                && (cube[2][2][2].getBottom().equals(colorY) || cube[2][2][2].getBottom().equals(colorO)
                        || cube[2][2][2].getBottom().equals(colorB))

        ) {
            rightAngleCont++;
            pos[0] = 2;
            pos[1] = 2;
            pos[2] = 2;

        }
        if (rightAngleCont == 1 || rightAngleCont == 2) {
            defineCenter(pos);
        }
        if (rightAngleCont == 0) {
            pos[0] = 2;
            pos[1] = 0;
            pos[2] = 0;
            defineCenter(pos);
            begin();
        }
        if (rightAngleCont == 4) {
            isEnded();
        }
    }

    private void defineCenter(final Integer... pos) {
        if (pos[1] == 0 && pos[2] == 0) {
            swapAngles(colorG, colorB);
        }
        if (pos[1] == 0 && pos[2] == 2) {
            swapAngles(colorR, colorO);
        }
        if (pos[1] == 2 && pos[2] == 0) {
            swapAngles(colorO, colorR);
        }
        if (pos[1] == 2 && pos[2] == 2) {
            swapAngles(colorB, colorG);
        }
        begin();
    }

    /**
     * Return true at the end.
     * 
     * @return true
     */
    public final boolean isEnded() {
        return (saveLoop != MAX_LOOP);

    }

    private void swapAngles(final Color colorLeft, final Color colorRight) {
        MoveUtils.turn(cube, colorY, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(colorY, Direction.RIGHT));

        MoveUtils.turn(cube, colorLeft, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(colorLeft, Direction.RIGHT));

        MoveUtils.turn(cube, colorY, Direction.LEFT);
        moves.add(new Pair<Color, Direction>(colorY, Direction.LEFT));

        MoveUtils.turn(cube, colorRight, Direction.LEFT);
        moves.add(new Pair<Color, Direction>(colorRight, Direction.LEFT));

        MoveUtils.turn(cube, colorY, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(colorY, Direction.RIGHT));

        MoveUtils.turn(cube, colorLeft, Direction.LEFT);
        moves.add(new Pair<Color, Direction>(colorLeft, Direction.LEFT));

        MoveUtils.turn(cube, colorY, Direction.LEFT);
        moves.add(new Pair<Color, Direction>(colorY, Direction.LEFT));

        MoveUtils.turn(cube, colorRight, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(colorRight, Direction.RIGHT));
    }
}
