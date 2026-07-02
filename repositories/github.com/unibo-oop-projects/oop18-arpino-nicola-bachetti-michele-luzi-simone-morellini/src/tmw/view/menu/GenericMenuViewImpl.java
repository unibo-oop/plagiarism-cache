package tmw.view.menu;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;

/**
 * Abstract class to implements the features and methods common to all the
 * scenes of the menu.
 */
public class GenericMenuViewImpl implements GenericMenuView {

    private Scene scene;
    private FXMLLoader loader;
    private static final int MIN_WIDHT = 450;
    private static final int MIN_HEIGHT = 450;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        try {
            this.scene = new Scene(this.loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Method to set the FXMLLoader of the view element.
     * 
     * @param loader The FXMLLoader of the view element
     */
    protected void setLoader(final FXMLLoader loader) {
        this.loader = loader;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FXMLLoader getLoader() {
        return this.loader;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension2D getMinSize() {
        return new Dimension2D(MIN_WIDHT, MIN_HEIGHT);
    }
}

