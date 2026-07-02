package factories;

import controller.entities.Entity;
import enumerators.SpecificType;
import javafx.geometry.Point2D;
import model.entities.EntityModel;
import view.entities.EntityView;

/**
 * Base interface to create factory. A factory class should create an
 * {@link Entity} that contain {@link EntityModel} and {@link EntityView}
 */
interface FactoryInterface {

    /**
     * Create an {@link Entity} character.
     * 
     * @param type     {@link SpecificType} of the character to create
     * @param position where to place the character
     * @return the {@link Entity}
     */
    Entity createEntity(SpecificType type, Point2D position);

    /**
     * Create the {@link EntityModel}.
     * 
     * @param type     {@link SpecificType} of the character to create
     * @param position where to place the character
     * @return the {@link EntityModel}
     */
    EntityModel createModel(SpecificType type, Point2D position);

    /**
     * Create the {@link EntityView}.
     * 
     * @param eModel the {@link EntityModel} to get the data
     * @return the {@link EntityView}
     */
    EntityView createView(EntityModel eModel);

}
