package clashclass.view.graphic;

/**
 * Abstract class for all windows in the application.
 */
public abstract class AbstractWindow implements Window {
    /**
     * {@inheritDoc}
     */
    @Override
    public void launchWindow() {
        final var window = this.getGraphicSceneFactory().createPlayerVillageScene(this);
        window.initializeScene();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseScene createMenuScene() {
        return this.getGraphicSceneFactory().createMenuScene(this);
    }

    /**
     * Provides the graphic scene factory for creating scenes.
     *
     * @return the GraphicSceneFactory
     */
    protected abstract GraphicSceneFactory getGraphicSceneFactory();
}
