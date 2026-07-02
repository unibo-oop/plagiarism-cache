package model.properties.defaultdata;

/**
 * Every enum contains a container with limits and default value of different
 * utilities utilized by view.
 *
 */
public enum ViewDefaultDataEnum implements DefaultDataEnum {
    /**
     * Contains integer values of the minimum, maximum and default number that represents after how
     * many cycles the world must be redesigned on screen.
     */
    UPDATE_VIEW(new DefaultDataContainerImpl<Integer>(1, 200, 1)),
    /**
     * Contains the integer values of the minimum, maximum and default number of hue values in the HSB model.
     */
    COLOR_HSB_RANGE(new DefaultDataContainerImpl<Integer>(0, 360, 0)),
    /**
     * Contains the integer values of the minimum, maximum and default number of hue values in the HSB model.
     */
    COLOR_RGB_RANGE(new DefaultDataContainerImpl<Integer>(0, 255, 0));

    private final DefaultDataContainer<? extends Number> data;

    ViewDefaultDataEnum(final DefaultDataContainer<? extends Number> data) {
        this.data = data;
    }

    @Override
    public DefaultDataContainer<? extends Number> getData() {
        return this.data;
    }
}
