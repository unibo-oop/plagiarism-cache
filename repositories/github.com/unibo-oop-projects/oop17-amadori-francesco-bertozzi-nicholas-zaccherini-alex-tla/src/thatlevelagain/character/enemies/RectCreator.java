package thatlevelagain.character.enemies;

import java.awt.Rectangle;

/**
 * Class that creates a Rectangle near the Enemy to verify its position respect to
 * objects of class Mattoni. If the rectangle doesn't touch a mattone and the player touch 
 * one. 
 * 
 * @author
 *
 */
public final class RectCreator {

    //private final EnemyInterface enemy;
    private static final RectCreator SINGLETON = new RectCreator();

    /**
     * 
     * @param enemy
     *          Object of a class that implements EnemyInterface. 
     */
    private RectCreator(/*final EnemyInterface enemy*/) {
        //NOT USED
    }

    /**
     * 
     * @return 
     *          SINGLETON RectCreator
     */
    public static RectCreator getRectCreator() {
        return SINGLETON;
    }

    /**
     * @param enemy
     *          Object of a class that implements EnemyInterface
     * 
     * @return 
     *          the created Rectangle in the right position
     */
    public Rectangle create(final EnemyInterface enemy) {
        if (enemy.getDirection() == EnemyInterface.LEFT) {
            return createRectSX(enemy);
        } else {
            return createRectDX(enemy);
        }
    }

    /**
     * @param enemy
     *          Object of a class that implements EnemyInterface
     *
     * @return 
     *          the left rectangle
     */
    public Rectangle createRectSX(final EnemyInterface enemy) {
        final Rectangle rect = new Rectangle(enemy.getRectLeft());
        rect.setLocation(enemy.getX() - EnemyInterface.RECT_SIDE, enemy.getY() + enemy.getHeight() - EnemyInterface.RECT_SIDE);
        rect.setSize(EnemyInterface.RECT_SIDE, EnemyInterface.RECT_SIDE);
        return rect;
    }

    /**
     * @param enemy
     *          Object of a class that implements EnemyInterface
     * 
     * @return 
     *          the right rectangle
     */
    public Rectangle createRectDX(final EnemyInterface enemy) {
        final Rectangle rect = new Rectangle(enemy.getRectRight());
        final int currentX = rect.x;
        rect.setLocation(currentX + EnemyInterface.RECT_SIDE, rect.y);
        rect.setSize(EnemyInterface.RECT_SIDE, enemy.getHeight());
        return rect;
    }
}
