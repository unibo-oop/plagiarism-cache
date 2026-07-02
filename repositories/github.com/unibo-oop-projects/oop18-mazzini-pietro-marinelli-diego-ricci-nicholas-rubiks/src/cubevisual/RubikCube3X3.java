package cubevisual;

import cubecontroller.CubeDimensions;
import cubestructure.LogicStructure;
import movestructure.Face;

/**
 * Extension of {@link RubikCubeAbstract}.
 * Dimension: 3X3X3.
 */
public class RubikCube3X3 extends RubikCubeAbstract {

    /**
     * RubikCube3X3 constructor, needs a {@link LogicStructure} as base.
     * @param size - The size of a single cube.
     * @param logicCube - The logic cube that is the base of the visual cube.
     */
    public RubikCube3X3(final double size, final LogicStructure logicCube) {
        super();
        for (int depth = 0; depth < CubeDimensions.Cube3X3.getDimension(); depth++) {
            for (int rows = 0; rows < CubeDimensions.Cube3X3.getDimension(); rows++) {
                for (int cols = 0; cols < CubeDimensions.Cube3X3.getDimension(); cols++) {
                    final Cube q = new CubeImpl(size, logicCube.getRubikCube()[rows][cols][depth].getBack(),
                            logicCube.getRubikCube()[rows][cols][depth].getBottom(),
                            logicCube.getRubikCube()[rows][cols][depth].getRight(),
                            logicCube.getRubikCube()[rows][cols][depth].getLeft(),
                            logicCube.getRubikCube()[rows][cols][depth].getTop(),
                            logicCube.getRubikCube()[rows][cols][depth].getFront());
                    // Why -1? Because we start "drawing" cubes by the center,
                    // not from the top left corner
                    q.getCube().setTranslateX((cols - 1) * size);
                    q.getCube().setTranslateY((rows - 1) * size);
                    q.getCube().setTranslateZ((depth - 1) * size);
                    getCubeMap().put(logicCube.getRubikCube()[rows][cols][depth].getID(), q);
                    getCubeGroup().getChildren().add(q.getCube());
                }
            }
        }

        int id;
        for (final CentersCoords3X3 center : CentersCoords3X3.values()) {
            id = logicCube.getRubikCube()[center.getRows()][center.getCols()][center.getDepth()].getID();
            getCentersMap().put(id, getCubeMap().get(id).getCube());
            getCentersColorMap().put(getCubeMap().get(id).getCube(), Face.getFaceByColor(center.getColor()));
        }
    }
}
