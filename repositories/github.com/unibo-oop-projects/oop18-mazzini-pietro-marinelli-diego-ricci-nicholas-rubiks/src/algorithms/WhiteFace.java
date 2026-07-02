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

/** Complete all the white face and the relative "crown" of colors around it. */
public class WhiteFace {
    private final Cube[][][] cube;
    private final CubeResearch ricerca;
    private final Color color1 = Color.WHITE;
    private Color color2;
    private Color color3;
    private final List<Color> colorRed = new ArrayList<>();
    private final List<Color> colorOrange = new ArrayList<>();
    private final List<Color> colors = new ArrayList<>();
    private final List<Pair<Color, Direction>> moves;

    /**
     * Initialize the rubik cube and get the previous list of moves.
     * 
     * @param rubik the rubik cube
     * @param moves the list of moves
     */
    public WhiteFace(final Cube3X3 rubik, final List<Pair<Color, Direction>> moves) {
        this.cube = rubik.getRubikCube();
        this.ricerca = new CubeResearch(cube);
        this.moves = moves;
        setColors();
        for (int i = 0; i < 2; i++) {
            color2 = colorRed.get(i);
            color3 = colors.get(i);
            begin();
        }
        for (int i = 0; i < 2; i++) {
            color2 = colorOrange.get(i);
            color3 = colors.get(i);
            begin();
        }
        isEnded();
    }

    /**
     * Return true at the end of the class.
     * 
     * @return true
     */
    public final boolean isEnded() {
        return true;
    }

    private void setColors() {
        colorRed.add(Color.RED);
        colorRed.add(Color.RED);
        colorOrange.add(Color.ORANGE);
        colorOrange.add(Color.ORANGE);
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
    }

    private void begin() {
        Integer[] pos = new Integer[3];
        pos = ricerca.threeFacePosition(color1, color2, color3);

        if (pos[0] == 0) {
            onTop(pos);
        }
        if (pos[0] == 2) {
            onBottom(pos);
        }

    }

    private void onTop(final Integer... posTmp) {
        Integer[] pos = posTmp;
        Color color = null;
        if (pos[1] == 0 && pos[2] == 0) {
            color = Color.GREEN;
        }
        if (pos[1] == 2 && pos[2] == 0) {
            color = Color.RED;
        }
        if (pos[1] == 0 && pos[2] == 2) {
            color = Color.ORANGE;
        }
        if (pos[1] == 2 && pos[2] == 2) {
            color = Color.BLUE;
        }
        MoveUtils.turn(cube, color, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(color, Direction.RIGHT));

        MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
        moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));

        MoveUtils.turn(cube, color, Direction.LEFT);
        moves.add(new Pair<Color, Direction>(color, Direction.LEFT));

        MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
        moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));

        pos = ricerca.threeFacePosition(color1, color2, color3);
        onBottom(pos);
    }

    private void onBottom(final Integer... posTmp) {
        // avoid white face facing bottom
        Integer[] pos = posTmp;
        Color tmp = null;
        if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(color1)) {
            while (cube[0][pos[1]][pos[2]].getTop().equals(Color.WHITE)) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
                pos = ricerca.threeFacePosition(color1, color2, color3);
            }

            if (pos[1] == 0 && pos[2] == 0) {
                tmp = Color.RED;
            }

            if (pos[1] == 2 && pos[2] == 0) {
                tmp = Color.BLUE;
            }

            if (pos[1] == 2 && pos[2] == 2) {
                tmp = Color.ORANGE;
            }

            if (pos[1] == 0 && pos[2] == 2) {
                tmp = Color.GREEN;
            }
            MoveUtils.turn(cube, tmp, Direction.LEFT);
            moves.add(new Pair<Color, Direction>(tmp, Direction.LEFT));

            MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
            moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));

            MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
            moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));

            MoveUtils.turn(cube, tmp, Direction.RIGHT);
            moves.add(new Pair<Color, Direction>(tmp, Direction.RIGHT));

        }
        // align the bottom facing color with its center
        pos = ricerca.threeFacePosition(color1, color2, color3);
        if (pos[1] == 0 && pos[2] == 0) {
            if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.ORANGE)) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));
                pos = ricerca.threeFacePosition(color1, color2, color3);
            }
            if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.BLUE)) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
            }
        }

        if (pos[1] == 2 && pos[2] == 0) {
            if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.ORANGE)) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
                pos = ricerca.threeFacePosition(color1, color2, color3);
            }
            if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.GREEN)) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));
            }
        }

        if (pos[1] == 0 && pos[2] == 2) {
            if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.BLUE)) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));
                pos = ricerca.threeFacePosition(color1, color2, color3);
            }
            if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.RED)) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
            }
        }

        if (pos[1] == 2 && pos[2] == 2) {
            if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.GREEN)) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.RIGHT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.RIGHT));
                pos = ricerca.threeFacePosition(color1, color2, color3);
            }
            if (cube[pos[0]][pos[1]][pos[2]].getBottom().equals(Color.RED)) {
                MoveUtils.turn(cube, Color.YELLOW, Direction.LEFT);
                moves.add(new Pair<Color, Direction>(Color.YELLOW, Direction.LEFT));
            }
        }
        pos = ricerca.threeFacePosition(color1, color2, color3);
        allCase(pos);

    }

    private void allCase(final Integer... pos) {
        if (pos[1] == 0 && pos[2] == 0) {
            if (cube[pos[0]][pos[1]][pos[2]].getLeft().equals(Color.GREEN)) {
                angleTurn4(Color.GREEN, Color.RED, Direction.LEFT);

            } else {

                if (cube[pos[0]][pos[1]][pos[2]].getFront().equals(Color.BLUE)) {
                    angleTurn3(Color.BLUE, Color.YELLOW, Direction.LEFT);

                } else {

                    if (cube[pos[0]][pos[1]][pos[2]].getLeft().equals(Color.ORANGE)) {
                        angleTurn3(Color.ORANGE, Color.YELLOW, Direction.RIGHT);

                    } else {

                        if (cube[pos[0]][pos[1]][pos[2]].getFront().equals(Color.RED)) {
                            angleTurn4(Color.RED, Color.GREEN, Direction.RIGHT);

                        }
                    }
                }
            }
        }

        if (pos[1] == 2 && pos[2] == 0) {
            if (cube[pos[0]][pos[1]][pos[2]].getRight().equals(Color.BLUE)) {
                angleTurn4(Color.BLUE, Color.RED, Direction.RIGHT);

            } else {

                if (cube[pos[0]][pos[1]][pos[2]].getFront().equals(Color.GREEN)) {
                    angleTurn3(Color.GREEN, Color.YELLOW, Direction.RIGHT);

                } else {

                    if (cube[pos[0]][pos[1]][pos[2]].getRight().equals(Color.ORANGE)) {
                        angleTurn3(Color.ORANGE, Color.YELLOW, Direction.LEFT);

                    } else {

                        if (cube[pos[0]][pos[1]][pos[2]].getFront().equals(Color.RED)) {
                            angleTurn4(Color.RED, Color.BLUE, Direction.LEFT);

                        }
                    }
                }
            }
        }

        if (pos[1] == 0 && pos[2] == 2) {
            if (cube[pos[0]][pos[1]][pos[2]].getLeft().equals(Color.RED)) {
                angleTurn3(Color.RED, Color.YELLOW, Direction.LEFT);

            } else {

                if (cube[pos[0]][pos[1]][pos[2]].getBack().equals(Color.ORANGE)) {
                    angleTurn4(Color.ORANGE, Color.GREEN, Direction.LEFT);

                } else {

                    if (cube[pos[0]][pos[1]][pos[2]].getLeft().equals(Color.GREEN)) {
                        angleTurn4(Color.GREEN, Color.ORANGE, Direction.RIGHT);

                    } else {

                        if (cube[pos[0]][pos[1]][pos[2]].getBack().equals(Color.BLUE)) {
                            angleTurn3(Color.BLUE, Color.YELLOW, Direction.RIGHT);

                        }
                    }
                }
            }
        }

        if (pos[1] == 2 && pos[2] == 2) {
            if (cube[pos[0]][pos[1]][pos[2]].getRight().equals(Color.RED)) {
                angleTurn3(Color.RED, Color.YELLOW, Direction.RIGHT);

            } else {

                if (cube[pos[0]][pos[1]][pos[2]].getBack().equals(Color.ORANGE)) {
                    angleTurn4(Color.ORANGE, Color.BLUE, Direction.RIGHT);

                } else {

                    if (cube[pos[0]][pos[1]][pos[2]].getRight().equals(Color.BLUE)) {
                        angleTurn4(Color.BLUE, Color.ORANGE, Direction.LEFT);

                    } else {

                        if (cube[pos[0]][pos[1]][pos[2]].getBack().equals(Color.GREEN)) {
                            angleTurn3(Color.GREEN, Color.YELLOW, Direction.LEFT);

                        }
                    }
                }
            }
        }
    }

    private void angleTurn4(final Color color1, final Color color2, final Direction first) {
        Direction opposite;
        if (first.equals(Direction.RIGHT)) {
            opposite = Direction.LEFT;
        } else {
            opposite = Direction.RIGHT;
        }
        MoveUtils.turn(cube, color1, first);
        moves.add(new Pair<Color, Direction>(color1, first));
        MoveUtils.turn(cube, color2, opposite);
        moves.add(new Pair<Color, Direction>(color2, opposite));
        MoveUtils.turn(cube, color1, opposite);
        moves.add(new Pair<Color, Direction>(color1, opposite));
        MoveUtils.turn(cube, color2, first);
        moves.add(new Pair<Color, Direction>(color2, first));
    }

    private void angleTurn3(final Color color1, final Color color2, final Direction first) {
        Direction opposite;
        if (first.equals(Direction.RIGHT)) {
            opposite = Direction.LEFT;
        } else {
            opposite = Direction.RIGHT;
        }
        MoveUtils.turn(cube, color1, first);
        moves.add(new Pair<Color, Direction>(color1, first));
        MoveUtils.turn(cube, color2, opposite);
        moves.add(new Pair<Color, Direction>(color2, opposite));
        MoveUtils.turn(cube, color1, opposite);
        moves.add(new Pair<Color, Direction>(color1, opposite));
    }
}
