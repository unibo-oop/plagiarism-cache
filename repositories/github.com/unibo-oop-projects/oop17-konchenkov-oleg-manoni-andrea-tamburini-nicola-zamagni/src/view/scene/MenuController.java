package view.scene;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Andrea Manoni
 *
 */
public class MenuController extends AbstractController {
    private static final String PANETOT = "paneTot";
    private SceneMainController mainController;
    private final ObservableList<String> resList = FXCollections.observableArrayList();
    private final List<Node> visibleList = new ArrayList<>();
    private URL imgURL;

    private Image quiton;

    private Image quitoff;

    private Image optionon;

    private Image optionoff;

    private Image newgameon;

    private Image newgameoff;

    private Image saveoptionon;

    private Image saveoptionoff;

    private Image quitoptionon;

    private Image quitoptionoff;

    private Image demooff;

    private Image demoon;
    @FXML
    private ImageView newGameButton;
    @FXML
    private ImageView optionButton;
    @FXML
    private ImageView quitButton;
    @FXML
    private ImageView demoButton;
    @FXML
    private ImageView backgroundImage;
    @FXML
    private ImageView saveOptionButton;
    @FXML
    private ImageView quitOptionButton;
    @FXML
    private Button newGame;
    @FXML
    private Button options;
    @FXML
    private Button quit;
    @FXML
    private Button demo;
    @FXML
    private Button saveOption;
    @FXML
    private Button quitOption;
    @FXML
    private ComboBox<String> optionCombo;
    @FXML
    private Slider soundSlider;
    @FXML
    private Slider musicSlider;
    @FXML
    private Label lblChangeSound;
    @FXML
    private Label lblChangeMusic;

    /* DIALOG */
    @FXML
    private Pane paneDialog;

    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;
    @FXML
    private ImageView yesButton;
    @FXML
    private ImageView noButton;
    @FXML
    private Rectangle rectExitScreen;
    /* END DIALOG */

    /**
     *
     * @param mainController
     *            mainController
     */
    @Override
    public final void setMainApp(final SceneMainController mainController) {
        this.mainController = mainController;
        newGame = (Button) getNodeNested(mainController, "newGame", PANETOT);
        options = (Button) getNodeNested(mainController, "Options", PANETOT);
        quit = (Button) getNodeNested(mainController, "Quit", PANETOT);
        demo = (Button) getNodeNested(mainController, "Demo", PANETOT);
        newGameButton = (ImageView) getNodeNested(mainController, "newGameButton", PANETOT);
        optionButton = (ImageView) getNodeNested(mainController, "optionButton", PANETOT);
        quitButton = (ImageView) getNodeNested(mainController, "quitButton", PANETOT);
        demoButton = (ImageView) getNodeNested(mainController, "demoButton", PANETOT);
        backgroundImage = (ImageView) getNode(mainController, "backgroundImage");
        saveOptionButton = (ImageView) getNodeNested(mainController, "saveOptionButton", PANETOT);
        quitOptionButton = (ImageView) getNodeNested(mainController, "quitOptionButton", PANETOT);
        saveOption = (Button) getNodeNested(mainController, "SaveOption", PANETOT);
        quitOption = (Button) getNodeNested(mainController, "QuitOption", PANETOT);
        lblChangeSound = (Label) getNodeNested(mainController, "lblChangeSound", PANETOT);
        lblChangeMusic = (Label) getNodeNested(mainController, "lblChangeMusic", PANETOT);
        musicSlider = (Slider) getNodeNested(mainController, "musicSlider", PANETOT);
        soundSlider = (Slider) getNodeNested(mainController, "soundSlider", PANETOT);

        setVisibleList();
        setImages();
        setListener();
        setDialog();
    }

    private void setVisibleList() {

        visibleList.add(getNodeNested(mainController, "optionRect", PANETOT));
        visibleList.add(getNodeNested(mainController, "resolutionRect", PANETOT));
        visibleList.add(getNodeNested(mainController, "musicRect", PANETOT));
        visibleList.add(getNodeNested(mainController, "soundRect", PANETOT));

        visibleList.add(getNodeNested(mainController, "lblRes", PANETOT));
        visibleList.add(getNodeNested(mainController, "lblMusic", PANETOT));
        visibleList.add(getNodeNested(mainController, "lblSound", PANETOT));
        visibleList.add(lblChangeSound);
        visibleList.add(lblChangeMusic);
        visibleList.add(optionCombo);
        visibleList.add(soundSlider);
        visibleList.add(musicSlider);
        visibleList.add(saveOption);
        visibleList.add(quitOption);
        visibleList.add(saveOptionButton);
        visibleList.add(quitOptionButton);
        visibleList.add(getNode(mainController, "screenBackGround"));
        visibleList.add(getNodeNested(mainController, "checkFull", PANETOT));
        optionCombo.setStyle("-fx-text-fill: white;  -fx-background-color: black;");
        optionCombo.setCellFactory(param -> {
            final ListCell<String> cell = new ListCell<String>() {

                @Override
                public void updateItem(final String item, final boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setText(item);
                        setTextFill(Color.WHITE);
                        setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
                    } else {
                        setText(null);
                    }
                }
            };
            return cell;
        });
        visibleList.forEach(elem -> elem.setVisible(false));
    }

    private void setImages() {
        imgURL = MenuController.class.getResource("/menu_newgame_on.png");
        newgameon = new Image(imgURL.toString());
        imgURL = MenuController.class.getResource("/menu_newgame_off.png");
        newgameoff = new Image(imgURL.toString());
        imgURL = MenuController.class.getResource("/menu_options_off.png");
        optionoff = new Image(imgURL.toString());
        imgURL = MenuController.class.getResource("/menu_options_on.png");
        optionon = new Image(imgURL.toString());
        imgURL = MenuController.class.getResource("/menu_quit_on.png");
        quiton = new Image(imgURL.toString());
        imgURL = MenuController.class.getResource("/menu_quit_off.png");
        quitoff = new Image(imgURL.toString());
        imgURL = MenuController.class.getResource("/menu_demo_on.png");
        demoon = new Image(imgURL.toString());
        imgURL = MenuController.class.getResource("/menu_demo_off.png");
        demooff = new Image(imgURL.toString());
        imgURL = MenuController.class.getResource("/menu_options_apply_on.png");
        saveoptionon = new Image(imgURL.toString());
        imgURL = MenuController.class.getResource("/menu_options_apply_off.png");
        saveoptionoff = new Image(imgURL.toString());
        imgURL = MenuController.class.getResource("/menu_options_back_on.png");
        quitoptionon = new Image(imgURL.toString());
        imgURL = MenuController.class.getResource("/menu_options_back_off.png");
        quitoptionoff = new Image(imgURL.toString());

        newGameButton.setImage(newgameoff);
        optionButton.setImage(optionoff);
        quitButton.setImage(quitoff);
        demoButton.setImage(demooff);
        imgURL = MenuController.class.getResource("/background.png");
        backgroundImage.setImage(new Image(imgURL.toString()));
        saveOptionButton.setImage(saveoptionoff);
        quitOptionButton.setImage(quitoptionoff);
    }

    private void setListener() {

        newGame.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> newGameButton.setImage(newgameon));
        newGame.addEventFilter(MouseEvent.MOUSE_EXITED, e -> newGameButton.setImage(newgameoff));
        newGame.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> actionNewGame());
        demo.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> demoButton.setImage(demoon));
        demo.addEventFilter(MouseEvent.MOUSE_EXITED, e -> demoButton.setImage(demooff));
        demo.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> actionDemo());
        options.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> optionButton.setImage(optionon));
        options.addEventFilter(MouseEvent.MOUSE_EXITED, e -> optionButton.setImage(optionoff));
        options.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> actionOption());
        quit.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> quitButton.setImage(quiton));
        quit.addEventFilter(MouseEvent.MOUSE_EXITED, e -> quitButton.setImage(quitoff));
        quit.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> actionQuit());
        saveOption.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> saveOptionButton.setImage(saveoptionon));
        saveOption.addEventFilter(MouseEvent.MOUSE_EXITED, e -> saveOptionButton.setImage(saveoptionoff));
        saveOption.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> actionOptionSave());
        quitOption.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> quitOptionButton.setImage(quitoptionon));
        quitOption.addEventFilter(MouseEvent.MOUSE_EXITED, e -> quitOptionButton.setImage(quitoptionoff));
        quitOption.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> actionOptionQuit());

        musicSlider.addEventFilter(MouseEvent.ANY, e -> {
            changedMusic();
        });
        musicSlider.addEventFilter(KeyEvent.ANY, e -> {
            changedMusic();
        });
        soundSlider.addEventFilter(MouseEvent.ANY, e -> {
            changedSound();
        });
        soundSlider.addEventFilter(KeyEvent.ANY, e -> {
            changedSound();
        });
        mainController.getPrimaryStage().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                if (paneDialog.isVisible()) {
                    closeDialog();
                    e.consume();

                } else {
                    openDialog();
                    e.consume();

                }
            }
        });
        mainController.getPrimaryStage().setOnCloseRequest(e -> {
            openDialog();
            e.consume();
        });
    }

    @FXML
    private void changedMusic() {
        lblChangeMusic.setText(String.valueOf((int) Math.floor(musicSlider.getValue())));
    }

    @FXML
    private void changedSound() {
        lblChangeSound.setText(String.valueOf((int) Math.floor(soundSlider.getValue())));
    }

    @FXML
    private void actionNewGame() {
        mainController.sceneNewGame();
    }

    @FXML
    private void actionDemo() {
        mainController.sceneDemo();
    }

    @FXML
    private void actionOption() {
        mainController.getResolutionList().stream().forEach(r -> resList.add(r.getName()));
        resList.subList(0, resList.size() / 2).clear();
        visibleList.forEach(elem -> elem.setVisible(true));
        newGame.setDisable(true);
        options.setDisable(true);
        quit.setDisable(true);
        demo.setDisable(true);
        optionCombo.setItems(resList);
        optionCombo.getSelectionModel().select(mainController.getResolution().getName());
        ((Rectangle) getNode(mainController, "screenBackGround")).setVisible(true);
        ((CheckBox) getNodeNested(mainController, "checkFull", PANETOT)).setSelected(mainController.isFullScreen());
        musicSlider.setValue(mainController.getSoundManager().getMusicVolume() * 100);
        soundSlider.setValue(mainController.getSoundManager().getSoundVolume() * 100);
        lblChangeSound
                .setText(String.valueOf((int) Math.floor(mainController.getSoundManager().getSoundVolume() * 100)));
        lblChangeMusic
                .setText(String.valueOf((int) Math.floor(mainController.getSoundManager().getMusicVolume() * 100)));
    }

    @FXML
    private void actionOptionSave() {
        visibleList.forEach(elem -> elem.setVisible(false));
        newGame.setDisable(false);
        options.setDisable(false);
        quit.setDisable(false);
        demo.setDisable(false);
        mainController.changeOptions(optionCombo.getSelectionModel().getSelectedItem(),
                ((CheckBox) getNodeNested(mainController, "checkFull", PANETOT)).isSelected() ? true : false,
                Math.floor(musicSlider.getValue()), Math.floor(soundSlider.getValue()));
    }

    @FXML
    private void actionOptionQuit() {
        visibleList.forEach(elem -> elem.setVisible(false));
        newGame.setDisable(false);
        options.setDisable(false);
        quit.setDisable(false);
        demo.setDisable(false);
        ((Rectangle) getNode(mainController, "screenBackGround")).setVisible(false);
    }

    @FXML
    private void actionQuit() {
        openDialog();
    }

    /* DIALOG */
    /**
     * Describes the action of pressing the button "Yes".
     */
    @FXML
    public void actionYesDialog() {
        mainController.closePrimaryStage();
    }

    /**
     * Describes the action of pressing the button "No".
     */
    @FXML
    public void actionNoDialog() {
        paneDialog.setVisible(false);
        rectExitScreen.setVisible(false);
    }

    private void setImagesDialog() {
        imgURL = MenuController.class.getResource("/dialog_yes_off.png");
        yesButton.setImage(new Image(imgURL.toString()));
        imgURL = MenuController.class.getResource("/dialog_no_off.png");
        noButton.setImage(new Image(imgURL.toString()));
    }

    private void setListenerDialog() {
        btnYes.setOnAction(event -> actionYesDialog());
        btnNo.setOnAction(event -> actionNoDialog());
        btnYes.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = MenuController.class.getResource("/dialog_yes_on.png");
            yesButton.setImage(new Image(imgURL.toString()));
        });
        btnYes.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = MenuController.class.getResource("/dialog_yes_off.png");
            yesButton.setImage(new Image(imgURL.toString()));
        });
        btnNo.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = MenuController.class.getResource("/dialog_no_on.png");
            noButton.setImage(new Image(imgURL.toString()));
        });
        btnNo.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = MenuController.class.getResource("/dialog_no_off.png");
            noButton.setImage(new Image(imgURL.toString()));
        });
    }

    private void setDialog() {
        setImagesDialog();
        setListenerDialog();
    }

    private void openDialog() {
        paneDialog.setVisible(true);
        rectExitScreen.setVisible(true);
    }

    private void closeDialog() {
        paneDialog.setVisible(false);
        rectExitScreen.setVisible(false);
    }
}
