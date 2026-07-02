package zombieversity.file;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;


/**
 * Class wrapper.
 *
 */
public class FXMLContainer {
    private static final String FXML_DIR = "/fxml/";
    private static final String DEF_NAME = "default";
    private static final String FILE_EXT = ".fxml";

    private String name;
    private Scene scene;
    private Initializable controller;
    private Parent root;

    /**
     * Empty constructor for FXML stuff.
     */
    public FXMLContainer() {
        this(DEF_NAME);
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
            fxml.setLocation(getClass().getResource(FXML_DIR + name + FILE_EXT));
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
