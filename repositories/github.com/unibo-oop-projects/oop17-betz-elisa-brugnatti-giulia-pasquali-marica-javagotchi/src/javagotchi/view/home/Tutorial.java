package javagotchi.view.home;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javagotchi.controller.home.Timers;

/**
 * Class that implements the tutorial view.
 * @author elisa
 *
 */
public class Tutorial implements GameView {

    private static final int LAST_PAGE = 6;
    private static final int FIRST_PAGE = 1;
    private Stage stage; 
    private final Timers timers;
    private int currentPage;

    @FXML
    private Pane pane;
    @FXML
    private Button nextBtn;
    @FXML
    private Button backToGameBtn;
    @FXML
    private ImageView tutorialImg;

    /**
     * Tutorial controller constructor.
     * @param timers  home controller timers
     */
    public Tutorial(final Timers timers) {
        this.timers = timers;
        this.currentPage = FIRST_PAGE;
    }

    /** 
     * Method to set the FXML Loader.
     * @param loader the FXML Loader.
     */
    public void setFXMLLoader(final FXMLLoader loader) {
        loader.setLocation(getClass().getResource("/tutorial.fxml"));
        loader.setController(this);
        try {
            this.pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to set the stage and the scene.
     */
    public void setStageAndScene() {
        this.stage = new Stage();
        final Scene scene = new Scene(pane);
        this.stage.setScene(scene);
        this.stage.setOnCloseRequest(e -> this.back());
        this.stage.setResizable(false);
    }

    /**
     * Method linked to the back button.
     */
    @FXML
    public final void back() {
        this.stage.close();
        this.timers.start();
    }

    /**
     * Method linked to the next-page button.
     */
    @FXML
    protected void nextPage() {
        currentPage++;
        final Image image = new Image(getClass().getResource("/tutorial" + currentPage + ".png").toExternalForm());
        this.tutorialImg.setImage(image);
        if (currentPage == LAST_PAGE) {
            this.nextBtn.setDisable(true);
        }
    }


    /**
     * Method to initialize the view.
     */
    public void init() {
        this.setFXMLLoader(new FXMLLoader());
        this.setStageAndScene();
        this.nextBtn.setGraphic(new ImageView(this.getClass().getResource("/arrow.png").toString()));
        this.timers.stop();
    }


    /**
     * Method to show the tutorial view.
     */
    public final void show() {
        this.init();
        this.stage.show();
    }
}
