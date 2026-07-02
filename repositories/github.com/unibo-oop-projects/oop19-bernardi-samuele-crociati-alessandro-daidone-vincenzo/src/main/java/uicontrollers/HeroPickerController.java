package uicontrollers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import controller.ControllerImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * Controller of the scene where you pick your heroes and add them to your team.
 */
public final class HeroPickerController {

    private static final double IMG_CONTRAST = 0.4;
    private static final double IMG_HUE = 0.05;
    private static final double IMG_BRIGHTNESS = 0.8;
    private static final double IMG_SATURATION = 0.8;
    private static final int COLS_IN_ROW = 5;
    private static final int DEFAULT_DIMENSION = 40;

    @FXML
    private Label heroname;

    @FXML
    private Label hp;

    @FXML
    private Label atk;

    @FXML
    private Label def;

    @FXML
    private Label movement;

    @FXML
    private Label atkType;

    @FXML
    private Label turn;

    @FXML
    private BorderPane heroPickerScreen;

    @FXML
    private VBox team1;

    @FXML
    private VBox team2;

    @FXML
    private GridPane heropool;

    private HeroPickerController hpc;

    private List<HeroButton> lteam1;

    private List<HeroButton> lteam2;

    /**
     * 
     * @param heroImages
     *            List containing the name of the images for each hero pickable in
     *            game
     * @param hpc
     *            Controller of the scene
     * 
     * @param initialPlayerTurn
     *            String rappresenting the first player to pick
     */
    public void initData(final List<String> heroImages, final HeroPickerController hpc,
            final String initialPlayerTurn) {
        if (this.hpc == null) {
            this.hpc = hpc;
        }
        int irow = 0;
        int icol = 0;
        int index = 0;
        lteam1 = new ArrayList<>();
        lteam2 = new ArrayList<>();
        this.turn.setText(initialPlayerTurn);
        for (final String s : heroImages) {
            final Image heroImg = new Image(getClass().getResource(s).toExternalForm());
            final ImageView iv = new ImageView();
            iv.setImage(heroImg);
            iv.setFitWidth(DEFAULT_DIMENSION);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setCache(true);
            final HeroButton btn = new HeroButton();
            btn.setIdentifier(index);
            btn.setGraphic(iv);
            btn.setOnAction(e -> {
                this.heroClick(e);
            });
            btn.focusedProperty().addListener((obs, oldVal, newVal) -> {
                this.heroFocusChanged(btn, newVal, COLS_IN_ROW);
            });
            btn.hoverProperty().addListener((obs, oldVal, newVal) -> {
                this.heroHover(btn, COLS_IN_ROW);
            });
            this.heropool.add(btn, icol, irow);
            icol++;
            if (icol == COLS_IN_ROW) {
                icol = 0;
                irow++;
            }
            index++;
        }
    }

    /**
     * 
     * @param e
     *            event of the button clicked
     */
    public void heroClick(final ActionEvent e) {
        final HeroButton btnSource = (HeroButton) e.getSource();
        ControllerImpl.getInstance().pickerSelectionAction(this.hpc, btnSource.getIdentifier());
    }

    /**
     * 
     * @param btnSource
     *            the button used to check if it's focused or not
     * @param newVal
     *            if it's focused a new button
     * 
     * @param columns
     *            number of columns of the hero pool
     */
    public void heroFocusChanged(final Button btnSource, final Boolean newVal, final int columns) {
        if (btnSource.isFocused()) {
            btnSource.setStyle("-fx-border-color: #ff0000; -fx-border-width: 1px;");
            ControllerImpl.getInstance().changePickerPosition(GridPane.getRowIndex(btnSource),
                    GridPane.getColumnIndex(btnSource), columns, this.hpc);
        } else {
            btnSource.setStyle("-fx-border-color: transparent;");
        }
    }

    /**
     * 
     * @param btnSource
     *            button used to check if it's hovered by the cursor
     * @param columns
     *            number of columns of the hero pool
     */
    public void heroHover(final Button btnSource, final int columns) {
        if (btnSource.isHover()) {
            btnSource.requestFocus();
            ControllerImpl.getInstance().pickerSelectionInfo(this.hpc);
            btnSource.setStyle("-fx-border-color: #ff0000; -fx-border-width: 1px;");
            ControllerImpl.getInstance().changePickerPosition(GridPane.getRowIndex(btnSource),
                    GridPane.getColumnIndex(btnSource), columns, this.hpc);
        } else {
            btnSource.setStyle("-fx-border-color: transparent;");
        }
    }

    /**
     * 
     * @param isPickable
     *            if the hero is pickable
     * @param index
     *            the indentifier of the hero picked
     * 
     */
    public void updatePool(final boolean isPickable, final int index) {
        final HeroButton btn = (HeroButton) heropool.getChildren().get(index);
        final ColorAdjust colorAdjust = new ColorAdjust();
        final ImageView iv = new ImageView();
        final Image heroImage = ((ImageView) btn.getGraphic()).getImage();
        iv.setImage(heroImage);
        iv.setFitWidth(DEFAULT_DIMENSION);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        if (!isPickable) {
            colorAdjust.setContrast(IMG_CONTRAST);
            colorAdjust.setHue(-IMG_HUE);
            colorAdjust.setBrightness(IMG_BRIGHTNESS);
            colorAdjust.setSaturation(IMG_SATURATION);
            iv.setEffect(colorAdjust);
        }
        btn.setGraphic(iv);
    }

    /**
     * 
     * @param playerTurn
     *            the string with the player turn
     */
    public void updateTurn(final String playerTurn) {
        this.turn.setText(playerTurn);
    }

    /**
     * 
     * @param heroImageName
     *            name of the image of the hero added in the team one
     * @param identifier
     *            indentifier of the hero
     */
    public void addTeamOne(final String heroImageName, final int identifier) {
        final Image img = new Image(heroImageName);
        final ImageView iv = new ImageView(img);
        iv.setFitWidth(DEFAULT_DIMENSION);
        iv.setFitHeight(DEFAULT_DIMENSION);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        final HeroButton hBtn = new HeroButton();
        hBtn.setIdentifier(identifier);
        hBtn.setGraphic(iv);
        VBox.setMargin(hBtn, new Insets(COLS_IN_ROW));
        this.lteam1.add(hBtn);
        this.team1.getChildren().add(hBtn);
    }

    /**
     * 
     * @param heroImageName
     *            name of the image of the hero added in the team two
     * @param identifier
     *            indentifier of the hero
     */
    public void addTeamTwo(final String heroImageName, final int identifier) {
        final Image img = new Image(heroImageName);
        final ImageView iv = new ImageView(img);
        iv.setFitWidth(DEFAULT_DIMENSION);
        iv.setFitHeight(DEFAULT_DIMENSION);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        final HeroButton hBtn = new HeroButton();
        hBtn.setIdentifier(identifier);
        hBtn.setGraphic(iv);
        VBox.setMargin(hBtn, new Insets(COLS_IN_ROW));
        this.lteam2.add(hBtn);
        this.team2.getChildren().add(hBtn);
    }

    /**
     * @param identifier
     *            id of the hero to be removed in team one
     */
    public void removeTeamOne(final int identifier) {
        HeroButton remove = null;
        for (final HeroButton hBtn : this.lteam1) {
            if (hBtn.getIdentifier() == identifier) {
                remove = hBtn;
            }
        }
        this.lteam1.remove(remove);
        final List<Node> save = this.team1.getChildren().stream().filter(n -> !(n instanceof HeroButton))
                .collect(Collectors.toList());
        this.team1.getChildren().clear();
        this.team1.getChildren().addAll(save);
        this.team1.getChildren().addAll(this.lteam1);
    }

    /**
     * @param identifier
     *            id of the hero to be removed in team one
     */
    public void removeTeamTwo(final int identifier) {
        HeroButton remove = null;
        for (final HeroButton hBtn : this.lteam2) {
            if (hBtn.getIdentifier() == identifier) {
                remove = hBtn;
            }
        }
        this.lteam2.remove(remove);
        final List<Node> save = this.team2.getChildren().stream().filter(n -> !(n instanceof HeroButton))
                .collect(Collectors.toList());
        this.team2.getChildren().clear();
        this.team2.getChildren().addAll(save);
        this.team2.getChildren().addAll(this.lteam2);
    }

    /**
     * 
     * @param name
     *            name of the current hero in the picker
     * @param maxHp
     *            maximum health points of the current hero in the picker
     * @param attack
     *            attack points of the current hero in the picker
     * @param defence
     *            defence points of the current hero in the picker
     * @param movementRange
     *            movement range of the current hero in the picker
     * @param attackRange
     *            attack range of the current hero in the picker
     */
    public void showPickerInfo(final String name, final String maxHp, final String attack, final String defence,
            final String movementRange, final String attackRange) {
        this.hp.setText(maxHp);
        this.atk.setText(attack);
        this.def.setText(defence);
        this.movement.setText(movementRange);
        this.atkType.setText(attackRange);
        this.heroname.setText(name);
    }

    @FXML
    private void openGame() { // NOPMD
        ControllerImpl.getInstance().loadBattleScene();
    }
}
