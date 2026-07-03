package view.game;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import controller.CircuitPlayable;
import controller.Controller;
import controller.file.ViewFile;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import model.RaceDirectionImpl;
import utility.Direction;
import utility.Driver;
import utility.Pair;
import utility.Position;
import utility.TyreType;
import view.Wait;
/**
 * Controller of fxmlView.
 *
 */
public class ViewGameImpl implements ViewGame {

    /**
     * Standard width of map.
     */
    public static final int STD_WIDTH = 1920;
    /**
     * Standard height of map.
     */
    public static final int STD_HEIGHT = 1080;
    private static final double STD_DIM_STOP = 25.0;
    private static final int FIVE = 5;
    private static final int DIM_PANEL_LEFT = 270;
    private static final int TIME_STOP = 300;
    private static final int DIM_STRING = 106;
    private static final int HEIGHT_RANK_CAR = 20;
    private Controller ctr;
    private Map<Driver, DriverViewImpl> listDriver;
    private final Wait<Direction> diceClass = new Wait<>();
    private Map<Position, Slot> slotMap;
    private Map<Driver, HBox> rankBox;
    private CircuitPlayable circuit;
    private ImageView circuitMap;
    private ImageView stop;
    private int zoomFactor;
    private Dialog<Boolean> infoDialog = new Dialog<>();


    /*Fields for help find right coordinate of slot
     * 
    TextField field;
    TextField x = new TextField();
    TextField y = new TextField();
    TextField rot = new TextField();*/

    @FXML // fx:id="gamePanel"
    private Pane gamePanel; // Value injected by FXMLLoader

    @FXML // fx:id="throwDiceButton"
    private Button throwDiceButton; // Value injected by FXMLLoader

    @FXML // fx:id="lapPanel"
    private HBox lapPanel; // Value injected by FXMLLoader

    @FXML // fx:id="tyreType"
    private Label tyreType; // Value injected by FXMLLoader

    @FXML // fx:id="outDice"
    private Label outDice; // Value injected by FXMLLoader

    @FXML // fx:id="pointShell"
    private Label pointShell; // Value injected by FXMLLoader

    @FXML // fx:id="team"
    private Label team; // Value injected by FXMLLoader

    @FXML // fx:id="right"
    private Button right; // Value injected by FXMLLoader

    @FXML // fx:id="driverCell"
    private Label driverCell; // Value injected by FXMLLoader

    @FXML // fx:id="straight"
    private Button straight; // Value injected by FXMLLoader

    @FXML // fx:id="left"
    private Button left; // Value injected by FXMLLoader

    @FXML // fx:id="driverRound"
    private Label driverRound; // Value injected by FXMLLoader

    @FXML // fx:id="boxButton"
    private Button boxButton; // Value injected by FXMLLoader

    @FXML // fx:id="zoomInButton"
    private Button zoomInButton; // Value injected by FXMLLoader

    @FXML // fx:id="zoomOutButton"
    private Button zoomOutButton; // Value injected by FXMLLoader

    @FXML // fx:id="ranking"
    private GridPane ranking; // Value injected by FXMLLoader

    @FXML // fx:id="placement"
    private Label placement; // Value injected by FXMLLoader

    @FXML // fx:id="tyreDecay"
    private Label tyreDecay; // Value injected by FXMLLoader

    @FXML // fx:id="laps"
    private Label laps; // Value injected by FXMLLoader

    @FXML // fx:id="remainingLaps"
    private Label remainingLaps; // Value injected by FXMLLoader

    @FXML // fx:id="direction"
    private HBox direction; // Value injected by FXMLLoader

    @FXML // fx:id="panel"
    private VBox panel; // Value injected by FXMLLoader

    /**
     * Advise throwDice the chose of the player.
     * @param event click on button to go left
     */
    @FXML
    public void goLeft(final ActionEvent event) {
        diceClass.actionPerformed(Direction.LEFT);
    }

    /**
     * Advise throwDice the chose of the player.
     * @param event click on button to go straight
     */
    @FXML
    public void goStraight(final ActionEvent event) {
        diceClass.actionPerformed(Direction.STRAIGHT);
    }

    /**
     * Advise throwDice the chose of the player.
     * @param event click on button to go right
     */
    @FXML
    public void goRight(final ActionEvent event) {
        diceClass.actionPerformed(Direction.RIGHT);
    }

    /**
     * Advise throwDice the chose of the player.
     * @param event click on button to go to box
     */
    @FXML
    public void goBox(final ActionEvent event) {
        diceClass.actionPerformed(Direction.BOX);
    }

    /**
     * Set visible direction panel and the number of dice.
     * @param event click on button "Throw Dice"
     */
    @FXML
    public void throwDice(final ActionEvent event) {
        this.outDice.setVisible(true);
        this.throwDiceButton.setDisable(true);
        this.direction.setVisible(true);
    }

    /**
     * Do test.
     * @param event click on button "Test"
     */
    @FXML // NOPMD
    public void test(final ActionEvent event) {
    }

    /**
     * Zoom in the map.
     * @param event click on button "Zoom in"
     */
    @FXML
    public void zoomIn(final ActionEvent event) {
        this.zoomOutButton.setDisable(false);
        this.zoomFactor++;
        zoom();
        if (zoomFactor >= 10) {
            this.zoomInButton.setDisable(true);
        }
    }

    /**
     * Zoom out the map.
     * @param event click on button "Zoom out"
     */
    @FXML
    public void zoomOut(final ActionEvent event) {
        this.zoomInButton.setDisable(false);
        this.zoomFactor--;
        zoom();
        final Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        if ((STD_WIDTH * (10 + zoomFactor - 1) / 10.0) <= (screen.getWidth() - DIM_PANEL_LEFT) 
                /*|| (HEIGHT * (10 + zoomFactor - 1) / 10.0) <= screen.getHeight()*/) {
            this.zoomOutButton.setDisable(true);
        }
    }

    /**
     * Initialize the game panel.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize() {
        assert gamePanel != null : "fx:id=\"gamePanel\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert panel != null : "fx:id=\"gamePanel\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert throwDiceButton != null : "fx:id=\"throwDiceButton\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert tyreType != null : "fx:id=\"tyreType\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert outDice != null : "fx:id=\"outDice\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert pointShell != null : "fx:id=\"pointShell\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert team != null : "fx:id=\"team\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert right != null : "fx:id=\"right\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert driverCell != null : "fx:id=\"driverCell\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert straight != null : "fx:id=\"straight\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert left != null : "fx:id=\"left\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert driverRound != null : "fx:id=\"driverRound\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert boxButton != null : "fx:id=\"boxButton\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert placement != null : "fx:id=\"placement\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert tyreDecay != null : "fx:id=\"tyreDecay\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert remainingLaps != null : "fx:id=\"placement\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert laps != null : "fx:id=\"placement\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert direction != null : "fx:id=\"direction\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert ranking != null : "fx:id=\"ranking\" was not injected: check your FXML file 'FxmlView.fxml'.";
        assert lapPanel != null : "fx:id=\"ranking\" was not injected: check your FXML file 'FxmlView.fxml'.";
        this.lapPanel.setBackground(new Background(new BackgroundFill(Color.web("#D5D6DA"), CornerRadii.EMPTY, Insets.EMPTY)));
        this.zoomFactor = 0;
        this.stop = new ImageView();
        this.stop.setImage(new Image(this.getClass().getResourceAsStream("/stop.png")));
        this.stop.setPreserveRatio(true);
        this.stop.setFitHeight(STD_DIM_STOP);
        /* Code for find coordinate of game panel
        this.panel.getChildren().add(new HBox(x, y, rot));
        field = new TextField();
        this.panel.getChildren().add(field);*/

        /*code below set in button
        this.listDriver.get(Driver.HAM).moveDriver(new SlotImpl(new PositionImpl(1, 1, 1), Double.parseDouble(x.getText()), Double.parseDouble(y.getText()), Double.parseDouble(rot.getText())), gamePanel.getWidth(), gamePanel.getHeight());
        this.field.setText(x.getText() + "," + y.getText() + "," + rot.getText());*/
    }


    @Override
    public void setControllerAndFile(final Controller ctr, final ViewFile fileContainer) {
        this.ctr = ctr;
        this.circuit = fileContainer.getCircuit();
        this.setSlotMap(fileContainer.getCoordinates());
        Platform.runLater(() -> {
            this.setMapAndDriver(fileContainer.getCircuitImage());
        });
    }


    @Override
    public void startRace() {
        final Wait<Boolean> waitStart = new Wait<>();
        Platform.runLater(() -> {
            this.zoomInButton.setDisable(true);
            this.zoomOutButton.setDisable(true);
            this.remainingLaps.setText(Integer.toString(ctr.lapsCompleted()));
            this.laps.setText(Integer.toString(this.circuit.getLaps()));
            this.lapPanel.setVisible(true);
            final Button start = new Button("START");
            start.setPrefSize(100, 100 / 2);
            start.setTranslateX(this.gamePanel.getWidth() / 2);
            start.setTranslateY(this.gamePanel.getHeight() * 2 / 3);
            start.setOnAction(event -> {
                this.gamePanel.getChildren().remove(start);
                this.zoomInButton.setDisable(false);
                this.zoomOutButton.setDisable(false);
                waitStart.actionPerformed(true);
            });
            this.gamePanel.getChildren().add(start);
        });
        waitStart.waitForUser();
    }

    @Override
    public Direction throwDice(final int number, final Pair<Boolean, Boolean> canDir, final boolean canBox) {
        Platform.runLater(() -> {
            this.outDice.setText(Integer.toString(number));
            this.left.setDisable(!canDir.getX());
            this.right.setDisable(!canDir.getY());
            this.boxButton.setDisable(!canBox);
            this.throwDiceButton.setDisable(false); 
        });
        diceClass.waitForUser();
        this.direction.setVisible(false);
        this.outDice.setVisible(false);
        return diceClass.getValue();
    }


    @Override
    public void update(final Driver drv, final Position pos, final boolean block) {
        Platform.runLater(() -> {
            this.listDriver.get(drv).moveDriver(this.slotMap.get(pos), this.gamePanel.getWidth(), this.gamePanel.getHeight());
        });
        if (block) {
            Platform.runLater(() -> {
                stop.setFitHeight(STD_DIM_STOP * (1 + this.zoomFactor / 10.0));
                stop.setTranslateX(this.slotMap.get(pos).getXCoord() * gamePanel.getWidth() / 100);
                stop.setTranslateY(this.slotMap.get(pos).getYCoord() * gamePanel.getHeight() / 100);
                stop.setRotate(this.slotMap.get(pos).getRotation());
                this.gamePanel.getChildren().add(stop);
            });
            try {
                Thread.sleep(TIME_STOP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                this.gamePanel.getChildren().remove(stop);
            });
        }
    }


    @Override
    public void updateRound(final InfoDriver info) {
        this.remainingLaps.setText(Integer.toString(this.ctr.lapsCompleted()));
        this.driverRound.setText(info.getName());
        this.driverCell.setText(info.getDriverName());
        this.team.setText(info.getTeam());
        this.placement.setText(info.getPosition());
        this.tyreType.setText(info.getTyreType());
        this.tyreDecay.setText(info.getTyreDecay() + "%");
        this.pointShell.setText(info.getShellPoint());
    }


    @Override
    public void crash(final Map<Driver, Optional<String>> crashedPlayers) {
        //text
        final StringBuilder text = new StringBuilder(DIM_STRING).append("Incredibile! Contatto tra ");
        crashedPlayers.forEach((x, y) -> {
            text.append(x.toString());
            if (y.isPresent()) {
                text.append("(" + y.get() + ")");
            }
            text.append(", ");  // NOPMD
        });
        text.delete(text.length() - 2, text.length());
        text.append(".\nUn punto telaio in meno per ogni pilota coinvolto.");

        //Image
        final ImageView img = new ImageView();
        img.setImage(new Image(this.getClass().getResourceAsStream("/crash.png")));
        final String header = new String("Incidente tra " + crashedPlayers.size() + " piloti!");
        this.alertInfo("Incidente", header, text.toString(), img);
    }




    @Override
    public void retire(final Map<Driver, Optional<String>> retiredPlayers) {
      //text
        final StringBuilder text = new StringBuilder(DIM_STRING).append("Nooo!!! ");
        retiredPlayers.forEach((x, y) -> {
            text.append(x.toString());
            if (y.isPresent()) {
                text.append("(" + y.get() + ")");
            }
            text.append(", ");
        });
        text.delete(text.length() - 2, text.length());
        if (retiredPlayers.size() != 1) {
            text.append(" hanno");
        } else {
            text.append(" ha");
        }
        text.append(" distrutto la propria vettura, perche' ");
        if (retiredPlayers.size() != 1) {
            text.append("hanno");
        } else {
            text.append("ha");
        }
        text.append(" finito i punti telaio.");
        //Image
        final ImageView img = new ImageView();
        img.setImage(new Image(this.getClass().getResourceAsStream("/black.png")));
        this.alertInfo("Ritiro", "Qualcuno ha distrutto la propria vettura!", text.toString(), img);
    }


    @Override
    public void disqualify(final Map<Driver, Optional<String>> disqualifiedPlayers) {
        //text
        final StringBuilder text = new StringBuilder(DIM_STRING);
        if (disqualifiedPlayers.size() != 1) {
            text.append("I piloti ");
        } else {
            text.append("Il pilota ");
        }
        disqualifiedPlayers.forEach((x, y) -> {
            text.append(x.toString());
            if (y.isPresent()) {
                text.append("(" + y.get() + ")");
            }
            text.append(", ");
        });
        text.delete(text.length() - 2, text.length());
        if (disqualifiedPlayers.size() != 1) {
            text.append(" sono stati squalificati");
        } else {
            text.append(" e' stato squalificato");
        }
        text.append(" perche' non ");
        if (disqualifiedPlayers.size() != 1) {
            text.append("hanno");
        } else {
            text.append("ha");
        }
        text.append(" montato due set di gomme diverse.");
        //Image
        final ImageView img = new ImageView();
        img.setImage(new Image(this.getClass().getResourceAsStream("/black.png")));
        this.alertInfo("Squalifica", "Regole violate!", text.toString(), img);
    }

    @Override
    public void rankUpdate(final List<Driver> rank) {
        ranking.getChildren().removeAll(this.rankBox.values());
        for (int i = 0; i < rank.size(); i++) {
            ranking.add(this.rankBox.get(rank.get(i)), 1, i + 1);
        }
    }


    @Override
    public void rankQualifying(final List<Pair<Driver, Integer>> rank) {
        closeInfoDialog();
        final Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Classifica Qualifica");
        final GridPane table = new GridPane();
        table.addRow(0, text("Pos.", HPos.CENTER), text("Piloti", HPos.CENTER), text("N. Lanci", HPos.CENTER));
        rank.forEach(a -> {
            table.addRow(rank.indexOf(a) + 1, text(Integer.toString(rank.indexOf(a) + 1), HPos.CENTER), 
                         this.rankBox.get(a.getX()), 
                         text(a.getY().toString(), HPos.CENTER));
        });
        //table.setGridLinesVisible(true);
        table.setHgap(FIVE);
        table.setVgap(10);
        dialog.getDialogPane().setContent(table);
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("GARA", ButtonData.OK_DONE));
        dialog.initOwner(this.gamePanel.getScene().getWindow());
        dialog.showAndWait();
    }



    @Override
    public boolean rankRace(final List<Driver> rank) {
        closeInfoDialog();
        final Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Classifica Gara");
        final GridPane table = new GridPane();
        table.addRow(0, text("Pos.", HPos.CENTER), text("Piloti", HPos.CENTER));
        rank.forEach(a -> {
            table.addRow(rank.indexOf(a) + 1, 
                         text(Integer.toString(rank.indexOf(a) + 1), HPos.CENTER), 
                         this.rankBox.get(a));
        });
        //table.setGridLinesVisible(true);
        table.setHgap(FIVE);
        table.setVgap(10);
        dialog.getDialogPane().setContent(table);
        final ButtonType menu = new ButtonType("Torna al menu'", ButtonData.OK_DONE);
        final ButtonType close = new ButtonType("Chiudi", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(menu, close);
        dialog.setResultConverter(dialogButton -> {
            return dialogButton.equals(menu);
        });
        Optional<Boolean> result = Optional.empty();
        while (!result.isPresent()) {
            result = dialog.showAndWait();
        }
        return result.get();
    }



    @Override
    public TyreType box(final String name) {
        closeInfoDialog();
        final Dialog<TyreType> dialog = new Dialog<>();
        dialog.setTitle("Pit-stop");
        dialog.setContentText("Che tipo di pneumatico vuoi mettere, " + name + "?");
        final Map<ButtonType, TyreType> mapButton = new HashMap<>();
        circuit.getTyresAvailable().forEach(a -> {
            final ButtonType button = new ButtonType(a.getName(), ButtonData.OTHER);
            dialog.getDialogPane().getButtonTypes().add(button);
            mapButton.put(button, a);
        });
        dialog.setResultConverter(dialogButton -> {
            return mapButton.get(dialogButton);
        });
        Optional<TyreType> result = Optional.empty();
        dialog.initOwner(this.gamePanel.getScene().getWindow());
        while (!result.isPresent()) {
            result = dialog.showAndWait();
        }
        return result.get();
    }





    //PRIVATE METHOD


    private void setMapAndDriver(final Image map) {
        this.circuitMap = new ImageView(map);
        this.circuitMap.setPreserveRatio(true);
        this.gamePanel.getChildren().add(circuitMap);
        //Set driver in invisible slot of the map
        final List<Driver> list = new ArrayList<>(Arrays.asList(Driver.values()));
        this.listDriver = new HashMap<>();
        this.rankBox = new HashMap<>();
        list.forEach(a -> {
            final ImageView img = new ImageView();
            switch(a.getTeam()) {
            case MER:
                img.setImage(new Image(this.getClass().getResourceAsStream("/cars/mercedesB.png")));
                break;
            case FER:
                img.setImage(new Image(this.getClass().getResourceAsStream("/cars/ferrariB.png")));
                break;
            case RDB:
                img.setImage(new Image(this.getClass().getResourceAsStream("/cars/redbullB.png")));
                break;
            case MCL: 
                img.setImage(new Image(this.getClass().getResourceAsStream("/cars/mclarenB.png")));
                break;
            case REN: 
                img.setImage(new Image(this.getClass().getResourceAsStream("/cars/renaultB.png")));
                break;
            default:
                break;
            }
            img.setPreserveRatio(true);
            img.setFitHeight(HEIGHT_RANK_CAR);
            final HBox box = new HBox(5, img, new Label(a.toString()));
            box.setAlignment(Pos.CENTER_LEFT);
            this.rankBox.put(a, box);
            this.listDriver.put(a, new DriverViewImpl(a, slotMap.get(RaceDirectionImpl.END_POS)));
        });
        this.listDriver.forEach((x, y) -> {
            y.setOnMouseClicked(event -> {
                dialogInfo(y);
            });
            this.gamePanel.getChildren().add(y);
        });
    }

    private void setSlotMap(final BufferedReader coordinates) {
        this.slotMap = SlotCreatorImpl.getSingleton().createSlots(coordinates);
    }

    private void alertInfo(final String title, final String header, final String text, final ImageView img) {
        closeInfoDialog();
        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.setGraphic(img);
        alert.initOwner(this.gamePanel.getScene().getWindow());
        alert.showAndWait();
    }

    private Label text(final String text, final HPos alignment) {
        final Label l = new Label(text);
        GridPane.setHalignment(l, alignment);
        return l;
    }

    private void dialogInfo(final DriverView driver) {
        infoDialog = new Dialog<>();
        final InfoDriver info = ctr.getInfo(driver.getDriver());
        int row = 0;
        infoDialog.setTitle(info.getDriverName());
        final GridPane table = new GridPane();
        table.addRow(row, text("Nome giocatore:", HPos.LEFT), text(info.getName(), HPos.LEFT));
        row++;
        table.addRow(row, text("Pilota:", HPos.LEFT), text(info.getDriverName(), HPos.LEFT));
        row++;
        table.addRow(row, text("Team:", HPos.LEFT), text(info.getTeam(), HPos.LEFT));
        row++;
        table.addRow(row, text("Posizione:", HPos.LEFT), text(info.getPosition(), HPos.LEFT));
        row++;
        table.addRow(row, text("Gomme:", HPos.LEFT), text(info.getTyreType(), HPos.LEFT));
        row++;
        table.addRow(row, text("Usura gomme:", HPos.LEFT), text(info.getTyreDecay() + "%", HPos.LEFT));
        row++;
        table.addRow(row, text("Punti telaio:", HPos.LEFT), text(info.getShellPoint(), HPos.LEFT));
        //table.setGridLinesVisible(true);
        table.setHgap(FIVE);
        table.setVgap(10);
        infoDialog.getDialogPane().setContent(table);
        infoDialog.getDialogPane().getButtonTypes().add(new ButtonType("Chiudi", ButtonData.OK_DONE));
        infoDialog.initOwner(this.gamePanel.getScene().getWindow());
        infoDialog.showAndWait();
    }

    private void closeInfoDialog() {
        if (infoDialog.isShowing()) {
            infoDialog.close();
        }
    }

    private void zoom() {
        final double n = (10 + this.zoomFactor) / 10.0;
        stop.setFitHeight(STD_DIM_STOP * n);
        this.circuitMap.setFitHeight(STD_HEIGHT * n);
        this.gamePanel.setMinSize(STD_WIDTH * n, STD_HEIGHT * n);
        this.gamePanel.setPrefSize(STD_WIDTH * n, STD_HEIGHT * n);
        this.gamePanel.setMaxSize(STD_WIDTH * n, STD_HEIGHT * n);
        this.listDriver.forEach((x, img) -> {
            img.setFitHeight(DriverViewImpl.STANDARD_DIM * n);
            img.moveDriver(this.slotMap.get(img.getPosition()), STD_WIDTH * n, STD_HEIGHT * n);
        });
    }
}
