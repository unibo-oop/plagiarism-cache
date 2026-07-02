package clashclass.ecs;



/**
 * Represents the base class of a Component.
 */
public abstract class AbstractComponent implements Component {
    private GameObject gameObject;

    /**
     * {@inheritDoc}
     */
    @Override
        public final GameObject getGameObject() {
        return this.gameObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
        public final void setGameObject(final GameObject gameObject) {
        if (this.gameObject == null) {
            this.gameObject = gameObject;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {

    }
}
