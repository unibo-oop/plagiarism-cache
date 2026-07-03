package breakout.controller.levels;

import breakout.model.ClassicFactory;
import breakout.model.entities.BrickType;
import breakout.model.entities.Wall.WallPos;
import breakout.model.levels.LevelImpl;
import breakout.view.graphics.Colors;
import javafx.util.Pair;

/**
 * Creates the classic level.
 *
 */
public final class ClassicLevel {
    private static final int BRICK_PER_ROW = 18;
    private static final int ROWS = 6;

    private static final LevelImpl CLASSIC_LEVEL = new LevelImpl();
    static {
        for (int i = 0; i < ROWS; i++) {
            Colors color = null;
            BrickType type = null;
            for (int j = 1; j <= BRICK_PER_ROW; j++) {
                switch (i) {
                case 0:
                    color = Colors.CLASSICRED;
                    type = BrickType.SEVEN_CLASSIC;
                    break;
                case 1:
                    color = Colors.CLASSICORANGE;
                    type = BrickType.FIVE_CLASSIC;
                    break;
                case 2:
                    color = Colors.CLASSICBROWN;
                    type = BrickType.THREE_CLASSIC;
                    break;
                case 3:
                    color = Colors.CLASSICYELLOW;
                    type = BrickType.THREE_CLASSIC;
                    break;
                case 4:
                    color = Colors.CLASSICGREEN;
                    type = BrickType.ONE_CLASSIC;
                    break;
                case (ROWS - 1):
                    color = Colors.CLASSICBLUE;
                    type = BrickType.ONE_CLASSIC;
                    break;
                default:
                    color = Colors.CLASSICBLUE;
                    type = BrickType.ONE_CLASSIC;
                }
                CLASSIC_LEVEL.addBrick(new Pair<>(i + 3, j), type, color);
            }
        }
        CLASSIC_LEVEL.addWall(ClassicFactory.get().createWall(WallPos.UP), Colors.CLASSICGREY);
        CLASSIC_LEVEL.addWall(ClassicFactory.get().createWall(WallPos.LEFT), Colors.CLASSICGREY);
        CLASSIC_LEVEL.addWall(ClassicFactory.get().createWall(WallPos.RIGHT), Colors.CLASSICGREY);
    }

    private ClassicLevel() {
    }

    /**
     * @return the istance of a classicLevel
     */
    public static LevelImpl getClassicLevel() {
        return CLASSIC_LEVEL;
    }

}
