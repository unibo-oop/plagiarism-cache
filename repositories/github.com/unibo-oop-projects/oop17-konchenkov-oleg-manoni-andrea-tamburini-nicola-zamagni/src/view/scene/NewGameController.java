package view.scene;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
import javafx.util.Pair;
import model.battlefield.BattlefieldComponent;
import model.battlefield.Fluid;
import model.battlefield.Location;

/**
 *
 * Controller for the Scene NewGame.fxml.
 *
 * @author Andrea Manoni
 *
 */
public class NewGameController extends AbstractController {

    private static final String PANETOT = "paneTot";
    private SceneMainController mainController;
    private Location selectedLocation;
    private Fluid selectedFluid;
    private long seedSelected;
    private boolean fluidStationary;

    private final List<ColorType> colorList = new ArrayList<>();
    private ObservableList<ColorType> colorObsList;
    private final ObservableList<String> selectedColourList = FXCollections.observableArrayList();
    private final List<Pair<String, Color>> playersList = new ArrayList<>();
    private final List<Label> labNameList = new ArrayList<>();
    private final List<Label> labColourList = new ArrayList<>();
    private final List<TextField> textList = new ArrayList<>();
    private final List<ComboBox<ColorType>> comboList = new ArrayList<>();
    private final List<Button> lessList = new ArrayList<>();
    private final List<Button> plusList = new ArrayList<>();
    private final List<ImageView> tickList = new ArrayList<>();
    private final List<ImageView> plusImageList = new ArrayList<>();
    private final List<ImageView> lessImageList = new ArrayList<>();
    private final List<Rectangle> recBlockList = new ArrayList<>();

    private List<BattlefieldComponent> locationList;
    private final ObservableList<String> comboLocationList = FXCollections.observableArrayList();
    private ObservableList<String> comboFluidList;
    private URL imgURL;

    @FXML
    private ComboBox<ColorType> combo1;
    @FXML
    private ComboBox<ColorType> combo2;
    @FXML
    private ComboBox<ColorType> combo3;
    @FXML
    private ComboBox<ColorType> combo4;
    @FXML
    private ComboBox<ColorType> combo5;
    @FXML
    private ComboBox<ColorType> combo6;
    @FXML
    private ComboBox<ColorType> combo7;
    @FXML
    private ComboBox<ColorType> combo8;
    @FXML
    private ComboBox<ColorType> combo9;
    @FXML
    private ComboBox<ColorType> combo10;
    @FXML
    private ComboBox<String> comboFluid;
    @FXML
    private ComboBox<String> comboLocation;
    @FXML
    private Rectangle recPlayers;
    @FXML
    private Rectangle recData;
    @FXML
    private TextField textSeed;

    private ImageView quitButton;

    private ImageView startButton;

    private Button buttonStart;

    private Button buttonQuit;
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

    @Override
    public final void setMainApp(final SceneMainController mainController) {
        this.mainController = mainController;
        recPlayers = (Rectangle) getNodeNested(mainController, "recPlayers", PANETOT);
        recData = (Rectangle) getNodeNested(mainController, "recData", PANETOT);
        quitButton = (ImageView) getNodeNested(mainController, "quitButton", PANETOT);
        startButton = (ImageView) getNodeNested(mainController, "startButton", PANETOT);
        buttonQuit = (Button) getNodeNested(mainController, "buttonQuit", PANETOT);
        buttonStart = (Button) getNodeNested(mainController, "buttonStart", PANETOT);
        textSeed = (TextField) getNodeNested(mainController, "textSeed", PANETOT);
        setLists();
        setImageViews();
        setImages();
        setComboBoxFirst();
        setListener();
        setRadioButtons();
        setDialog();
    }

    private void setImageViews() {
        tickList.add((ImageView) getNodeNested(mainController, "tick1", PANETOT));
        tickList.add((ImageView) getNodeNested(mainController, "tick2", PANETOT));
        tickList.add((ImageView) getNodeNested(mainController, "tick3", PANETOT));
        tickList.add((ImageView) getNodeNested(mainController, "tick4", PANETOT));
        tickList.add((ImageView) getNodeNested(mainController, "tick5", PANETOT));
        tickList.add((ImageView) getNodeNested(mainController, "tick6", PANETOT));
        tickList.add((ImageView) getNodeNested(mainController, "tick7", PANETOT));
        tickList.add((ImageView) getNodeNested(mainController, "tick8", PANETOT));
        tickList.add((ImageView) getNodeNested(mainController, "tick9", PANETOT));
        tickList.add((ImageView) getNodeNested(mainController, "tick10", PANETOT));
        plusImageList.add((ImageView) getNodeNested(mainController, "plusImage3", PANETOT));
        plusImageList.add((ImageView) getNodeNested(mainController, "plusImage4", PANETOT));
        plusImageList.add((ImageView) getNodeNested(mainController, "plusImage5", PANETOT));
        plusImageList.add((ImageView) getNodeNested(mainController, "plusImage6", PANETOT));
        plusImageList.add((ImageView) getNodeNested(mainController, "plusImage7", PANETOT));
        plusImageList.add((ImageView) getNodeNested(mainController, "plusImage8", PANETOT));
        plusImageList.add((ImageView) getNodeNested(mainController, "plusImage9", PANETOT));
        plusImageList.add((ImageView) getNodeNested(mainController, "plusImage10", PANETOT));
        lessImageList.add((ImageView) getNodeNested(mainController, "lessImage3", PANETOT));
        lessImageList.add((ImageView) getNodeNested(mainController, "lessImage4", PANETOT));
        lessImageList.add((ImageView) getNodeNested(mainController, "lessImage5", PANETOT));
        lessImageList.add((ImageView) getNodeNested(mainController, "lessImage6", PANETOT));
        lessImageList.add((ImageView) getNodeNested(mainController, "lessImage7", PANETOT));
        lessImageList.add((ImageView) getNodeNested(mainController, "lessImage8", PANETOT));
        lessImageList.add((ImageView) getNodeNested(mainController, "lessImage9", PANETOT));
        lessImageList.add((ImageView) getNodeNested(mainController, "lessImage10", PANETOT));
    }

    private void setLists() {
        colorList.add(ColorType.OLIVE);
        colorList.add(ColorType.GREEN);
        colorList.add(ColorType.TEAL);
        colorList.add(ColorType.BLUE);
        colorList.add(ColorType.CYAN);
        colorList.add(ColorType.VIOLET);
        colorList.add(ColorType.MAGENTA);
        colorList.add(ColorType.PINK);
        colorList.add(ColorType.RED);
        colorList.add(ColorType.YELLOW);
        colorObsList = FXCollections.observableList(new ArrayList<>(Collections.unmodifiableList(colorList)));
        labNameList.add((Label) getNodeNested(mainController, "labname3", PANETOT));
        labNameList.add((Label) getNodeNested(mainController, "labname4", PANETOT));
        labNameList.add((Label) getNodeNested(mainController, "labname5", PANETOT));
        labNameList.add((Label) getNodeNested(mainController, "labname6", PANETOT));
        labNameList.add((Label) getNodeNested(mainController, "labname7", PANETOT));
        labNameList.add((Label) getNodeNested(mainController, "labname8", PANETOT));
        labNameList.add((Label) getNodeNested(mainController, "labname9", PANETOT));
        labNameList.add((Label) getNodeNested(mainController, "labname10", PANETOT));
        labColourList.add((Label) getNodeNested(mainController, "labcol3", PANETOT));
        labColourList.add((Label) getNodeNested(mainController, "labcol4", PANETOT));
        labColourList.add((Label) getNodeNested(mainController, "labcol5", PANETOT));
        labColourList.add((Label) getNodeNested(mainController, "labcol6", PANETOT));
        labColourList.add((Label) getNodeNested(mainController, "labcol7", PANETOT));
        labColourList.add((Label) getNodeNested(mainController, "labcol8", PANETOT));
        labColourList.add((Label) getNodeNested(mainController, "labcol9", PANETOT));
        labColourList.add((Label) getNodeNested(mainController, "labcol10", PANETOT));
        textList.add((TextField) getNodeNested(mainController, "text1", PANETOT));
        textList.add((TextField) getNodeNested(mainController, "text2", PANETOT));
        textList.add((TextField) getNodeNested(mainController, "text3", PANETOT));
        textList.add((TextField) getNodeNested(mainController, "text4", PANETOT));
        textList.add((TextField) getNodeNested(mainController, "text5", PANETOT));
        textList.add((TextField) getNodeNested(mainController, "text6", PANETOT));
        textList.add((TextField) getNodeNested(mainController, "text7", PANETOT));
        textList.add((TextField) getNodeNested(mainController, "text8", PANETOT));
        textList.add((TextField) getNodeNested(mainController, "text9", PANETOT));
        textList.add((TextField) getNodeNested(mainController, "text10", PANETOT));
        recBlockList.add((Rectangle) getNodeNested(mainController, "recBlock1", PANETOT));
        recBlockList.add((Rectangle) getNodeNested(mainController, "recBlock2", PANETOT));
        recBlockList.add((Rectangle) getNodeNested(mainController, "recBlock3", PANETOT));
        recBlockList.add((Rectangle) getNodeNested(mainController, "recBlock4", PANETOT));
        recBlockList.add((Rectangle) getNodeNested(mainController, "recBlock5", PANETOT));
        recBlockList.add((Rectangle) getNodeNested(mainController, "recBlock6", PANETOT));
        recBlockList.add((Rectangle) getNodeNested(mainController, "recBlock7", PANETOT));
        recBlockList.add((Rectangle) getNodeNested(mainController, "recBlock8", PANETOT));
        recBlockList.add((Rectangle) getNodeNested(mainController, "recBlock9", PANETOT));
        recBlockList.add((Rectangle) getNodeNested(mainController, "recBlock10", PANETOT));
        comboList.add(combo1);
        comboList.add(combo2);
        comboList.add(combo3);
        comboList.add(combo4);
        comboList.add(combo5);
        comboList.add(combo6);
        comboList.add(combo7);
        comboList.add(combo8);
        comboList.add(combo9);
        comboList.add(combo10);
        lessList.add((Button) getNodeNested(mainController, "buttonless3", PANETOT));
        lessList.add((Button) getNodeNested(mainController, "buttonless4", PANETOT));
        lessList.add((Button) getNodeNested(mainController, "buttonless5", PANETOT));
        lessList.add((Button) getNodeNested(mainController, "buttonless6", PANETOT));
        lessList.add((Button) getNodeNested(mainController, "buttonless7", PANETOT));
        lessList.add((Button) getNodeNested(mainController, "buttonless8", PANETOT));
        lessList.add((Button) getNodeNested(mainController, "buttonless9", PANETOT));
        lessList.add((Button) getNodeNested(mainController, "buttonless10", PANETOT));
        plusList.add((Button) getNodeNested(mainController, "buttonplus3", PANETOT));
        plusList.add((Button) getNodeNested(mainController, "buttonplus4", PANETOT));
        plusList.add((Button) getNodeNested(mainController, "buttonplus5", PANETOT));
        plusList.add((Button) getNodeNested(mainController, "buttonplus6", PANETOT));
        plusList.add((Button) getNodeNested(mainController, "buttonplus7", PANETOT));
        plusList.add((Button) getNodeNested(mainController, "buttonplus8", PANETOT));
        plusList.add((Button) getNodeNested(mainController, "buttonplus9", PANETOT));
        plusList.add((Button) getNodeNested(mainController, "buttonplus10", PANETOT));
        textList.forEach(t -> t.setStyle("-fx-text-fill: white; -fx-font-size: 15;"));

    }

    private void changeVisibilityOn(final int index) {
        labNameList.get(index).setVisible(true);
        labColourList.get(index).setVisible(true);
        textList.get(index + 2).setVisible(true);
        comboList.get(index + 2).setVisible(true);
        lessList.get(index).setVisible(true);
        lessImageList.get(index).setVisible(true);
        plusList.get(index).setVisible(false);
        plusImageList.get(index).setVisible(false);
        if (index < plusList.size() - 1) {
            plusList.get(index + 1).setVisible(true);
            plusImageList.get(index + 1).setVisible(true);
        }
        if (index > 0) {
            lessList.get(index - 1).setVisible(false);
            lessImageList.get(index - 1).setVisible(false);
        }
    }

    private void changeVisibilityOff(final int index) {
        labNameList.get(index).setVisible(false);
        labColourList.get(index).setVisible(false);
        textList.get(index + 2).setVisible(false);
        textList.get(index + 2).deleteText(0, textList.get(index + 2).getLength());

        if (comboList.get(index + 2).getValue() != null) {
            selectedColourList.remove(comboList.get(index + 2).getValue().toString());
            comboList.get(index + 2).setVisible(false);
            comboList.get(index + 2).getItems().clear();
            comboList.get(index + 2).setDisable(false);
            comboList.get(index + 2).setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        } else {
            comboList.get(index + 2).setVisible(false);
        }

        lessList.get(index).setVisible(false);
        lessImageList.get(index).setVisible(false);
        plusList.get(index).setVisible(true);
        plusImageList.get(index).setVisible(true);
        if (index < plusList.size() - 1) {
            plusList.get(index + 1).setVisible(false);
            plusImageList.get(index + 1).setVisible(false);
        }
        if (index > 0) {
            lessList.get(index - 1).setVisible(true);
            lessImageList.get(index - 1).setVisible(true);
        }
    }

    private int indexPlus(final Button btn) {
        return plusList.indexOf(btn);
    }

    private int indexLess(final Button btn) {
        return lessList.indexOf(btn);
    }

    private void rechargeComboColor() {

        comboList.stream().filter(c -> !c.isDisable())
                .forEach(c -> c.setItems(FXCollections.observableArrayList(colorObsList)));

    }

    private void colorOnAction(final ComboBox<ColorType> combo) {
        for (final ComboBox<ColorType> c : comboList) {
            if (c.getValue() != null && !c.equals(combo)) {
                final String str = c.getSelectionModel().getSelectedItem().toString();
                final List<ColorType> color = colorList.stream()
                        .filter(col -> col.toString().equals(c.getSelectionModel().getSelectedItem().toString()))
                        .collect(Collectors.toList());
                colorObsList.removeAll(FXCollections.observableArrayList(color));
                recBlockList.get(comboList.indexOf(c)).setMouseTransparent(false);

                rechargeComboColor();
                colorList.stream().filter(col -> col.toString().equals(str)).forEach(col -> c.setValue(col));

                if (c.isFocused()) {
                    tickList.get(comboList.indexOf(c)).requestFocus();
                }

            }
        }
    }

    private void checkOk() {
        IntStream.range(0, textList.size())
                .filter(i -> (textList.get(i).getText().length() == 0 || comboList.get(i).getValue() == null))
                .forEach(elem -> tickList.get(elem).setVisible(false));
        IntStream.range(0, textList.size())
                .filter(i -> (textList.get(i).getText().length() != 0 && comboList.get(i).getValue() != null))
                .forEach(elem -> tickList.get(elem).setVisible(true));
        if (tickList.stream().filter(elem -> elem.isVisible()).count() >= 2 && comboLocation.getValue() != null
                && comboFluid.getValue() != null) {
            ((Button) getNodeNested(mainController, "buttonStart", PANETOT)).setDisable(false);
        } else {
            ((Button) getNodeNested(mainController, "buttonStart", PANETOT)).setDisable(true);
        }

    }

    private void changeComboFluid(final int locationIndex) {
        final List<BattlefieldComponent> fluidList = new ArrayList<>(
                ((Location) locationList.get(locationIndex)).getChild());
        comboFluidList = FXCollections.observableArrayList();
        fluidList.forEach(fluid -> comboFluidList.add(fluid.getName()));
        comboFluid.setItems(comboFluidList);
        comboFluid.setDisable(false);
    }

    private void setListener() {
        buttonQuit.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = NewGameController.class.getResource("/newgame_back_on.png");
            quitButton.setImage(new Image(imgURL.toString()));
        });
        buttonQuit.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = NewGameController.class.getResource("/newgame_back_off.png");
            quitButton.setImage(new Image(imgURL.toString()));
        });
        buttonQuit.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> actionBack());
        buttonStart.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = NewGameController.class.getResource("/newgame_start_on.png");
            startButton.setImage(new Image(imgURL.toString()));
        });
        buttonStart.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = NewGameController.class.getResource("/newgame_start_off.png");
            startButton.setImage(new Image(imgURL.toString()));
        });
        buttonStart.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> actionStart());
        recData.addEventFilter(MouseEvent.ANY, e -> {
            checkOk();
        });
        recPlayers.addEventHandler(MouseEvent.ANY, e -> {
            comboList.forEach(c -> {
                if (c.getSelectionModel().getSelectedItem() != null) {
                    switch (c.getSelectionModel().getSelectedItem()) {
                    case RED:
                        c.setBackground(new Background(new BackgroundFill(ColorType.RED.getColor(), null, null)));
                        break;
                    case GREEN:
                        c.setBackground(new Background(new BackgroundFill(ColorType.GREEN.getColor(), null, null)));
                        break;
                    case MAGENTA:
                        c.setBackground(new Background(new BackgroundFill(ColorType.MAGENTA.getColor(), null, null)));
                        break;
                    case BLUE:
                        c.setBackground(new Background(new BackgroundFill(ColorType.BLUE.getColor(), null, null)));
                        break;
                    case CYAN:
                        c.setBackground(new Background(new BackgroundFill(ColorType.CYAN.getColor(), null, null)));
                        break;
                    case YELLOW:
                        c.setBackground(new Background(new BackgroundFill(ColorType.YELLOW.getColor(), null, null)));
                        break;
                    case PINK:
                        c.setBackground(new Background(new BackgroundFill(ColorType.PINK.getColor(), null, null)));
                        break;
                    case TEAL:
                        c.setBackground(new Background(new BackgroundFill(ColorType.TEAL.getColor(), null, null)));
                        break;
                    case VIOLET:
                        c.setBackground(new Background(new BackgroundFill(ColorType.VIOLET.getColor(), null, null)));
                        break;
                    case OLIVE:
                        c.setBackground(new Background(new BackgroundFill(ColorType.OLIVE.getColor(), null, null)));
                        break;
                    default:
                        c.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
                        break;
                    }
                }
                checkOk();
                colorOnAction(c);
                rechargeComboColor();
            });
        });

        // Listener per i bottoni(+) per aggiungere un giocatore
        plusList.forEach(elem -> elem.addEventFilter(MouseEvent.MOUSE_CLICKED,
                e -> changeVisibilityOn(indexPlus((Button) e.getSource()))));
        plusList.forEach(elem -> elem.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = NewGameController.class.getResource("/newgame_plus_on.png");
            plusImageList.get(plusList.indexOf(elem)).setImage(new Image(imgURL.toString()));
        }));
        plusList.forEach(elem -> elem.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = NewGameController.class.getResource("/newgame_plus_off.png");
            plusImageList.get(plusList.indexOf(elem)).setImage(new Image(imgURL.toString()));
        }));
        // Listener per i bottoni(-) per togliere un giocatore
        lessList.forEach(elem -> elem.addEventFilter(MouseEvent.MOUSE_CLICKED,
                e -> changeVisibilityOff(indexLess((Button) e.getSource()))));
        lessList.forEach(elem -> elem.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = NewGameController.class.getResource("/newgame_minus_on.png");
            lessImageList.get(lessList.indexOf(elem)).setImage(new Image(imgURL.toString()));
        }));
        lessList.forEach(elem -> elem.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = NewGameController.class.getResource("/newgame_minus_off.png");
            lessImageList.get(lessList.indexOf(elem)).setImage(new Image(imgURL.toString()));
        }));

        textList.forEach(t -> t.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null))));
        textList.forEach(elem -> elem.setOnKeyReleased(e -> checkOk()));
        textList.forEach(elem -> elem.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> checkOk()));

        comboLocation.setOnAction(e -> {
            changeComboFluid(comboLocation.getSelectionModel().getSelectedIndex());
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

    private void setComboBoxFirst() {
        recBlockList.forEach(r -> r.setMouseTransparent(true));
        comboList.forEach(c -> c.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null))));
        comboList.forEach(elem -> elem.setItems(FXCollections.observableArrayList(colorObsList)));
        comboList.forEach(combo -> combo.setCellFactory(param -> {
            final ListCell<ColorType> cell = new ListCell<ColorType>() {
                @Override
                public void updateItem(final ColorType item, final boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                        setText(item.toString());
                        switch (item) {
                        case RED:
                            setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                            break;
                        case GREEN:
                            setBackground(new Background(new BackgroundFill(ColorType.GREEN.getColor(), null, null)));
                            break;
                        case BLUE:
                            setBackground(new Background(new BackgroundFill(ColorType.BLUE.getColor(), null, null)));
                            break;
                        case YELLOW:
                            setBackground(new Background(new BackgroundFill(ColorType.YELLOW.getColor(), null, null)));
                            break;
                        case MAGENTA:
                            setBackground(new Background(new BackgroundFill(ColorType.MAGENTA.getColor(), null, null)));
                            break;
                        case TEAL:
                            setBackground(new Background(new BackgroundFill(ColorType.TEAL.getColor(), null, null)));
                            break;
                        case CYAN:
                            setBackground(new Background(new BackgroundFill(ColorType.CYAN.getColor(), null, null)));
                            break;
                        case PINK:
                            setBackground(new Background(new BackgroundFill(ColorType.PINK.getColor(), null, null)));
                            break;
                        case VIOLET:
                            setBackground(new Background(new BackgroundFill(ColorType.VIOLET.getColor(), null, null)));
                            break;
                        case OLIVE:
                            setBackground(new Background(new BackgroundFill(ColorType.OLIVE.getColor(), null, null)));
                            break;
                        default:
                            setTextFill(Color.WHITE);
                            break;
                        }

                    } else {
                        setText(null);
                    }
                }
            };
            return cell;
        }));

        locationList = new ArrayList<>(mainController.getLocationsList());
        locationList.forEach(loc -> comboLocationList.add(loc.getName()));
        comboLocation.setItems(comboLocationList);
        comboFluid.setDisable(true);
    }

    private void setRadioButtons() {
        final ToggleGroup groupWind = new ToggleGroup();
        ((RadioButton) getNodeNested(mainController, "radioWind1", PANETOT)).setToggleGroup(groupWind);
        ((RadioButton) getNodeNested(mainController, "radioWind2", PANETOT)).setToggleGroup(groupWind);
        ((RadioButton) getNodeNested(mainController, "radioWind1", PANETOT)).setSelected(true);
    }

    private void setImages() {
        imgURL = NewGameController.class.getResource("/background.png");
        ((ImageView) getNode(mainController, "backgroundImage")).setImage(new Image(imgURL.toString()));
        imgURL = NewGameController.class.getResource("/newgame_start_off.png");
        startButton.setImage(new Image(imgURL.toString()));
        imgURL = NewGameController.class.getResource("/newgame_back_off.png");
        quitButton.setImage(new Image(imgURL.toString()));
        imgURL = NewGameController.class.getResource("/newgame_plus_off.png");
        plusImageList.forEach(p -> p.setImage(new Image(imgURL.toString())));
        imgURL = NewGameController.class.getResource("/newgame_minus_off.png");
        lessImageList.forEach(l -> l.setImage(new Image(imgURL.toString())));
        imgURL = NewGameController.class.getResource("/tick.png");
        tickList.forEach(t -> t.setImage(new Image(imgURL.toString())));
    }

    @FXML
    private void actionStart() {
        final Random rnd = new Random();
        // Verifica Location e Fluid
        if (comboFluid.getSelectionModel().getSelectedItem() != null) {
            selectedLocation = (Location) locationList.stream()
                    .filter(l -> l.getName().equals(comboLocation.getSelectionModel().getSelectedItem()))
                    .collect(Collectors.toList()).get(0);
            selectedFluid = (Fluid) selectedLocation.getChild().stream()
                    .filter(f -> f.getName().equals(comboFluid.getSelectionModel().getSelectedItem()))
                    .collect(Collectors.toList()).get(0);
        }

        // Verifica Seed, se omesso verrà passato Random
        if (textSeed.getText().length() != 0) {
            try {
                seedSelected = Long.parseLong(textSeed.getText());
            } catch (final Exception e) {
                seedSelected = rnd.nextLong();
            }

        } else {

            seedSelected = rnd.nextLong();
        }
        // Verifica se il vento è stato scelto o meno
        if (!((RadioButton) getNodeNested(mainController, "radioWind1", PANETOT)).isSelected()) {
            fluidStationary = true;
        } else {
            fluidStationary = false;
        }

        tickList.stream().filter(t -> t.isVisible()).forEach(t -> {

            final Color color = comboList.get(tickList.indexOf(t)).getSelectionModel().getSelectedItem().getColor();

            playersList.add(new Pair<>(textList.get(tickList.indexOf(t)).getText(), color));

        });
        mainController.sceneGame();
    }

    @FXML
    private void actionBack() {
        mainController.sceneMenu();
    }

    /**
     * Returns the seed selected or generated.
     *
     * @return seed
     */
    public long getSeed() {
        return seedSelected;
    }

    /**
     * Return the fluidStationary decision.
     *
     * @return fluidStationary
     */
    public boolean isFluidStationary() {
        return fluidStationary;
    }

    /**
     *
     * @return selectedLocation
     */
    public Location getLocation() {
        return selectedLocation;
    }

    /**
     *
     * @return selectedFluid
     */
    public Fluid getFluid() {
        return selectedFluid;
    }

    /**
     * Return the a list of player divided in player name and tank color.
     *
     * @return playersList
     */
    public List<Pair<String, Color>> getPlayersList() {
        return playersList;
    }

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
        imgURL = NewGameController.class.getResource("/dialog_yes_off.png");
        yesButton.setImage(new Image(imgURL.toString()));
        imgURL = NewGameController.class.getResource("/dialog_no_off.png");
        noButton.setImage(new Image(imgURL.toString()));
    }

    private void setListenerDialog() {
        imgURL = NewGameController.class.getResource("/dialog_no_on.png");
        btnYes.setOnAction(e -> actionYesDialog());
        btnNo.setOnAction(e -> actionNoDialog());
        btnYes.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = NewGameController.class.getResource("/dialog_yes_on.png");
            yesButton.setImage(new Image(imgURL.toString()));
        });
        btnYes.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = NewGameController.class.getResource("/dialog_yes_off.png");
            yesButton.setImage(new Image(imgURL.toString()));
        });
        btnNo.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = NewGameController.class.getResource("/dialog_no_on.png");
            noButton.setImage(new Image(imgURL.toString()));
        });
        btnNo.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = NewGameController.class.getResource("/dialog_no_off.png");
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
