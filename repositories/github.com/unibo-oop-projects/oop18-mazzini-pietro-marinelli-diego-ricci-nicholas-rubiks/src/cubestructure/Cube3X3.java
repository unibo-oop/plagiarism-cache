package cubestructure;

import java.util.Random;
import javafx.scene.paint.Color;
import movestructure.Direction;
import movestructure.MoveUtils;
import movestructure.SideUtils;
/**
 * Implementation of {@link LogicStructure}.
 */
public class Cube3X3 implements LogicStructure {
    private static final int NUM_RAND_MOVES = 30;
    private static final int NUM_CUBE_FACES = 6;
    private static final int NUM_DIRECTIONS = 2;

    private final Cube[][][] rubik;
    /**
     * This constructor initialize an array 3x3 of {@link Cube}(Cube[][][]).
     */
    public Cube3X3() {
        rubik = new Cube[3][3][3];
        int id = 0;
        for (int r = 0; r <= 2; r++) {
            for (int c = 0; c <= 2; c++) {
                for (int d = 0; d <= 2; d++) {
                    this.rubik[r][c][d] = new Cube(id, r, c, d);
                    id++;
                }
            }
        }
    }
    /**
     * {@inheritDoc}.
     */
    @Override
    public void setCompletedCube() {
        SideUtils.reset();
        SideUtils.setFace(this.rubik, Color.WHITE);
        SideUtils.setFace(this.rubik, Color.RED);
        SideUtils.setFace(this.rubik, Color.GREEN);
        SideUtils.setFace(this.rubik, Color.BLUE);
        SideUtils.setFace(this.rubik, Color.ORANGE);
        SideUtils.setFace(this.rubik, Color.YELLOW);
        SideUtils.reset();
    }

    @Override
    public final void setRandomCube() {
        final Random random = new Random();
        int  n;
        Direction dir = null;
        Direction lastDir = null;
        Color centerC = Color.GRAY;
        final Color lastC = Color.GRAY;
        this.setCompletedCube();
        /*
         * that "30" are the number for random moves the RubikCube do, they can be increased if needed
         * yes... is a "Magic Number", but is the only position where it's used so i decided to let him live
         */
        for (int i = 0; i <= NUM_RAND_MOVES; i++) {
            //this is where we chose a random face of the cube to move
            n = random.nextInt(NUM_CUBE_FACES);
            switch (n) {
                case 0 : centerC = Color.WHITE;
                break;
                case 1 : centerC = Color.RED; 
                break;
                case 2 : centerC = Color.GREEN; 
                break;
                case 3 : centerC = Color.BLUE;
                break;
                case 4 : centerC = Color.ORANGE;
                break;
                case (NUM_CUBE_FACES - 1) : centerC = Color.YELLOW; 
                break;
                default:
                    break;
            }
            //now we chose if rotate Right or Left
            n = random.nextInt(NUM_DIRECTIONS);
            switch (n) {
                case 0: dir = Direction.LEFT;
                break;
                case 1: dir = Direction.RIGHT; 
                break;
                default:
                    break;
            }
            //this is a check for "avoid" the inverse movement of the last face moved
            if (centerC.equals(lastC) && (!dir.equals(lastDir))) {
                i--;
            }
            lastDir = dir;
            //in the end we call the Turn method of Move to do the movement we selected
            MoveUtils.turn(this.rubik, centerC, dir);
        }
    }
    private void setAllGray() {
        SideUtils.reset();
        SideUtils.extractSide(this.rubik, Color.WHITE);
        SideUtils.getSide().forEach(e -> e.setTop(Color.GRAY));
        SideUtils.extractSide(this.rubik, Color.RED);
        SideUtils.getSide().forEach(e -> e.setFront(Color.GRAY));
        SideUtils.extractSide(this.rubik, Color.GREEN);
        SideUtils.getSide().forEach(e -> e.setLeft(Color.GRAY));
        SideUtils.extractSide(this.rubik, Color.BLUE);
        SideUtils.getSide().forEach(e -> e.setRight(Color.GRAY));
        SideUtils.extractSide(this.rubik, Color.ORANGE);
        SideUtils.getSide().forEach(e -> e.setBack(Color.GRAY));
        SideUtils.extractSide(this.rubik, Color.YELLOW);
        SideUtils.getSide().forEach(e -> e.setBottom(Color.GRAY));
        SideUtils.reset();
    }
    @Override
    public final void setGivenCube() {
        setAllGray();
        this.rubik[1][1][0].setCube(Color.GRAY, Color.RED, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY);
        this.rubik[0][1][1].setCube(Color.WHITE, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY);
        this.rubik[1][0][1].setCube(Color.GRAY, Color.GRAY, Color.GREEN, Color.GRAY, Color.GRAY, Color.GRAY);
        this.rubik[1][1][2].setCube(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY, Color.ORANGE, Color.GRAY);
        this.rubik[1][2][1].setCube(Color.GRAY, Color.GRAY, Color.GRAY, Color.BLUE, Color.GRAY, Color.GRAY);
        this.rubik[2][1][1].setCube(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY, Color.YELLOW);
    }
    @Override
    public final Cube[][][] getRubikCube() {
        return this.rubik;
    }
    @Override
    public final Cube3X3 getCube3X3() {
        return this;
    }
    @Override
    public final Cube3X3 getCopyOf() {
        final Cube3X3 cube = new Cube3X3();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                for (int d = 0; d < 3; d++) {
                    cube.getRubikCube()[r][c][d] = this.rubik[r][c][d].getCopyOf();
                }
            }
        }
        return cube;
    }
}
