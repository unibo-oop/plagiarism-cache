package it.lttply.model.builders;

/**
 * It provides a interface of a basic generic builder.
 * 
 * @param <X>
 */
public interface Builder<X> {
    /**
     * It concretely builds the object.
     * 
     * @return the built <X> object
     */
    X build();

    /**
     * It builds the object using another object (it makes a defensive copy).
     * 
     * @param source
     *            the source object used to build the defensive copy of source
     *            itself. All the parameters already setted will be overwritten.
     * @return the built <X> object (defensive copy)
     */
    X buildFrom(X source);
}
