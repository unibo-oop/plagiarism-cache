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

/** Complete the central ring inserting the edges. */
public class CentralLine {
    private final Cube[][][] cube;
    private final CubeResearch ricerca;
    private Color color1;
    private Color color2;
    private final List<Color> colors1 = new ArrayList<>();
    private final List<Color> colors2 = new ArrayList<>();
    private final List<Integer> missed = new ArrayList<>();
    private final boolean isPhaseTwo;
    private final List<Pair<Color, Direction>> moves;

    /**
     * Initialize rubik cube and get the list of previous moves.
     * 
     * @param rubik the rubik cube
     * @param moves the list of the moves
     */
    public CentralLine(final Cube3X3 rubik, final List<Pair<Color, Direction>> moves) {
        this.cube = rubik.getRubikCube();
        this.ricerca = new CubeResearch(cube);
        this.moves = moves;
        setColors();
        for (int i = 0; i < 4; i++) {
            color1 = colors1.get(i);
            color2 = colors2.get(i);
            begin(i);
        }
        isPhaseTwo = true;
        for (int i = 0; i < missed.size(); i++) {
            color1 = colors1.get(missed.get(i));
            color2 = colors2.get(missed.get(i));
            begin(missed.get(i));
        }
        isEnded();
    }

    /**
     * Return true at the end.
     * 
     * @return true
     */
    public final boolean isEnded() {
        return true;

    }

    private void begin(final int i) {
        Integer[] pos = new Integer[3];
        pos = ricerca.twoFacePosition(color1, color2);

        if (pos[0] == 2) {
            phaseOne(pos);
        }
        if (pos[0] == 1) {
            if (!isPhaseTwo) {
                missed.add(i);
            } else {
                phaseTwo(pos);
            }
        }
    }

    private void setColors() {
        colors1.add(Color.RED);
        colors1.add(Color.RED);
        colors1.add(Color.ORANGE);
        colors1.add(Color.ORANGE);
        colors2.add(Color.BLUE);
        colors2.add(Color.GREEN);
        colors2.add(Color.BLUE);
        colors2.add(Color.GREEN);
    }

    private void phaseOne(final Integer... pos) {
        Color tmpBottom;
        Color tmpColor = null;
        // align in the right spot
        tmpBottom = cube[pos[0]][pos[1]][pos[2]].getBottom();
        if (pos[1] == 1 && pos[2] == 0) {
            tmpColor = cube[pos[0]][pos[1]][pos[2]].getFront();
            if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.GREEN)) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
            } else {
                if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.BLUE)) {
                    MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
                    moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));
                } else {
                    if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.RED)) {
                        MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                        moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
                        MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                        moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
                    }
                }
            }
        }

        if (pos[1] == 0 && pos[2] == 1) {
            tmpColor = cube[pos[0]][pos[1]][pos[2]].getLeft();
            if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.ORANGE)) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
            } else {
                if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.RED)) {
                    MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
                    moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));
                } else {
                    if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.GREEN)) {
                        MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                        moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
                        MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                        moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
                    }
                }
            }
        }

        if (pos[1] == 2 && pos[2] == 1) {
            tmpColor = cube[pos[0]][pos[1]][pos[2]].getRight();
            if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.RED)) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
            } else {
                if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.ORANGE)) {
                    MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
                    moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));
                } else {
                    if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.BLUE)) {
                        MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                        moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
                        MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                        moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
                    }
                }
            }
        }

        if (pos[1] == 1 && pos[2] == 2) {
            tmpColor = cube[pos[0]][pos[1]][pos[2]].getBack();
            if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.BLUE)) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
            } else {
                if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.GREEN)) {
                    MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
                    moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));
                } else {
                    if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.ORANGE)) {
                        MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                        moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
                        MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                        moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
                    }
                }
            }
        }
        // call the defined-step algorithm
        if (tmpColor.equals(Color.RED) && tmpBottom.equals(Color.BLUE)) {
            turn(tmpBottom, tmpColor, Direction.LEFT);

        } else {
            if (tmpColor.equals(Color.RED) && tmpBottom.equals(Color.GREEN)) {
                turn(tmpBottom, tmpColor, Direction.RIGHT);
            }
        }

        if (tmpColor.equals(Color.BLUE) && tmpBottom.equals(Color.RED)) {
            turn(tmpBottom, tmpColor, Direction.RIGHT);

        } else {
            if (tmpColor.equals(Color.GREEN) && tmpBottom.equals(Color.RED)) {
                turn(tmpBottom, tmpColor, Direction.LEFT);
            }
        }

        if (tmpColor.equals(Color.ORANGE) && tmpBottom.equals(Color.BLUE)) {
            turn(tmpBottom, tmpColor, Direction.RIGHT);

        } else {
            if (tmpColor.equals(Color.ORANGE) && tmpBottom.equals(Color.GREEN)) {
                turn(tmpBottom, tmpColor, Direction.LEFT);
            }
        }

        if (tmpColor.equals(Color.BLUE) && tmpBottom.equals(Color.ORANGE)) {
            turn(tmpBottom, tmpColor, Direction.LEFT);

        } else {
            if (tmpColor.equals(Color.GREEN) && tmpBottom.equals(Color.ORANGE)) {
                turn(tmpBottom, tmpColor, Direction.RIGHT);
            }
        }

    }

    private void turn(final Color color2, final Color color1, final Direction first) {
        Direction opposite;
        if (first.equals(Direction.RIGHT)) {
            opposite = Direction.LEFT;
        } else {
            opposite = Direction.RIGHT;
        }
        MoveUtils.turn(cube, color2, first);
        moves.add(new Pair<Color, Direction>(color2, first));

        MoveUtils.turn(cube, Color.YELLOW, opposite);
        moves.add(new Pair<Color, Direction>(Color.YELLOW, opposite));

        MoveUtils.turn(cube, color2, opposite);
        moves.add(new Pair<Color, Direction>(color2, opposite));

        MoveUtils.turn(cube, Color.YELLOW, opposite);
        moves.add(new Pair<Color, Direction>(Color.YELLOW, opposite));

        MoveUtils.turn(cube, color1, opposite);
        moves.add(new Pair<Color, Direction>(color1, opposite));

        MoveUtils.turn(cube, Color.YELLOW, first);
        moves.add(new Pair<Color, Direction>(Color.YELLOW, first));

        MoveUtils.turn(cube, color1, first);
        moves.add(new Pair<Color, Direction>(color1, first));
    }

    private void phaseTwo(final Integer... posTmp) {
        Integer[] pos = posTmp;
        if (pos[1] == 0 && pos[2] == 0) {
            turn(Color.GREEN, Color.RED, Direction.RIGHT);
        } else {
            if (pos[1] == 2 && pos[2] == 0) {
                turn(Color.BLUE, Color.RED, Direction.LEFT);
            } else {
                if (pos[1] == 0 && pos[2] == 2) {
                    turn(Color.GREEN, Color.ORANGE, Direction.LEFT);
                } else {
                    if (pos[1] == 2 && pos[2] == 2) {
                        turn(Color.BLUE, Color.ORANGE, Direction.RIGHT);
                    }
                }
            }
        }
        pos = ricerca.twoFacePosition(color1, color2);
        phaseOne(pos);
    }
}
