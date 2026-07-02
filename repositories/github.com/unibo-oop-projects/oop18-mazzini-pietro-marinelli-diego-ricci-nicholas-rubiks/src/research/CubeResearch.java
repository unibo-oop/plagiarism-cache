package research;

import cubestructure.Cube;
import javafx.scene.paint.Color;

/** This class allow to find any cube position by giving his colors. */
public class CubeResearch {
    private static final int NUM_FACES = 6;
    private final Cube[][][] cube;

    /**
     * rubik CubeResearch constructor, initialize the rubik cube.
     * 
     * @param cube the rubik cube
     */
    public CubeResearch(final Cube[][]... cube) {
        this.cube = cube.clone();
    }

    /**
     * Return an array with the 3 coordinates of the cube searched with 2 colors.
     * 
     * @param color1 the first color
     * 
     * @param color2 the second color
     * 
     * @return the array with the position, {-1, -1, -1} if the combination not
     *         found
     */
    public Integer[] twoFacePosition(final Color color1, final Color color2) {
        Color[] faceColor = new Color[NUM_FACES];
        Integer[] posizione = { -1, -1, -1 };
        if (!isValidTwo(color1, color2)) {
            return posizione;
        } else {
            int save = 0;
            for (int r = 0; r <= 2; r++) {
                for (int c = 0; c <= 2; c++) {
                    for (int d = 0; d <= 2; d++) {
                        faceColor[0] = cube[r][c][d].getTop();
                        faceColor[1] = cube[r][c][d].getBottom();
                        faceColor[2] = cube[r][c][d].getBack();
                        faceColor[3] = cube[r][c][d].getFront();
                        faceColor[4] = cube[r][c][d].getLeft();
                        faceColor[NUM_FACES - 1] = cube[r][c][d].getRight();
                        for (int i = 0; i < NUM_FACES; i++) {
                            if (faceColor[i].equals(color1)) {
                                for (int k = 0; k < NUM_FACES; k++) {
                                    if (faceColor[k].equals(color2)) {
                                        for (int l = 0; l < NUM_FACES; l++) {
                                            if (l != i && l != k && faceColor[l].equals(Color.GREY)) {
                                                save++;
                                            }
                                        }
                                        if (save == 4) {
                                            posizione[0] = r;
                                            posizione[1] = c;
                                            posizione[2] = d;
                                        } else {
                                            save = 0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return posizione;
        }
    }

    /**
     * Return if two colors are valid to be setted.
     * 
     * @return boolean
     * 
     * @param color1 the first color
     * 
     * @param color2 the second color
     * 
     */
    public boolean isValidTwo(final Color color1, final Color color2) {
        if (color1.equals(color2)) {
            return false;
        }
        if (color1.equals(Color.WHITE) && color2.equals(Color.YELLOW)) {
            return false;
        }
        if (color1.equals(Color.YELLOW) && color2.equals(Color.WHITE)) {
            return false;
        }
        if (color1.equals(Color.BLUE) && color2.equals(Color.GREEN)) {
            return false;
        }
        if (color1.equals(Color.GREEN) && color2.equals(Color.BLUE)) {
            return false;
        }
        if (color1.equals(Color.RED) && color2.equals(Color.ORANGE)) {
            return false;
        }
        return (!(color1.equals(Color.ORANGE) && color2.equals(Color.RED)));

    }

    /**
     * Return an array with the 3 coordinates of the cube searched with 3 colors.
     * 
     * @param color1 the first color
     * 
     * @param color2 the second color
     * 
     * @param color3 the third color
     * 
     * @return the array with the position, {-1, -1, -1} if the combination not
     *         found
     */
    public Integer[] threeFacePosition(final Color color1, final Color color2, final Color color3) {
        Color[] faceColor = new Color[NUM_FACES];
        Integer[] posizione = { -1, -1, -1 };
        if (!isValidThree(color1, color2, color3)) {
            return posizione;
        } else {
            for (int r = 0; r <= 2; r++) {
                for (int c = 0; c <= 2; c++) {
                    for (int d = 0; d <= 2; d++) {
                        faceColor[0] = cube[r][c][d].getTop();
                        faceColor[1] = cube[r][c][d].getBottom();
                        faceColor[2] = cube[r][c][d].getBack();
                        faceColor[3] = cube[r][c][d].getFront();
                        faceColor[4] = cube[r][c][d].getLeft();
                        faceColor[NUM_FACES - 1] = cube[r][c][d].getRight();
                        for (int i = 0; i < NUM_FACES; i++) {
                            if (faceColor[i].equals(color1)) {
                                for (int k = 0; k < NUM_FACES; k++) {
                                    if (faceColor[k].equals(color2)) {
                                        for (int l = 0; l < NUM_FACES; l++) {
                                            if (faceColor[l].equals(color3)) {
                                                posizione[0] = r;
                                                posizione[1] = c;
                                                posizione[2] = d;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return posizione;
        }
    }

    /**
     * Return if three colors are valid to be setted.
     * 
     * @return boolean
     * 
     * @param color1 the first color
     * 
     * @param color2 the second color
     * 
     * @param color3 the third color
     */
    public boolean isValidThree(final Color color1, final Color color2, final Color color3) {
        if (color1.equals(color2) && color2.equals(color3) && color3.equals(color1)) {
            return false;
        }
        return (isValidTwo(color1, color2) && isValidTwo(color2, color3) && isValidTwo(color1, color3));
    }

    /**
     * Return the color of the center of a given position.
     * 
     * @param posTmp the array indicating the position of the center
     * 
     * @return the color of the center
     */
    public Color getCenterColor(final Integer... posTmp) {
        Color[] faceColor = new Color[NUM_FACES];
        Color center = null;
        final Integer[] pos = posTmp;
        if (pos[0] == 1 && pos[1] == 1 && pos[2] == 0 || pos[1] == 0 && pos[2] == 1 || pos[1] == 2 && pos[2] == 1
                || pos[1] == 1 && pos[2] == 2) {
            faceColor[0] = cube[pos[0]][pos[1]][pos[2]].getTop();
            faceColor[1] = cube[pos[0]][pos[1]][pos[2]].getBottom();
            faceColor[2] = cube[pos[0]][pos[1]][pos[2]].getBack();
            faceColor[3] = cube[pos[0]][pos[1]][pos[2]].getFront();
            faceColor[4] = cube[pos[0]][pos[1]][pos[2]].getLeft();
            faceColor[NUM_FACES - 1] = cube[pos[0]][pos[1]][pos[2]].getRight();
            for (int i = 0; i < NUM_FACES; i++) {
                if (!faceColor[i].equals(Color.GRAY)) {
                    center = faceColor[i];

                }
            }
        }
        return center;
    }

}
