package pvz.model.plants.api;
import pvz.utilities.Position;

/**
 * Public interface for a factory that creates different types of {@link Plant} instances.
 */
public interface PlantFactory {

    /**
     * Creates a new Peashooter plant at the specified position.
     *
     * @param position the position where the Peashooter will be placed
     * @return a new {@link Plant} instance
     */
    Plant createPeashooter(Position position);

    /**
     * Creates a new Sunflower plant at the specified position.
     *
     * @param position the position where the Sunflower will be placed
     * @return a new {@link Plant} instance
     */
    Plant createSunflower(Position position);

    /**
     * Creates a new Wall-nut plant at the specified position.
     *
     * @param position the position where the Wall-nut will be placed
     * @return a new {@link Plant} instance
     */
    Plant createWallnut(Position position);
}
