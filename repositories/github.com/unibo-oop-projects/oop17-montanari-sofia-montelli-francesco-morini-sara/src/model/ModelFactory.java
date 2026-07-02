package model;

/**
 * A static factory of {@link ModelImpl}.
 */
public final class ModelFactory {

    private ModelFactory() { }
    /**
     * @return a interactive version of the model
     */
    public static Model getModelInteract() {
        return ModelImpl.getModel();
    }

    /**
     * @return a read-only version of the model.
     */
    public static ImmutableModel getModelShow() {
        return ModelImpl.getModel();
    }
}
