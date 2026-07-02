package algorithms;

import cubestructure.Cube3X3;

/**
 * Class for tests.
 * 
 */
public class TestsUtils {
    /**
     * Automatic test.
     */

    public TestsUtils() {
        final Cube3X3 rubik = new Cube3X3();
        for (int i = 0; i < 100; i++) {
            rubik.setRandomCube();
            final GeneralAlgorithm resolver = new GeneralAlgorithm(rubik);
            if (!resolver.isCubeValid()) {
                System.out.println("errore");
            }
        }
    }

}
