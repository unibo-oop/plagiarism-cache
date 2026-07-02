package movestructure;

import java.util.ArrayList;
import java.util.List;

import cubestructure.Cube;
import javafx.scene.paint.Color;

/**
 * Utility class that that help other class to select the right {@link Cube}s to use.
 */
public final class SideUtils {
    private static Color lastColor = Color.GRAY;
    private static List<Cube> sideList = new ArrayList<>();

    private SideUtils() {
    }

    private static void pick(final  Cube[][][] rubik, final Color centerC) {
        Cube temp;
        //create a list of cube of specified side
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Color.WHITE == centerC || Color.YELLOW == centerC) {
                    sideList.add(rubik[Color.WHITE == centerC ? 0 : 2][i][j]);
                } else {
                    if (Color.RED == centerC || Color.ORANGE == centerC) {
                        sideList.add(rubik[i][j][Color.RED == centerC ? 0 : 2]);
                    } else {
                        if (Color.GREEN == centerC || Color.BLUE == centerC) {
                            sideList.add(rubik[i][Color.GREEN == centerC ? 0 : 2][j]);
                        }
                    }
                }
            }
        }
        //the following change are needed for the purpose of having a circular representation of the face
        temp = sideList.get(8);
        sideList.set(8, sideList.get(6));
        sideList.set(6, temp);
        temp = sideList.get(3);
        sideList.remove(3);
        sideList.add(temp);
        temp = sideList.get(3);
        sideList.remove(3);
        sideList.add(temp);
        // now we have a circular representation of the face with the center of the face as last element
    }
    /**
     * Method used for avoid useless call to Pick Method.
     * @param centerC - {@link Color} of the central cube of the chosen face
     * @param rubik - {@link Cube}[][][] subject of the extraction
     */
    public static void extractSide(final Cube[][][] rubik, final Color centerC) {
        if (!lastColor.equals(centerC)) {
            reset();
            pick(rubik, centerC);
        }
        lastColor = centerC;
    }
    /**
     * Return the side that is currently moving or was last moved.
     * @return List of {@link Cube}
     */
    public static List<Cube> getSide() {
        return sideList;
    }
    /**
     * Return a list of {@link Cube}s id of the current List of {@link Cube}.
     * @return List
     */
    public static List<Integer> getSideID() {
        final List<Integer> id = new ArrayList<>();
        sideList.forEach(e -> id.add(e.getID()));
        return id;
    }
    /**
     * Method used by {@link cubestructure.Cube3X3}.CompletedCube Method to craft a completed RubikCube.
     * @param centerC - {@link Color} of the central cube of the chosen face
     * @param rubik - {@link Cube}[][][] subject of the initialization
     */
    public static void setFace(final Cube[][][] rubik, final Color centerC) {
        pick(rubik, centerC);
        if (centerC == Color.WHITE) {
            sideList.forEach(e -> e.setTop(centerC));
        }
        if (centerC == Color.RED) {
            sideList.forEach(e -> e.setFront(centerC));
        }
        if (centerC == Color.GREEN) {
            sideList.forEach(e -> e.setLeft(centerC));
        }
        if (centerC == Color.BLUE) {
            sideList.forEach(e -> e.setRight(centerC));
        }
        if (centerC == Color.YELLOW) {
            sideList.forEach(e -> e.setBottom(centerC));
        }
        if (centerC == Color.ORANGE) {
            sideList.forEach(e -> e.setBack(centerC));
        }
        reset();
    }
    /**
     * Method to clear the List of {@link Cube}.
     */
    public static void reset() {
        lastColor = Color.GRAY;
        sideList.clear();
    }
    /**
     * Method that craft a String representation of the list of {@link Cube}.
     */
    public static void sideString() {
        for (int i = 0; i < sideList.size(); i++) {
            System.out.println("cubo girato n " + i);
            sideList.get(i).cubeToString();
        }
    }
}
