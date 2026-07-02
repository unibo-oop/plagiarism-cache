package model.properties.defaultdata;

/**
 * 
 * Every enum contains a container with limits and default value of different utilities utilized by world.
 *
 */
public enum WorldDefaultDataEnum implements DefaultDataEnum {
    /**
     * Contains integer values of the minimum, maximum and default world height size.
     */
    WORLD_HEIGHT(new DefaultDataContainerImpl<Integer>(10, 150, 75)),
    /**
     * Contains integer values of the minimum, maximum and default world width size.
     */
    WORLD_WIDTH(new DefaultDataContainerImpl<Integer>(10, 200, 110));

    private final DefaultDataContainer<? extends Number> data;

    WorldDefaultDataEnum(final DefaultDataContainer<? extends Number> data) {
        this.data = data;
    }

    @Override
    public DefaultDataContainer<? extends Number> getData() {
        return this.data;
    }
}
