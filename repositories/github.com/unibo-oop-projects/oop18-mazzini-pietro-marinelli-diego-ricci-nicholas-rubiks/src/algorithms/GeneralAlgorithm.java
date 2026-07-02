package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cubestructure.Cube;
import cubestructure.Cube3X3;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import movestructure.Direction;

/**
 * Receive the rubik cube, make a copy and call all the necessary classes in
 * order to resolve it.
 */
public class GeneralAlgorithm {
    private WhiteCross whiteCrossMaker;
    private WhiteFace whiteFaceMaker;
    private CentralLine centralLineMaker;
    private YellowCross yellowCrossMaker;
    private CenterAlignment centerAlignmentMaker;
    private YellowAngles yellowAnglesMaker;
    private final Cube3X3 rubik;
    private final List<Pair<Color, Direction>> moves = new ArrayList<>();
    private int movesCounter = -1;
    private boolean cubeValidity = true;

    /**
     * Initialize a copy of the rubik cube and calls the other algorithm.
     * 
     * @param rubik the rubik cube
     */
    public GeneralAlgorithm(final Cube3X3 rubik) {
        Cube[][][] cube;
        this.rubik = rubik;
        cube = rubik.getRubikCube();
        if (!cubeVerify(cube)) {
            whiteCross();
            if (whiteCrossMaker.isEnded()) {
                whiteFace();
                whiteAnglesVerify(cube);
            }
            if (whiteFaceMaker.isEnded()) {
                centralLine();
            }
            if (centralLineMaker.isEnded()) {
                yellowCross();
            } else {
                cubeValidity = false;
                return;
            }
            if (yellowCrossMaker.isEnded() && cubeValidity) {
                centerAlignmet();
            } else {
                cubeValidity = false;
                return;
            }
            if (centerAlignmentMaker.isEnded() && cubeValidity) {
                yellowAngles();
                yellowAnglesVerify(cube);
            } else {
                cubeValidity = false;
                return;
            }
            if (yellowAnglesMaker.isEnded() && cubeValidity) {
                complete();
            } else {
                cubeValidity = false;
                return;
            }
        }
    }

    private void whiteAnglesVerify(final Cube[][]... cube) {
        if (!cube[0][0][0].getTop().equals(Color.WHITE) || !cube[0][2][0].getTop().equals(Color.WHITE)
                || !cube[0][0][2].getTop().equals(Color.WHITE) || !cube[0][2][2].getTop().equals(Color.WHITE)) {
            cubeValidity = false;
            return;
        }

    }

    private void yellowAnglesVerify(final Cube[][]... cube) {
        if (!cube[0][0][0].getTop().equals(Color.WHITE) || !cube[0][2][0].getTop().equals(Color.WHITE)
                || !cube[0][0][2].getTop().equals(Color.WHITE) || !cube[0][2][2].getTop().equals(Color.WHITE)) {
            cubeValidity = false;
            return;
        }

    }

    /**
     * Verify if the rubik cube is solved.
     * 
     * @return boolean
     * 
     * @param cube the rubik cube
     */
    public static boolean cubeVerify(final Cube[][]... cube) {
        boolean white = true, red = true, blue = true, green = true, orange = true, yellow = true;
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (!cube[0][i][k].getTop().equals(Color.WHITE)) {
                    white = false;
                }
                if (!cube[2][i][k].getBottom().equals(Color.YELLOW)) {
                    yellow = false;
                }
                if (!cube[i][k][0].getFront().equals(Color.RED)) {
                    red = false;
                }
                if (!cube[i][k][2].getBack().equals(Color.ORANGE)) {
                    orange = false;
                }
                if (!cube[i][0][k].getLeft().equals(Color.GREEN)) {
                    green = false;
                }
                if (!cube[i][2][k].getRight().equals(Color.BLUE)) {
                    blue = false;
                }
            }
        }
        return (white && red && blue && green && orange && yellow);
    }

    private void complete() {
        new Complete(rubik, moves);

    }

    private void yellowAngles() {
        yellowAnglesMaker = new YellowAngles(rubik, moves);

    }

    private void centerAlignmet() {
        centerAlignmentMaker = new CenterAlignment(rubik, moves);

    }

    private void yellowCross() {
        yellowCrossMaker = new YellowCross(rubik, moves);
    }

    private void centralLine() {
        centralLineMaker = new CentralLine(rubik, moves);

    }

    private void whiteFace() {
        whiteFaceMaker = new WhiteFace(rubik, moves);

    }

    private void whiteCross() {
        whiteCrossMaker = new WhiteCross(rubik, moves);
    }

    /**
     * Return the next move in order to solve the rubik cube.
     * 
     * @return {@link Optional}
     */
    public Optional<Pair<Color, Direction>> nextMove() {
        final int maxMoves = moves.size();
        if (movesCounter < maxMoves - 1) {
            movesCounter++;
            return Optional.of(moves.get(movesCounter));
        }
        return Optional.empty();
    }

    /**
     * Return the previous move in order to see it again.
     * 
     * @return {@link Optional}
     */
    public Optional<Pair<Color, Direction>> previousMove() {
        if (movesCounter >= 0) {
            movesCounter--;
            if (moves.get(movesCounter + 1).getValue().equals(Direction.RIGHT)) {
                return Optional.of(new Pair<Color, Direction>(moves.get(movesCounter + 1).getKey(), Direction.LEFT));
            } else {
                return Optional.of(new Pair<Color, Direction>(moves.get(movesCounter + 1).getKey(), Direction.RIGHT));
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * return false if the algorithm can't solve the cube.
     * 
     * @return boolean
     */
    public boolean isCubeValid() {
        return cubeValidity;
    }

}
