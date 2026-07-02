package bzzbomber.model.utilities;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an utility which loads content of several external game files for
 * the correct execution of the game.
 */

public final class FileLoader {

    private FileLoader() {

    }

    /**
     * @param path
     *            file's path.
     * @param tileW
     *            tile width
     * @param tileH
     *            tile height
     * @return Correct points for the starting positions of the bomber.
     */
    public static Point loadStartPositionBomber(final String path, final int tileW, final int tileH) {

        final Point position = new Point(0, 0);
        try {
            final InputStream in = FileLoader.class.getResourceAsStream(path);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));

            Integer startX = Integer.valueOf(br.readLine());
            Integer startY = Integer.valueOf(br.readLine());

            startX = Integer.valueOf((int) (startX * tileW));
            startY = Integer.valueOf((int) (startY * tileH));

            position.setLocation(new Point(startX.intValue(), startY.intValue()));
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return position;
    }

    /**
     * @param path
     *            is the path of file
     * @param tileW
     *            tile width
     * @param tileH
     *            tile height
     * @return Correct points for the starting positions of the entities.
     */
    public static List<Point> loadStartPositionsEntities(final String path, final int tileW, final int tileH) {
        final List<Point> positions = new ArrayList<>();
        try {
            final InputStream in = FileLoader.class.getResourceAsStream(path);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));

            final String firstLine = br.readLine();
            if (firstLine != null && firstLine.equals("[")) {
                final String delims = "\\,";
                final int pairLen = 2;
                String tmpLine = br.readLine();
                while (tmpLine != null && !tmpLine.equals("]")) {
                    if (tmpLine != null) {
                        final String[] pairXY = tmpLine.split(delims);
                        if (pairXY != null && pairXY.length == pairLen) {

                            Integer startX = Integer.valueOf(pairXY[0]);
                            Integer startY = Integer.valueOf(pairXY[1]);

                            startX = Integer.valueOf((int) (startX * tileW));
                            startY = Integer.valueOf((int) (startY * tileH));

                            final Point p = new Point(startX.intValue(), startY.intValue());
                            positions.add(p);
                        }
                    }
                    tmpLine = br.readLine();
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return positions;
    }

    /**
     * @param path
     *            to the map file
     * @return Converted matrix 2D of int from file to matrix 2D of int.
     */
    public static int[][] loadMap(final String path) {
        int[][] gameMap = null;
        try {
            final InputStream in = FileLoader.class.getResourceAsStream(path);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));

            final int numRows = Integer.parseInt(br.readLine());
            final int numCols = Integer.parseInt(br.readLine());

            gameMap = new int[numRows][numCols];

            final String delims = "\\s+";
            for (int row = 0; row < numRows; row++) {
                final String line = br.readLine();
                if (line != null) {
                    final String[] tokens = line.split(delims);
                    if (tokens != null) {
                        for (int col = 0; col < numCols; col++) {
                            final int value = Integer.parseInt(tokens[col]);
                            gameMap[row][col] = value;
                        }
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameMap;
    }

    /**
     * @param path
     *            path to file
     * @return Information about map dimension expressed in rows and columns.
     */
    public static Pair<Integer, Integer> loadMapDimension(final String path) {
        Pair<Integer, Integer> mapDimension = null;
        try {
            final InputStream in = FileLoader.class.getResourceAsStream(path);
            final BufferedReader br = new BufferedReader(new InputStreamReader(in));

            final int numRows = Integer.parseInt(br.readLine());
            final int numCols = Integer.parseInt(br.readLine());

            mapDimension = new Pair<>(Integer.valueOf(numRows), Integer.valueOf(numCols));

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapDimension;
    }

}
