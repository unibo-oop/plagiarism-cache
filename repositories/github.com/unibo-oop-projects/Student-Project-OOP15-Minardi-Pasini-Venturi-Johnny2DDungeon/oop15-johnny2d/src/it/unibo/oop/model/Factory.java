package it.unibo.oop.model;

import static it.unibo.oop.utilities.CharactersSettings.INVISIBLE_ENEMY;
import static it.unibo.oop.utilities.CharactersSettings.WALL;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import it.unibo.oop.utilities.Position;
import it.unibo.oop.utilities.Vector2;

/**
 * General factory that contains more specific Factories
 */
public class Factory {
    /**
     * Specific {@link Factory} that gets the walls and groups of {@link Wall}
     */
    public static class WallFactory {

        private static final double HUD_PERCENTAGE = 0.05;

        private WallFactory() {
        }

        /**
         * Creates an horizontal {@link Wall}
         * @param startingX The starting wall X pos
         * @param startingY The starting wall Y pos
         * @param dimension the number of bricks dimension
         * @return an horizontal wall
         */
        public static List<Wall> generateHorizontalWall(final int startingX, final int startingY, final int dimension) {
            final List<Wall> resultList = new ArrayList<>(dimension);
            for (int offset = 0; offset < WALL.getWidth() * dimension; offset++) {
                resultList.add(new Wall(offset + WALL.getWidth() / 2, startingY));
            }
            return resultList;
        }

        /**
         * Creates a vertical {@link Wall}
         * @param startingX The starting wall X pos
         * @param startingY The starting wall Y pos
         * @param dimension the number of bricks dimension
         * @return a vertical wall
         */
        public static List<Wall> generateVerticalWall(final int startingX, final int startingY, final int dimension) {
            final List<Wall> resultList = new ArrayList<>(dimension);
            for (int offset = 0; offset < WALL.getHeight() * dimension; offset++) {
                resultList.add(new Wall(startingX, offset + WALL.getHeight() / 2));
            }
            return resultList;
        }

        /**
         * Creates an {@link Arena} made of {@link Wall}
         * @param panel height
         * @param panel width
         * @return A new {@link Arena}
         */
        public static Arena generateArena(final int panelHeight, final int panelWidth) {
            return new Arena(panelHeight, panelWidth, (int) (panelHeight * HUD_PERCENTAGE));
        }
    }

    /**
     * A {@link Factory} for the {@link MainCharacter}
     */
    public static class MainCharacterFactory {

        private MainCharacterFactory() {
        }

        /**
         * Creates a {@link MainCharacter} centered in the {@link Rectangle} panel
         * passed
         * @param panel the rectangle of the panel
         * 
         * @return A new {@link MainCharacter}
         */
        public static MainCharacter generateCentredCharacter(final Rectangle panel) {
            return new MainCharacter(panel.getCenterX(), panel.getCenterY());
        }

        /**
         * Generates a {@link MainCharacter} still in a defined {@link Position}
         * @param startingX starting X
         * @param startingY starting Y
         * @return A new {@link MainCharacter}
         */
        public static MainCharacter generateStillCharacter(final double startingX, final double startingY) {
            return new MainCharacter(startingX, startingY);
        }

        /**
         * Generates a {@link MainCharacter} with a movement {@link Vector2}
         * defined
         * @param startingX starting X
         * @param startingY starting Y
         * @return A new {@link MainCharacter}
         */
        public static MainCharacter generateMovingCharacter(final double startingX, final double startingY,
                final Vector2 movement) {
            return new MainCharacter(startingX, startingY, movement);
        }
    }

    /**
     * A {@link Factory} for the {@link InvisibleMonster} and the
     * {@link BasicMonster}
     */
    public static class EnemiesFactory {

        private EnemiesFactory() {
        }

        /**
         * Generates a {@link BasicMonster} in a defined {@link Position}
         * without a movement {@link Vector2}         
         * @param intialX starting X
         * @param intialY starting Y
         * @return A new {@link BasicMonster}
         */
        public static BasicMonster generateStillBasicEnemy(final double intialX, final double intialY) {
            return new BasicMonster(intialX, intialY, new Vector2());
        }

        /**
         * Generates a {@link InvisibleMonster} in a defined {@link Position}
         * without a movement {@link Vector2}
                  
         * @param intialX starting X
         * @param intialY starting Y
         * @return A new {@link InvisibleMonster}
         */
        public static InvisibleMonster generateStillInvisibleEnemy(final double intialX, final double intialY) {
            return new InvisibleMonster(intialX, intialY, new Vector2(), INVISIBLE_ENEMY.getSpeed());
        }
    }

    /**
     * A {@link Factory} for the {@link Bullet} class
     */
    public static class BulletFactory {

        private BulletFactory() {
        }

        /**
         * Generates a {@link Bullet} that starts from a {@link MainCharacter}
         * @param mainChar {@link MainCharacter} that shot the {@link Bullet}
         * @return A new {@link Bullet}
         */
        public static Bullet shootBulletFromCharacter(final MainCharacter mainChar) {
            return new Bullet(mainChar);
        }

        /**
         * Generates a {@link Bullet} with a starting {@link Vector2} and
         * {@link Position} 
         * @param startingX starting X
         * @param startingY starting Y
         */
        public static Bullet createBullet(final double startingX, final double startingY,
                final Vector2 movementVector) {
            return new Bullet(startingX, startingY, movementVector);
        }
    }
}