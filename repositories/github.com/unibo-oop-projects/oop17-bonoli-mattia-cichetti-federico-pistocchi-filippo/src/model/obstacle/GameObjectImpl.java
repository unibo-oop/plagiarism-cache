package model.obstacle;

/**
 *
 */
public class GameObjectImpl implements GameObject { // NOPMD

    /* 
     * Suppressed warning "Class cannot be instantiated and does not provide any static methods or fields"
     * This is not correct because there's a public constructor so class can be instantiated.
     */

    /**
     * GameObjectType represents what the object is.
     * The first parameter is the width of the obstacle.
     * The second parameter is the BaseType of the GameObject
    */
    public enum GameObjectType {

        /**
         *
         */
        TRUCK(12.0, GameObjectBaseType.VEHICLE),

        /**
         *
         */
        PURPLE_CAR(7.0, GameObjectBaseType.VEHICLE),

        /**
         *
         */
        RACE_CAR(7.0, GameObjectBaseType.VEHICLE),

        /**
         * 
         */
        YELLOW_CAR(7.0, GameObjectBaseType.VEHICLE),

        /**
         *
         */
        WHITE_CAR(6.0, GameObjectBaseType.VEHICLE),

        /**
         *
         */
        BIG_LOG(40.0, GameObjectBaseType.LOG),

        /**
         *
         */
        MEDIUM_LOG(26.0, GameObjectBaseType.LOG),

        /**
         *
         */
        SMALL_LOG(19.0, GameObjectBaseType.LOG),

        /**
         * 
         */
        TURTLE(6.0, GameObjectBaseType.TURTLE),

        /**
         * 
         */
        MOD(8.0, GameObjectBaseType.MOD),

        /**
         * 
         */
        FROG(10.0, GameObjectBaseType.FROG);

        private final double width;
        private final GameObjectBaseType baseType;

        GameObjectType(final double width, final GameObjectBaseType baseType) {
            this.width = width;
            this.baseType = baseType;
        }

        /**
         * @return the width of the obstacle.
         */
        public double getWidth() {
            return this.width;
        }

        /**
         *
         * @return the baseType effective type of the obstacle.
         */
        public GameObjectBaseType getBaseType() {
            return this.baseType;
        }

        /**
         * 
         * @param center the center of the obstacle to create.
         * @return the new obstacle.
         */
        public GameObject create(final double center) {
            return new GameObjectImpl(center, this);
        }

    }

    private double center;
    private final GameObjectType obstacleType;

    /**
     * 
     * @param center is the center of the obstacle.
     * @param obstacleType is the type of the obstacle.
     */
    private GameObjectImpl(final double center, final GameObjectType obstacleType) {
        this.center = center;
        this.obstacleType = obstacleType;
    }

    /**
     * 
     */
    @Override
    public double getCenter() {
        return this.center;
    }

    /**
     * 
     */
    @Override
    public void setCenter(final double newCenter) {
        this.center = newCenter;
    }

    /**
     * 
     */
    @Override
    public GameObjectType getGameObjectType() {
        return this.obstacleType;
    }

    /**
     * 
     */
    @Override
    public double getWidth() {
        return this.obstacleType.getWidth();
    }

    /**
     * 
     */
    @Override
    public GameObjectBaseType getBaseType() {
        return this.obstacleType.getBaseType();
    }

}
