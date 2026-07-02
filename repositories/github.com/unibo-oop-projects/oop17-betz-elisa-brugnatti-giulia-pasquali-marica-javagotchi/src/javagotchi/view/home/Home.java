package javagotchi.view.home;

import java.io.IOException;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javagotchi.controller.home.DeathException;
import javagotchi.controller.home.HomeController;
import javagotchi.model.information.Avatar;
import javagotchi.model.information.Gender;
import javagotchi.model.needs.AbstractNeed;

/**
 * Class that implements the home view.
 * @author elisa
 *
 */
public class Home implements GameView {

    private static final String RED = "-fx-accent: #e42929;";
    private static final String PINK = "-fx-accent: #d367a4;";
    private static final String BLUE = "-fx-accent: #5863b5;";
    private static final String ORANGE = "-fx-accent: #eaa83d;";
    private static final String PINK_BACKGROUND = "-fx-background-color: #fbf2f7;";
    private static final String BLUE_BACKGROUND = "-fx-background-color: #edf2ff;";
    private static final String ORANGE_BACKGROUND = "-fx-background-color: #fff8ee;";

    private Stage stage; 
    @FXML
    private Pane root;
    @FXML
    private ImageView genderImg;
    @FXML
    private ImageView avatarImg;
    @FXML
    private Button playBtn;
    @FXML
    private Button cureBtn;
    @FXML
    private Button cleanBtn;
    @FXML
    private Button sleepBtn;
    @FXML
    private Button feedBtn;
    @FXML
    private Button scoldBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button tutorialBtn;
    @FXML
    private ProgressBar cleanlinessBar;
    @FXML
    private ProgressBar hungerBar;
    @FXML
    private ProgressBar happinessBar;
    @FXML
    private ProgressBar energyBar;
    @FXML
    private ProgressBar healthBar;
    @FXML
    private ProgressBar disciplineBar;
    @FXML
    private Label nameLabel;
    @FXML
    private Label ageLabel;

    private String defaultColour;

    private final HomeController homecontroller;

    /** 
     * Home view constructor.
     * @param homecontroller the home controller
     */
    public Home(final HomeController homecontroller) {
        this.homecontroller = homecontroller;
    }

    /**
     * Method linked to the play button.
     */
    @FXML 
    protected final void play() {
        this.homecontroller.playHandler();
        this.updateBars();
    }

    /**
     * Method linked to the cure button.
     */
    @FXML 
    protected final void cure() {
        this.homecontroller.getJavagotchi().cure();
        this.updateBars();
    }

    /**
     * Method linked to the clean button.
     */
    @FXML 
    protected final void clean() {
        this.homecontroller.getJavagotchi().clean();
        this.updateBars();
    }

    /**
     * Method linked to the sleep button.
     */
    @FXML 
    protected final void sleep() {
        this.homecontroller.getJavagotchi().sleep();
        this.updateBars();
    }

    /**
     * Method linked to the feed button.
     */
    @FXML 
    protected final void feed() {
        this.homecontroller.getJavagotchi().feed();
        this.updateBars();
    }

    /**
     * Method linked to the scold button.
     */
    @FXML 
    protected final void scold() {
        this.homecontroller.getJavagotchi().scold();
        this.updateBars();
    }

    /**
     * Method linked to the back button.
     */
    @FXML 
    protected final void back() {
        this.homecontroller.backHandler(this.stage);
    }

    /**
     * Method linked to the tutorial button.
     */
    @FXML 
    protected final void showTutorial() {
        this.homecontroller.tutorialHandler();
    }

    /** 
     * Method to set the FXML Loader.
     * @param loader the FXML Loader.
     */
    public void setFXMLLoader(final FXMLLoader loader) {
        loader.setLocation(this.getClass().getResource("/home.fxml"));
        loader.setController(this);
        try {
            this.root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method to set the stage and the scene.
     */
    public void setStageAndScene() {
        this.stage = new Stage();
        final Scene scene = new Scene(this.root);
        stage.setScene(scene);
        this.stage.setOnCloseRequest(e -> {
            this.homecontroller.getTimers().stop();
            this.homecontroller.save();
            System.exit(0);
        });
        this.stage.setResizable(false);
    }


    private void setJavagotchiName() {
        this.nameLabel.setText(this.homecontroller.getJavagotchi().getInformation().getName().toUpperCase(Locale.ROOT));
    }


    private void setAvatarAppearance() {
        final Avatar avatar = this.homecontroller.getJavagotchi().getInformation().getAvatar();
        final Image image;

        if (avatar == Avatar.CAT) {
            image = new Image(this.getClass().getResource("/homecat.gif").toExternalForm());
            this.defaultColour = PINK;
            root.setStyle(PINK_BACKGROUND);
        } else if (avatar == Avatar.FOX) {
            image = new Image(this.getClass().getResource("/homefox.gif").toExternalForm());
            this.defaultColour = ORANGE;
            root.setStyle(ORANGE_BACKGROUND);
        } else {
            image = new Image(this.getClass().getResource("/homepanda.gif").toExternalForm());
            this.defaultColour = BLUE;
            root.setStyle(BLUE_BACKGROUND);
        }

        this.avatarImg.setImage(image);
    }


    private void setGenderImage() {
        final Gender gender = this.homecontroller.getJavagotchi().getInformation().getGender();
        final Image image;
        if (gender == Gender.FEMALE) {
            image = new Image(this.getClass().getResource("/female.png").toExternalForm());
        } else {
            image = new Image(this.getClass().getResource("/male.png").toExternalForm());
        }
        this.genderImg.setImage(image);
    }


    private void setButtonsGraphic() {
        this.feedBtn.setGraphic(new ImageView(this.getClass().getResource("/feed.png").toString()));
        this.feedBtn.setTooltip(new Tooltip("Feed"));

        this.scoldBtn.setGraphic(new ImageView(this.getClass().getResource("/scold.png").toString()));
        this.scoldBtn.setTooltip(new Tooltip("Scold"));

        this.playBtn.setGraphic(new ImageView(this.getClass().getResource("/play.png").toString()));
        this.playBtn.setTooltip(new Tooltip("Play"));

        this.cleanBtn.setGraphic(new ImageView(this.getClass().getResource("/clean.png").toString()));
        this.cleanBtn.setTooltip(new Tooltip("Clean"));

        this.cureBtn.setGraphic(new ImageView(this.getClass().getResource("/cure.png").toString()));
        this.cureBtn.setTooltip(new Tooltip("Cure"));

        this.sleepBtn.setGraphic(new ImageView(this.getClass().getResource("/sleep.png").toString()));
        this.sleepBtn.setTooltip(new Tooltip("Sleep"));

        this.tutorialBtn.setGraphic(new ImageView(this.getClass().getResource("/tutorial.gif").toString()));
        this.tutorialBtn.setTooltip(new Tooltip("Tutorial"));
    }


    /**
     * Method to update the age label on the view according to the current age of the javagotchi.
     */
    public void updateAge() {
        ageLabel.setText(this.homecontroller.getJavagotchi().getInformation().getAge().toString());
    }


    private void updateBar(final ProgressBar bar, final double level) {
        bar.setProgress(level);
        if (level <= AbstractNeed.MIN_LEVEL) {
            bar.setStyle(RED);
        } else {
            bar.setStyle(defaultColour);
        }
    }

    /**
     * Method to update the needs progress bars. 
     */
    public final void updateBars() {
        this.updateBar(this.cleanlinessBar, this.homecontroller.getJavagotchi().getNeeds().getCleanliness().getLevel());
        this.updateBar(this.disciplineBar, this.homecontroller.getJavagotchi().getNeeds().getDiscipline().getLevel());
        this.updateBar(this.energyBar, this.homecontroller.getJavagotchi().getNeeds().getEnergy().getLevel());
        this.updateBar(this.happinessBar, this.homecontroller.getJavagotchi().getNeeds().getHappiness().getLevel());
        this.updateBar(this.healthBar, this.homecontroller.getJavagotchi().getNeeds().getHealth().getLevel());
        this.updateBar(this.hungerBar, this.homecontroller.getJavagotchi().getNeeds().getHunger().getLevel());

        try {
            this.homecontroller.checkLiveness();
        } catch (DeathException deathExc) {
            deathExc.manage();
        }
    }



    /**
     * Method to initialize the home view.
     */
    public void init() {
        this.setFXMLLoader(new FXMLLoader());
        this.setStageAndScene();

        this.setButtonsGraphic();
        this.setJavagotchiName();
        this.setGenderImage();
        this.setAvatarAppearance();

        this.updateAge();
        this.updateBars();

        this.homecontroller.startTimers(this);
    }


    /**
     * Method to show the home view.
     * 
     */
    public final void show() {
        this.init();
        this.stage.show();
    }
}

