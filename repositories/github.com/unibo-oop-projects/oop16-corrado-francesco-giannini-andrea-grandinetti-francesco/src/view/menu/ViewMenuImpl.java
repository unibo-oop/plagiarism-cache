package view.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import controller.CircuitPlayable;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utility.Driver;
import view.Wait;

/**
 * Controller of FxmlMenu.
 *
 */
public class ViewMenuImpl implements ViewMenu {

    private final Wait<Boolean> wait = new Wait<>();
    private Controller ctr;
    private Label allert;

    @FXML // fx:id="secondPage"
    private VBox secondPage; // Value injected by FXMLLoader

    @FXML // fx:id="firstPage"
    private HBox firstPage; // Value injected by FXMLLoader

    @FXML // fx:id="backButton"
    private Button backButton; // Value injected by FXMLLoader

    @FXML // fx:id="onlyRace"
    private CheckBox onlyRace; // Value injected by FXMLLoader

    @FXML // fx:id="numPlayer"
    private ChoiceBox<Integer> numPlayer; // Value injected by FXMLLoader

    @FXML // fx:id="circuits"
    private ChoiceBox<CircuitPlayable> circuits; // Value injected by FXMLLoader

    /*@FXML // fx:id="VBox"
    private VBox VBox; // Value injected by FXMLLoader
    */

    /**
     * To initialize the FXML.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    public void initialize() {
        assert secondPage != null : "fx:id=\"secondPage\" was not injected: check your FXML file 'FxmlMenu.fxml'.";
        assert firstPage != null : "fx:id=\"firstPage\" was not injected: check your FXML file 'FxmlMenu.fxml'.";
        assert backButton != null : "fx:id=\"backButton\" was not injected: check your FXML file 'FxmlMenu.fxml'.";
        assert onlyRace != null : "fx:id=\"onlyRace\" was not injected: check your FXML file 'FxmlMenu.fxml'.";
        assert numPlayer != null : "fx:id=\"numPlayer\" was not injected: check your FXML file 'FxmlMenu.fxml'.";
        assert circuits != null : "fx:id=\"circuits\" was not injected: check your FXML file 'FxmlMenu.fxml'.";
        final ObservableList<Integer> num = FXCollections.observableArrayList(IntStream.range(1, 10 + 1).boxed().collect(Collectors.toList()));
        final ObservableList<CircuitPlayable> circuitsP = FXCollections.observableArrayList(Arrays.asList(CircuitPlayable.values()));
        this.circuits.setItems(circuitsP);
        this.circuits.setValue(CircuitPlayable.MELBOURNE);
        this.numPlayer.setItems(num);
        this.numPlayer.setValue(1);
    }

    /**
     * To passed on the second menu.
     * @param event click on button
     */
    @FXML
    public void toSecond(final ActionEvent event) {
        final List<BoxNamePlayer> list = new ArrayList<>();
        int i;
        for (i = 1; i <= this.numPlayer.getValue(); i++) {
            list.add(new BoxNamePlayerImpl(i, circuits.getValue()));
            this.secondPage.getChildren().add((BoxNamePlayerImpl) list.get(list.size() - 1));
        }
        final Button play = new Button("Gioca");
        this.allert = new Label();
        this.secondPage.getChildren().add(play);
        this.secondPage.getChildren().add(allert);
        play.setOnAction((ActionEvent e) -> play(list));
        this.firstPage.setVisible(false);
        this.backButton.setVisible(true);
        this.secondPage.setVisible(true);
    }

    /**
     * Return to the main menu.
     * @param event click on button "Back"
     */
    @FXML
    public void back(final ActionEvent event) {
        this.secondPage.getChildren().clear();
        this.secondPage.setVisible(false);
        this.backButton.setVisible(false);
        this.firstPage.setVisible(true);
    }

    @Override
    public void setController(final Controller ctr) {
        this.ctr = ctr;
    }

    @Override
    public void waitMenu() {
        wait.waitForUser();
    }

    private void play(final List<BoxNamePlayer> list) {
        final Set<Driver> listDriver = new TreeSet<>();
        final Set<String> listName = new TreeSet<>();
        list.forEach(a -> listDriver.add(a.getDriver()));
        list.forEach(a -> {
            if (!a.getName().isEmpty() && !a.getName().startsWith(" ")) {
                listName.add(a.getName());
            }
        });
        if (listDriver.size() == numPlayer.getValue() && listName.size() == numPlayer.getValue()) {
            setGame(list);
            wait.actionPerformed(true);
        } else if (listDriver.size() != numPlayer.getValue()) {
            allert.setText("Inserire piloti differenti per ogni giocatore.");
        } else {
            allert.setText("Non possono esserci nomi di giocatori uguali o vuoti.");
        }
    }

    private void setGame(final List<BoxNamePlayer> list) {
        if (onlyRace.isSelected()) {
            ctr.setOnlyRace();
        }
        ctr.setCircuit(circuits.getValue());
        list.forEach(a-> {
            ctr.addPlayer(a.getName(), a.getDriver());
            ctr.setPlayerInitialTyre(a.getDriver(), a.getTyre());
        });
    }
}
