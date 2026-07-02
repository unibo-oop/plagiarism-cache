package util.mapbuilder;

/**
 * Describes the variable part of the case of the view, which includes
 * everything but the terrain(the background). This part of the case is the one
 * that will be refreshed every update.
 * @param <X> the type of the of the returned case
 */
public interface CaseBuilder<X> {

    /**
     * the bottom part of the case(the structure).
     * 
     * @param id of the element
     * @return the builder
     */
    CaseBuilder<X> setBottom(String id);

    /**
     * the top part of the case(the unit).
     * 
     * @param id of the element
     * @return the builder
     */
    CaseBuilder<X> setTop(String id);

    /**
     * the border of the case(the selection).
     * 
     * @param id of the element
     * @return the builder
     */
    CaseBuilder<X> setBorder(String id);

    /**
     * the build method.
     * 
     * @return the variable part of the case.
     * @throws IllegalStateException if the build method has already been called
     */
    X build();
}
