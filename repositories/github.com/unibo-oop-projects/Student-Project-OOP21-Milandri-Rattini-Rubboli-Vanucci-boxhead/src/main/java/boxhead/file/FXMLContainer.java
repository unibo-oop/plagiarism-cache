package boxhead.file;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Wrapper.
 */
public class FXMLContainer {
	private static final String FXML_DIRECTORY = "/fxml/";
    private static final String DEFAULT_NAME = "default";
    private static final String FILE_EXTENSION = ".fxml";

    private String name;
    private Scene scene;
    private Initializable controller;
    private Parent root;

    /**
     * Empty constructor for FXML stuff.
     */
    public FXMLContainer() {
        this(DEFAULT_NAME);
    }

    /**
     * Constructor not empty for FXML.
     * @param name
     *          URL for FXML file.
     */
    public FXMLContainer(final String name) {
        this.name = name;
        final FXMLLoader fxml = new FXMLLoader();
        try {
            fxml.setLocation(getClass().getResource(FXML_DIRECTORY + name + FILE_EXTENSION));
            this.scene = new Scene(fxml.load());
            this.controller = fxml.getController();
            this.root = fxml.getRoot();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * @return
     *          Scene associated to the FXML file.
     */
    public final Scene getScene() {
        return this.scene;
    }

    /**
     * @return
     *          FXML controller.
     */
    public final Initializable getController() {
        return this.controller;
    }

    /**
     * @return
     *          Root of the scene.
     */
    public final Parent getRoot() {
        return this.root;
    }

    /**
     *  @return
     *          URL of the FXML file.
     */
    public final String getName() {
        return this.name;
    }
}
