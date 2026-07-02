package model.properties.defaultdata;

/**
 * 
 * A interface that returns different possible values of data.
 *
 */
public interface DefaultDataEnum {
    
    /**
     * 
     * @return container with data.
     */
    DefaultDataContainer<? extends Number> getData();

}
