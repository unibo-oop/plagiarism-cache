package util.mapbuilder;

/**
 *a factory of game map builders for the view.
 *@param <X> the type of the of the returned Map
 */
public interface GameMapBuilderFactory<X> {

    /**
     * 
     * @return a game map builder.
     */
    GameMapBuilder<X> get();
}
