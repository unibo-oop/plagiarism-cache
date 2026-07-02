package cubestructure;

import research.CubeResearch;
import java.util.ArrayList;
import java.util.List;

import algorithms.GeneralAlgorithm;
import javafx.scene.paint.Color;
import movestructure.SideUtils;

/**
 * Class whit the purpose of checking if the User has created a correct
 * {@link Cube3X3}.
 */
public class CubeCheck {
    private Boolean done;
    private int check;
    private final List<Color> colors;
    private final Cube3X3 test;
    private CubeResearch search;
    private GeneralAlgorithm algo;

    /**
     * This constructor initialize a CubeCheck Object that have to check if User had
     * set {@link Cube3X3} correctly.
     */
    public CubeCheck() {
        this.done = false;
        this.check = 0;
        this.colors = new ArrayList<>();
        this.test = new Cube3X3();
        this.test.setCompletedCube();
        this.search = new CubeResearch(test.getRubikCube());
    }

    /**
     * Method that craft a list of the colored face of the cube (Color.GRAY ==
     * uncolored).
     * 
     * @param cube - is the {@link Cube} to check
     */
    private void getColors(final Cube cube) {

        final Cube temp = cube.getCopyOf();
        this.colors.clear();
        if (!temp.getTop().equals(Color.GRAY)) {
            this.colors.add(cube.getTop());
        }
        if (!temp.getFront().equals(Color.GRAY)) {
            this.colors.add(cube.getFront());
        }
        if (!temp.getLeft().equals(Color.GRAY)) {
            this.colors.add(cube.getLeft());
        }
        if (!temp.getRight().equals(Color.GRAY)) {
            this.colors.add(cube.getRight());
        }
        if (!temp.getBack().equals(Color.GRAY)) {
            this.colors.add(cube.getBack());
        }
        if (!temp.getBottom().equals(Color.GRAY)) {
            this.colors.add(cube.getBottom());
        }
        // if the cube is all Color.Gray or is not totally completed
        if (this.colors.size() < 2) {
            this.colors.clear();
            this.colors.add(Color.GRAY);
        }
    }

    /**
     * check if all the {@link Cube}s of User {@link Cube3X3} have a valid
     * combination of {@link Color}s if a cube doesn't exist become an uncolored
     * {@link Cube}(all Color.GRAY) if the RubikCube is uncompleted, updated cube is
     * sent to the User that will have to complete, that will be repeated until the
     * RubikCube is Completed correctly.
     * 
     * @param cube - Cube3X3 to test.
     * @return true if rubik is correct, false otherwise
     */
    public Boolean checkUserWork(final Cube3X3 cube) {
        SideUtils.reset();
        final Cube[][][] rubik = cube.getRubikCube();
        this.check = 0;
        for (int r = 0; r <= 2; r++) {
            for (int c = 0; c <= 2; c++) {
                for (int d = 0; d <= 2; d++) {
                    if ((!isACenter(r, c, d))) {
                        Integer[] pos;
                        getColors(rubik[r][c][d].getCube());
                        if (this.colors.size() != 1) {
                            // the 2 following IF check if the Cube have 3 colorable face and if they are
                            // colored
                            if (isAnAngle(r, c, d)) {
                                if (this.colors.size() < 3) {
                                    setFail(rubik[r][c][d].getCube());
                                } else {
                                    pos = this.search.threeFacePosition(this.colors.get(0), this.colors.get(1),
                                            this.colors.get(2));
                                    checkExistence(pos, rubik[r][c][d].getCube());
                                }
                            } else {
                                pos = this.search.twoFacePosition(this.colors.get(0), this.colors.get(1));
                                checkExistence(pos, rubik[r][c][d].getCube());
                            }
                        } else {
                            setFail(rubik[r][c][d].getCube());
                        }
                    }
                }
            }
        }
        if (check != 0) {
            resetResearch();
            return false;
        }
        if (lastCheck(cube.getCopyOf())) {
            this.done = true;
            return true;
        }
        cube.setGivenCube();
        resetResearch();
        this.done = false;
        return false;

    }

    /**
     * Return {@link GeneralAlgorithm} used as last check for Cube[][][] check.
     * 
     * @return {@link GeneralAlgorithm}
     */
    public final GeneralAlgorithm getAlgorithm() {
        return this.algo;
    }

    private void resetResearch() {
        this.test.setCompletedCube();
        this.search = new CubeResearch(this.test.getRubikCube());
    }

    private Boolean lastCheck(final Cube3X3 cube) {
        try {
            algo = new GeneralAlgorithm(cube.getCopyOf());
        } catch (Exception e) {
            return false;
        }
        return algo.isCubeValid();
    }

    private void checkExistence(final Integer[] pos, final Cube cube) {
        if (pos[0] == -1 && pos[1] == -1 && pos[2] == -1) {
            setFail(cube);
        } else {
            test.getRubikCube()[pos[0]][pos[1]][pos[2]].setCube(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY,
                    Color.GRAY, Color.GRAY);
        }
    }

    /**
     * Private Method that is called when a checked {@link Cube} is not valid, it
     * set it all Gray.
     * 
     * @param cube - {@link Cube} subject of the check
     */
    private void setFail(final Cube cube) {
        this.check++;
        cube.setCube(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY);
    }

    /**
     * Check if the subject {@link Cube} is a center.
     * 
     * @param r - Row coordinates of {@link Cube} in RubikCube array
     * @param c - Column coordinates of {@link Cube} in RubikCube array
     * @param d - Depth coordinates of {@link Cube} in RubikCube array
     * @return true if {@link Cube} is a Central {@link Cube}
     */
    private Boolean isACenter(final int r, final int c, final int d) {
        // CENTER OF THE RUBICK CUBE
        if ((r == 1) && (c == 1) && (d == 1)) {
            return true;
        }
        // WHITE CENTER
        if ((r == 0) && (c == 1) && (d == 1)) {
            return true;
        }
        // RED CENTER
        if ((r == 1) && (c == 1) && (d == 0)) {
            return true;
        }
        // GREEN CENTER
        if ((r == 1) && (c == 0) && (d == 1)) {
            return true;
        }
        // BLUE CENTER
        if ((r == 1) && (c == 2) && (d == 1)) {
            return true;
        }
        // ORANGE CENTER
        if ((r == 1) && (c == 1) && (d == 2)) {
            return true;
        }
        // YELLOW CENTER
        if ((r == 2) && (c == 1) && (d == 1)) {
            return true;
        }
        return false;
    }

    /**
     * Check if the subject {@link Cube} can have 3 Colored faces.
     * 
     * @param r - Row coordinates of {@link Cube} in RubikCube array
     * @param c - Column coordinates of {@link Cube} in RubikCube array
     * @param d - Depth coordinates of {@link Cube} in RubikCube array
     * @return true if {@link Cube} is in an Angle position
     */
    private Boolean isAnAngle(final int r, final int c, final int d) {
        if (r == 0 && c == 0 && d == 0) {
            return true;
        }
        if (r == 2 && c != 1 && d != 1) {
            return true;
        }
        if (c == 2 && r != 1 && d != 1) {
            return true;
        }
        if (d == 2 && c != 1 && r != 1) {
            return true;
        }
        return false;
    }

    /**
     * Check if the {@link CubeCheck} has ended his work.
     * 
     * @return true if {@link CubeCheck} has done.
     */
    public final Boolean isItDone() {
        return this.done;
    }
}
